package business.services.roleService

import bridge.exception.AppValidationException
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import bridge.GrailsHelperService
import business.RepositoryService
import bridge.Role
import bridge.RoleService
import bridge.command.ShowCommand

@TestFor(RoleService)
class ShowRoleSpec extends Specification {

    @Shared
    ShowCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    RepositoryService repositoryServiceMocked

    def setup() {
        commandMocked = Mock(ShowCommand)
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked
        service.grailsHelperService = Mock(GrailsHelperService)

    }

    def cleanup() {
    }

    void "Deverá pesquisar por uma role pelo id com sucesso"() {
        given:
        def resultService
        def commandMocked = Mock(ShowCommand)
        def id = 1

        when:
        resultService = service.showRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findRoleById(id) >> {
            return Mock(Role)
        }

        and:
        resultService.isValid()
        resultService.data != null
    }

    void "Devera dar erro quando ShowRoleCommand for invalido"() {

        given:
        def resultService
        def ex


        when:
        service.showRole(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.showRole(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando não localizar uma role"() {

        given:
        def resultService
        def id = 1

        when:
        resultService = service.showRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findRoleById(id) >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "id",
                "default.not.found.message",
                ["ShowCommand class", "id"]
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }
}
