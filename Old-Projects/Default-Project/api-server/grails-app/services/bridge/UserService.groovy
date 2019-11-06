package bridge

import bridge.exception.AppValidationException
import bridge.misc.ErrorMessage
import bridge.misc.ResultService
import bridge.misc.SearchParams
import bridge.command.ShowCommand
import business.ChangePasswordByTokenCommand
import business.ResetPasswordCommand
import business.enums.EnabledOrDisabled
import grails.transaction.Transactional

@Transactional
class UserService {

    def utilService
    def repositoryService
    def grailsHelperService
    def profileService

    ResultService resetPassword(ResetPasswordCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        def username = command.username

        def user = repositoryService.findUserByUsername(username)

        if (!user) {

            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "username",
                            "default.not.found.message",
                            ["ResetPasswordCommand class", "username"]
                    )
            )
        } else if (!user.enabled) {

            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "username",
                            "retrievePasswordCommand.username.error.userIsDisabled"

                    )
            )
        }

        if (resultService.isValid()) {

            def token = utilService.generateRandomString(64)
            repositoryService.saveTokenToResetPassword(user, token)

            // TODO - resolve this...
            def linkToRetrievePassword = "http://localhost:8090/#!/blank/recover-password/${token}"

            utilService.sendEmail(user?.email,
                    "Recuperar a senha",
                    "Usuario " + user?.username + " segue o link (" +
                            linkToRetrievePassword +
                            ") para resetar sua senha link"
            )
        }

        return resultService
    }

    ResultService changePasswordByToken(ChangePasswordByTokenCommand command) {
        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        TokenToResetPassword tokenToResetPassword = repositoryService.findTokenToResetPasswordByToken(
                command.token)

        if (!tokenToResetPassword) {
            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "token",
                            "default.not.found.message",
                            ["TokenToResetPassword class", "token"]
                    )
            )
        } else {
            User user = tokenToResetPassword.user
            user.password = command.password
            repositoryService.saveUser(user)
            repositoryService.deleteTokenToResetPassword(tokenToResetPassword)
        }

        return resultService
    }

    ResultService searchUser(SearchUserCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        if (!profileService.isAdmin()) {
            resultService.errorMessage = ErrorMessage.buildWithMessageCode(
                    "user.not.adminUser")
            return resultService
        }

        SearchParams searchParams = new SearchParams(
                currentPage: command.currentPage,
                itemsPerPage: command.itemsPerPage
        )

        searchParams.addFilter("name", command.name)
        searchParams.addFilter("username", command.username)
        def status = command.status
        if (status) {
            searchParams.addFilter(
                    "enabled",
                    status == EnabledOrDisabled.ENABLED
            )
        }
        searchParams.addFilter("tenant", tenant)
        searchParams.addFilter("customerIsNull", true)

        resultService.data = repositoryService.findUsers(searchParams)

        return resultService
    }

    ResultService saveUser(SaveUserCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            log.error("O command de usuário é invalido")
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        log.info("Validando se o usuario logado esta autorizado a criar usuarios")
        if (!profileService.isAdmin() && !profileService.hasAffiliationWithTenant()) {
            resultService.errorMessage = ErrorMessage.buildWithMessageCode(
                    "user.not.adminUser.or.not.hasAffiliationWithTenant")
            return resultService
        }

        User user

        def username = command.username
        def tenantId = command.tenantId
        def tenant

        log.info("Procurando um usuario com o nome de usuário[$username]")

        user = repositoryService.findUserByUsernameEqualsAndTenantIsNull(username)



        if (user) {
            log.error("Já existe um usuario com o nome de usuário informado")
            if (tenantId) {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "username",
                                "saveUserCommand.error.username.tenant.exist"
                        )
                )

                return resultService
            } else {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "username",
                                "saveUserCommand.error.username.exist"
                        )
                )

                return resultService
             }
        }

        def email = command.email
        if (tenantId) {
            user = repositoryService.findUserByEmailEqualsAndTenantEquals(
                    email,
                    tenant
            )
        } else {
            user = repositoryService.findUserByEmailAndTenantIsNull(
                    email
            )
        }

        if (user) {
            if (tenantId) {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "email",
                                "saveUserCommand.error.email.tenant.exist"
                        )
                )

                return resultService
            } else {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "email",
                                "saveUserCommand.error.email.exist"
                        )
                )

                return resultService
            }
        }

        def enabled = (command.status == EnabledOrDisabled.ENABLED)

        user = new User()
        user.name = command.name
        user.email = email
        user.username = username
        user.password = command.password
        user.enabled = enabled
        user.tenant = tenant
        user.customer = command.customer
        user.createdDate = new Date()

        return repositoryService.saveUser(user)
    }

    ResultService showUser(ShowCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        if (!profileService.isAdmin()) {
            resultService.errorMessage = ErrorMessage.buildWithMessageCode(
                    "user.not.adminUser")
            return resultService
        }

        def id = command.id

        def user = repositoryService.findUserById(id)

        if (!user) {
            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "id",
                            "default.not.found.message",
                            ["ShowUserCommand class", "id"]
                    )
            )
        }

        resultService.data = user

        return resultService
    }

    ResultService updateUser(UpdateUserCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        if (!profileService.isAdmin() && !profileService.hasAffiliationWithTenant()) {
            resultService.errorMessage = ErrorMessage.buildWithMessageCode(
                    "user.not.adminUser.or.not.hasAffiliationWithTenant")
            return resultService
        }

        User user
        User userToUpdate = repositoryService.findUserById(command.id)

        if (!userToUpdate) {
            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "id",
                            "updateUserCommand.error.id.not.found"
                    )
            )

            return resultService
        }

        def username = command.username
        def tenantId = command.tenantId
        def tenant

        if (tenantId) {
            tenant = repositoryService.findTenantById(tenantId)
            user = repositoryService.findUserByUsernameEqualsAndTenantEquals(username, tenant)
        } else {
            user = repositoryService.findUserByUsernameEqualsAndTenantIsNull(username)
        }

        if (user && (user != userToUpdate)) {
            if (tenantId) {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "username",
                                "updateUserCommand.error.username.tenant.exist"
                        )
                )

                return resultService
            } else {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "username",
                                "updateUserCommand.error.username.exist"
                        )
                )

                return resultService
            }
        }

        def email = command.email
        if (tenantId) {
            user = repositoryService.findUserByEmailEqualsAndTenantEquals(
                    email,
                    tenant
            )
        } else {
            user = repositoryService.findUserByEmailAndTenantIsNull(
                    email
            )
        }

        if (user && (user != userToUpdate)) {
            if (tenantId) {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "email",
                                "updateUserCommand.error.email.tenant.exist"
                        )
                )

                return resultService
            } else {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "email",
                                "updateUserCommand.error.email.exist"
                        )
                )

                return resultService
            }
        }

        def enabled = (command.status == EnabledOrDisabled.ENABLED)

        userToUpdate.name = command.name
        userToUpdate.email = email
        userToUpdate.username = username

        def password = command.password
        if (password) {
            userToUpdate.password = password
        }
        userToUpdate.enabled = enabled
        userToUpdate.tenant = tenant
        userToUpdate.createdDate = new Date()

        return repositoryService.saveUser(userToUpdate)
    }

    ResultService searchRoles(RolesListCommand command) {
        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        User user = repositoryService.findUserById(command.id)

        if (!user) {
            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "id",
                            "default.not.found.message",
                            ["User class", "id"]
                    )
            )
        } else {
            SearchParams searchParams = new SearchParams(
                    currentPage: command.currentPage,
                    itemsPerPage: command.itemsPerPage
            )

            searchParams.addFilter("user", user)
            resultService.data = repositoryService.findUserRoles(searchParams)
        }

        return resultService
    }

    ResultService updateRole(RolesUpdateCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        User user = repositoryService.findUserById(command.idUser)

        if (!user) {

            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command.idUser,
                            "id",
                            "default.not.found.message",
                            ["User class", "id"]
                    )
            )
        } else {

            repositoryService.removeAllRoles(user)

            def idRoles = command.roles

            if (!idRoles) {
                return resultService
            }

            def searchParams = new SearchParams()
            searchParams.addFilter("ids", idRoles)
            def searchResult = repositoryService.findRoles(searchParams)

            def totalCount = searchResult.totalCount

            if (!totalCount) {

                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "roles",
                                "default.not.found.message",
                                ["Role class", "roles"]
                        )
                )
            } else if (totalCount != idRoles.size()) {
                resultService.addValidationErrors(
                        grailsHelperService.resolveValidationErrors(
                                command,
                                "roles",
                                "roles.id.error.not.found"
                        )
                )
            } else {

                repositoryService.addRolesToUser(user, searchResult.instanceList)
            }
        }

        return resultService
    }
}
