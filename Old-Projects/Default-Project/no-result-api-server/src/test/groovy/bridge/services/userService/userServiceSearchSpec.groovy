package bridge.services.userService

import bridge.exception.AppValidationException
import business.ProfileService
import business.Tenant
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import business.RepositoryService
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.SearchUserCommand
import bridge.UserService

@TestFor(UserService)
class userServiceSearchSpec extends Specification {

    @Shared
    SearchUserCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    ProfileService profileServiceMocked

    def setup() {
        0 *_
        commandMocked = Mock(SearchUserCommand)
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        service.repositoryService = Mock(RepositoryService)
        profileServiceMocked = Mock(ProfileService)
        service.profileService = profileServiceMocked
    }

    def cleanup() {
    }

    void "Deverá pesquisar usuários com sucesso"() {
        given:
        def resultService
        def currentPage = 1
        def itemsPerPage = 10
        def name = "example"
        def username = "example"
        def status = EnabledOrDisabled.ENABLED
        def tenantId = 4
        Tenant tenantMocked = Mock(Tenant)

        when:
        resultService = service.searchUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getCurrentPage() >> currentPage
        1 * commandMocked.getItemsPerPage() >> itemsPerPage
        1 * commandMocked.getName() >> name
        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getStatus() >> status
        1 * commandMocked.getTenantId() >> tenantId
        1 * service.repositoryService.findTenantById(
                tenantId
        ) >> tenantMocked
        1 * service.repositoryService.findUsers(_ as SearchParams) >> { SearchParams argument ->
            assert argument.currentPage == currentPage
            assert argument.itemsPerPage == itemsPerPage
            assert argument.getField("name") == name
            assert argument.getField("username") == username
            assert argument.getField("enabled") == true
            assert argument.getField("tenant") == tenantMocked
            return Mock(SearchResult)
        }

        and:
        resultService.isValid()
        resultService.data != null
    }

    void "Deverá fazer a pesquisa de usuários sem o parametro enabled quando o status não tiver valor"() {
        given:
        def resultService
        def currentPage = 2
        def itemsPerPage = 20

        when:
        resultService = service.searchUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getCurrentPage() >> currentPage
        1 * commandMocked.getItemsPerPage() >> itemsPerPage
        1 * commandMocked.getName() >> null
        1 * commandMocked.getUsername() >> null
        1 * commandMocked.getStatus() >> null
        1 * commandMocked.getTenantId() >> null
        1 * service.repositoryService.findTenantById(
                null
        ) >> null
        1 * service.repositoryService.findUsers(_ as SearchParams) >> { SearchParams argument ->
            assert argument.currentPage == currentPage
            assert argument.itemsPerPage == itemsPerPage
            assert argument.getField("name") == null
            assert argument.getField("username") == null
            assert argument.getField("enabled") == null
            assert argument.getField("tenant") == null
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
        service.searchUser(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.searchUser(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando o usuario logado não for do perfil administrador"() {

        given:
        def resultService

        when:
        resultService = service.searchUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> false

        and:
        resultService != null
        resultService.errorMessage != null
    }
}
