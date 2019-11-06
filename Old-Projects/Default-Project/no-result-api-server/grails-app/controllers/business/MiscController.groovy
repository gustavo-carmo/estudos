package business

import bridge.controllers.ControllerHelpers
import bridge.controllers.ResponseErrorMode
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MiscController {

    static responseFormats = ['json']
    static allowedMethods = [
            initialization       : "GET",
            resetPassword        : "POST",
            changePasswordByToken: "PUT"
    ]

    def userService
    def initializationService

    def initialization(ConfigCommand command) {
        respond ControllerHelpers.resolveResponseToGetWithSingleResult(
                initializationService.config(command)
        )
    }

    @Transactional(readOnly = false)
    def resetPassword(ResetPasswordCommand command) {
        respond ControllerHelpers.resolveResponse(
                userService.resetPassword(command), ResponseErrorMode.ERROR_MODE_1
        )
    }

    @Transactional(readOnly = false)
    def changePasswordByToken(ChangePasswordByTokenCommand command) {
        respond ControllerHelpers.resolveResponse(
                userService.changePasswordByToken(command),
                ResponseErrorMode.ERROR_MODE_1
        )
    }
}

class ConfigCommand {

    String url

    static constraints = {
        url nullable: false, blank: false
    }
}

class ResetPasswordCommand {

    String username

    static constraints = {
        username nullable: false, blank: false
    }
}

class ChangePasswordByTokenCommand {

    String token
    String password

    static constraints = {
        token nullable: false, blank: false
        password nullable: false, blank: false
    }
}
