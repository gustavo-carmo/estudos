package bridge

import bridge.controllers.ControllerHelpers
import bridge.command.SearchCommand
import bridge.command.ShowCommand
import business.enums.EnabledOrDisabled
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
class UsersController {

    static responseFormats = ['json']
    static allowedMethods = [resetPassword: "POST", changePasswordByToken: "PUT"]

    def userService

    @Secured(['ROLE_USER'])
    def index(SearchUserCommand command) {
        respond ControllerHelpers.resolveResponseToGetWithMultipleResult(
                userService.searchUser(command)
        )
    }

    @Secured(['ROLE_USER'])
    @Transactional(readOnly = false)
    def save(SaveUserCommand command) {
        respond ControllerHelpers.resolveResponseEntityReference(
                userService.saveUser(command),
                201
        )
    }

    @Secured(['ROLE_USER'])
    def show(ShowCommand command) {
        respond ControllerHelpers.resolveResponseToGetWithSingleResult(
                userService.showUser(command)
        )
    }

    @Secured(['ROLE_USER'])
    @Transactional(readOnly = false)
    def update(UpdateUserCommand command) {

        command.id = params.long("id")

        respond ControllerHelpers.resolveResponseEntityReference(
                userService.updateUser(command),
                200
        )
    }

    @Secured(['ROLE_USER'])
    @Transactional(readOnly = true)
    def rolesList(RolesListCommand command) {

        respond ControllerHelpers.resolveResponseToGetWithMultipleResult(
                userService.searchRoles(command)
        )
    }

    @Secured(['ROLE_USER'])
    @Transactional(readOnly = false)
    def rolesUpdate(RolesUpdateCommand command) {

        command.idUser = params.long("id")

        respond ControllerHelpers.resolveResponse(
                userService.updateRole(command)
        )
    }
}

class SearchUserCommand extends SearchCommand {

    String name
    String username
    EnabledOrDisabled status

    static constraints = {
        name nullable: true
        username nullable: true
        status nullable: true
    }
}

class SaveUserCommand {

    String name
    String username
    String email
    String password
    EnabledOrDisabled status

    static constraints = {
        name nullable: false, blank: false
        username nullable: false, blank: false
        email nullable: false, blank: false, email: true
        password nullable: false, blank: false, minSize: 5, maxSize: 20
        status nullable: false
    }
}

class UpdateUserCommand {

    Long id
    String name
    String username
    String email
    String password
    EnabledOrDisabled status

    static constraints = {
        id nullable: false
        name nullable: false, blank: false
        username nullable: false, blank: false
        email nullable: false, blank: false, email: true
        password nullable: true, blank: false, minSize: 5, maxSize: 20
        status nullable: false
    }
}

class RolesListCommand extends SearchCommand {

    Long id

    static constraints = {

        id nullable: false
    }
}

class RolesUpdateCommand {

    Long idUser
    List<Long> roles

    static constraints = {
        idUser nullable: false
        roles nullable: true
    }
}
