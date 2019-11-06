package bridge.services.userService

import bridge.exception.AppValidationException
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import bridge.misc.ErrorMessage
import bridge.GrailsHelperService
import business.RepositoryService
import business.ResetPasswordCommand
import bridge.User
import bridge.UserService
import bridge.UtilService

@TestFor(UserService)
class ResetPasswordSpec extends Specification {

    @Shared
    ResetPasswordCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    User userMocked
    @Shared
    String email, username

    def setup() {
        commandMocked = Mock(ResetPasswordCommand)
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        userMocked = Mock(User)
        email = 'token@teste.com'
        username = 'Pedrinho'
        service.repositoryService = Mock(RepositoryService)
        service.utilService = Mock(UtilService)
        service.grailsHelperService = Mock(GrailsHelperService)
    }

    def cleanup() {
    }

    void "Devera enviar um email quando informar valores validos"() {
        given:
        def resultService
        def tokenRandom = "tokenRandom"
        def linkToRetrievePassword = "http://localhost:8090/#!/blank/recover-password/${tokenRandom}"

        when:
        resultService = service.resetPassword(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUsername() >> username
        1 * service.repositoryService.findUserByUsername(username) >> userMocked
        1 * userMocked.getEnabled() >> true
        1 * userMocked.getEmail() >> email
        1 * userMocked.getUsername() >> username
        1 * service.utilService.generateRandomString(64) >> tokenRandom
        1 * service.repositoryService.saveTokenToResetPassword(userMocked, tokenRandom)

        // TODO - gerar email de verdade
        1 * service.utilService.sendEmail(email,
                "Recuperar a senha",
                "Usuario " + username + " segue o link (" +
                        linkToRetrievePassword +
                        ") para resetar sua senha link"
        )

        and:
        resultService.isValid()

    }

    void "Devera dar erro quando commandObject for invalido"() {
        given:
        def resultService
        def ex


        when:
        service.resetPassword(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.resetPassword(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando não localizar um usuario"() {
        given:
        def resultService
        GroovyMock(ErrorMessage)

        when:
        resultService = service.resetPassword(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUsername() >> username
        1 * service.repositoryService.findUserByUsername(username) >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "username",
                "default.not.found.message",
                ["ResetPasswordCommand class", "username"]
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando usuario esta desabilitado"() {
        given:
        def resultService

        when:
        resultService = service.resetPassword(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUsername() >> username
        1 * service.repositoryService.findUserByUsername(username) >> userMocked
        1 * userMocked.getEnabled() >> false
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "username",
                "retrievePasswordCommand.username.error.userIsDisabled"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }
}
