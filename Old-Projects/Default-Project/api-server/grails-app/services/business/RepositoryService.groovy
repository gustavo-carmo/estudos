package business

import grails.transaction.Transactional
import bridge.misc.ResultService
import bridge.Role
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.TokenToResetPassword
import bridge.User
import bridge.UserRole

@Transactional
class RepositoryService {

    def findUserById(Long id) {

        if (!id) {
            return null
        }

        def searchParams = new SearchParams()
        searchParams.addFilter("id", id)

        return findUsers(searchParams).getFirstItem()
    }

    // TODO - precisa refatorar quem usa esse método,
    // o certo é enviar nome de usuário + tenant_identifier
    def User findUserByUsername(String username) {

        if (!username) {
            return null
        }

        def searchParams = new SearchParams()
        searchParams.addFilter("username", username)

        return findUsers(searchParams).getFirstItem()
    }

    // TODO - test
    def TokenToResetPassword findTokenToResetPasswordByToken(String token) {
        TokenToResetPassword.findByToken(token)
    }

    // TODO - tests
    def ResultService saveUser(User user) {
        def resultService = new ResultService()
        if (user.validate()) {
            resultService.data = user?.save()
        } else {
            resultService.addValidationErrors(user.errors)
        }
        return resultService
    }

    // TODO - test
    def deleteTokenToResetPassword(TokenToResetPassword token) {
        token?.delete()
    }

    // TODO - test
    def saveTokenToResetPassword(User user, String token) {
        new TokenToResetPassword(user: user, token: token).save()
    }

    // TODO - lastLoginStart, lastLoginEnd
    SearchResult findUsers(SearchParams searchParams = new SearchParams()) {

        def result = User.createCriteria().list(
                searchParams.paramsToPagination()
        ) {

            def id = searchParams?.getField("id")
            def name = searchParams?.getField("name")
            def username = searchParams?.getField("username")
            def usernameEq = searchParams?.getField("usernameEq")
            def enabled = searchParams?.getField("enabled")
            def email = searchParams?.getField("email")
            def emailEq = searchParams?.getField("emailEq")
            def lastLoginStart = searchParams?.getField("lastLoginStart")
            def lastLoginEnd = searchParams?.getField("lastLoginEnd")
            def tenant = searchParams?.getField("tenant")
//          To use param IsNull, expected send value true
            def customerIsNull = searchParams?.getField("customerIsNull")
            def tenantIsNull = searchParams?.getField("tenantIsNull")

            if (id) {
                eq 'id', id
            }

            if (name) {
                like 'name', "%${name}%"
            }

            if (customerIsNull) {
                isNull("customer")
            }

            if (tenantIsNull) {
                isNull("tenant")
            }

            if (username) {
                like 'username', "%${username}%"
            }

            if (usernameEq) {
                eq 'username', usernameEq
            }

            if (enabled != null) {
                eq 'enabled', enabled
            }

            if (email) {
                like 'email', "%${email}%"
            }

            if (emailEq) {
                eq 'email', emailEq
            }

            if (lastLoginStart && lastLoginEnd) {
                between 'lastLogin', lastLoginStart, lastLoginEnd
            } else if (lastLoginStart) {
                ge 'lastLogin', lastLoginStart
            } else if (lastLoginEnd) {
                // TODO - check essa condição não está sendo respeitada
                'le'('lastLogin', lastLoginEnd)
            }

            if (tenant) {
                eq 'tenant', tenant
            }
        }

        SearchResult searchResult = new SearchResult(searchParams)
        searchResult.instanceList = result
        searchResult.totalCount = result.totalCount

        return searchResult
    }


    SearchResult findRoles(SearchParams searchParams = new SearchParams()) {

        def result = Role.createCriteria().list(
                searchParams.paramsToPagination()
        ) {

            def id = searchParams?.getField("id")
            def ids = searchParams?.getField("ids")

            if (id) {
                eq 'id', id
            }

            if (ids) {
                'in' "id", ids
            }

        }

        SearchResult searchResult = new SearchResult(searchParams)
        searchResult.instanceList = result
        searchResult.totalCount = result.totalCount

        return searchResult
    }

    // TODO - tests
    def findRoleById(Long id) {
        return Role.findById(id)
    }

//    TODO - testes
    SearchResult findUserRoles(SearchParams searchParams = new SearchParams()) {

        def result = UserRole.createCriteria().list(
                searchParams.paramsToPagination()
        ) {

            def user = searchParams?.getField("user")

            if (user) {
                eq "user", user
            }
        }

        SearchResult searchResult = new SearchResult(searchParams)
        searchResult.instanceList = result
        searchResult.totalCount = result.totalCount

        return searchResult
    }

    //TODO refactor
    def removeAllRoles(User user) {
        UserRole.removeAll(user)
    }

    //TODO refactor
    def addRolesToUser(User user, List<Role> roles) {
        roles.each { role ->
            UserRole.create(user, role)
        }
    }

    User findUserByUsernameEquals(String username) {

        if (!username) {
            return null
        }

        def searchParams = new SearchParams()
        searchParams.addFilter("usernameEq", username)

        return findUsers(searchParams).getFirstItem()
    }

    User findUserByEmailEquals(String email) {

        if (!email) {
            return null
        }

        def searchParams = new SearchParams()
        searchParams.addFilter("emailEq", email)

        return findUsers(searchParams).getFirstItem()
    }
}