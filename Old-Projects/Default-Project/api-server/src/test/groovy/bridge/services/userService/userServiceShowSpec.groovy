package bridge.services.userService

import bridge.exception.AppValidationException
import business.ProfileService
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import bridge.GrailsHelperService
import business.RepositoryService
import bridge.command.ShowCommand
import bridge.User
import bridge.UserService
import bridge.UtilService

@TestFor(UserService)
class userServiceShowSpec extends Specification {

    @Shared
    def id
    @Shared
    ShowCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    RepositoryService repositoryServiceMocked
    @Shared
    ProfileService profileServiceMocked

    def setup() {
        0 *_
        id = 1
        commandMocked = Mock(ShowCommand)
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked
        service.utilService = Mock(UtilService)
        service.grailsHelperService = Mock(GrailsHelperService)
        profileServiceMocked = Mock(ProfileService)
        service.profileService = profileServiceMocked
    }

    def cleanup() {
    }


    void "Deverá pesquisar por um usuário pelo Id com sucesso"() {

        given:
        def resultService

        when:
        resultService = service.showUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(id) >> {
            return Mock(User)
        }

        and:
        resultService.isValid()
        resultService.data != null
    }

    void "Devera dar erro quando commandObject for invalido"() {
        given:
        def resultService
        def ex


        when:
        service.showUser(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.showUser(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando não localizar um usuario"() {
        given:
        def resultService

        when:
        resultService = service.showUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(id) >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "id",
                "default.not.found.message",
                ["ShowUserCommand class", "id"]
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando o usuario logado não for do perfil administrador"() {

        given:
        def resultService

        when:
        resultService = service.showUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> false

        and:
        resultService != null
        resultService.errorMessage != null
    }
}
