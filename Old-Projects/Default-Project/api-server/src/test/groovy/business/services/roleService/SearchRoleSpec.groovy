package business.services.roleService

import bridge.exception.AppValidationException
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import business.RepositoryService
import bridge.RoleService
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.command.SearchCommand

@TestFor(RoleService)
class SearchRoleSpec extends Specification {

    @Shared
    SearchCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    RepositoryService repositoryServiceMocked

    def setup() {
        commandMocked = Mock(SearchCommand)
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked
    }

    def cleanup() {
    }

    void "Deverá pesquisar todas roles com sucesso"() {

        given:
        def resultService
        def currentPage = 1
        def itemsPerPage = 50

        when:
        resultService = service.searchRoles(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getCurrentPage() >> currentPage
        1 * commandMocked.getItemsPerPage() >> itemsPerPage
        1 * service.repositoryService.findRoles(_ as SearchParams) >> { SearchParams argument ->
            assert argument.currentPage == currentPage
            assert argument.itemsPerPage == itemsPerPage
            return Mock(SearchResult)
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
        service.searchRoles(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.searchRoles(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }
}
