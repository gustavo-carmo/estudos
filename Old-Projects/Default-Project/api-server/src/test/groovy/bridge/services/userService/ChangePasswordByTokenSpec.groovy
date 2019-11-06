package bridge.services.userService

import bridge.exception.AppValidationException
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import business.ChangePasswordByTokenCommand
import bridge.GrailsHelperService
import business.RepositoryService
import bridge.User
import bridge.TokenToResetPassword
import bridge.UserService

@TestFor(UserService)
class ChangePasswordByTokenSpec extends Specification {

    @Shared
    ChangePasswordByTokenCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    User userMocked
    @Shared
    TokenToResetPassword userChangePasswordMocked
    @Shared
    String token, password

    def setup() {
        commandMocked = Mock(ChangePasswordByTokenCommand)
        errorsMocked = errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        userMocked = Mock(User)
        userChangePasswordMocked = Mock(TokenToResetPassword)
        token = 'LKSHFS9fy9uherOIDFJOe'
        password = '123456'
        service.repositoryService = Mock(RepositoryService)
        service.grailsHelperService = Mock(GrailsHelperService)
    }

    def cleanup() {
    }

    void "Devera alterar a senha do usuario quando informo valores validos"() {
        given:
        def resultService
        def tokenToResetPassword = Mock(TokenToResetPassword)

        when:
        resultService = service.changePasswordByToken(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getToken() >> token
        1 * commandMocked.getPassword() >> password
        1 * service.repositoryService.findTokenToResetPasswordByToken(token) >> tokenToResetPassword
        1 * tokenToResetPassword.getUser() >> userMocked
        1 * userMocked.setPassword(password)
        1 * service.repositoryService.saveUser(userMocked)
        1 * service.repositoryService.deleteTokenToResetPassword(tokenToResetPassword)

        and:
        resultService.isValid()
    }

    void "Devera dar erro quando o commandObject for invalido"() {
        given:
        def resultService
        def ex


        when:
        service.changePasswordByToken(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.changePasswordByToken(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando não localizar um tokenToResetPassword com o token informado"() {
        given:
        def resultService

        when:
        resultService = service.changePasswordByToken(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getToken() >> token
        1 * service.repositoryService.findTokenToResetPasswordByToken(token) >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "token",
                "default.not.found.message",
                ["TokenToResetPassword class", "token"]
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }
}

