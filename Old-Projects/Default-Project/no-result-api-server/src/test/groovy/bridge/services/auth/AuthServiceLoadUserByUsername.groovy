package bridge.services.auth

import bridge.User
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import business.AuthService
import business.RepositoryService
import business.Tenant
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestFor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Shared
import spock.lang.Specification

@TestFor(AuthService)
class AuthServiceLoadUserByUsername extends Specification {

    @Shared
    RepositoryService repositoryServiceMocked
    @Shared
    def result
    @Shared
    String username
    @Shared
    String tenant_identifier
    @Shared
    User userMocked
    @Shared
    def userDetailsMocked
    @Shared
    Tenant tenantMocked

    def setup () {
        0 *_
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked
        username = "usuario"
        tenant_identifier = "123456"
        userMocked = Mock(User)
        userDetailsMocked = Mock(UserDetails)
        tenantMocked = Mock(Tenant)
    }

    def cleanup () {
    }

    void "Deverá carregar os dados do usuario com sucesso para um usuario associado a um tenant" () {

        given:
        SearchResult searchResultMocked = Mock(SearchResult)
        def credentials = '''{
            "username": "usuario",
            "tenant_identifier": "123456"
        }'''

        when:
        result = service.loadUserByUsername(credentials)

        then:
        1 * service.repositoryService.findUserByUsernameAndTenantIdentifier(
                username, tenant_identifier) >> userMocked
        1 * userMocked.getTenant() >> tenantMocked
        1 * tenantMocked.getStatus() >> EnabledOrDisabled.ENABLED
        1 * userMocked.getUsername() >> null
        1 * repositoryServiceMocked.findCustomers(_ as SearchParams) >> searchResultMocked
        1 * searchResultMocked.getFirstItem() >> null
        1 * userMocked.getUserDetails() >> userDetailsMocked

        and:
        result == userDetailsMocked
    }

    void "Deverá carregar os dados do usuario com sucesso para um usuario que não esta associado a um tenant" () {

        given:
        def credentials = '''{
            "username": "usuario"
        }'''

        when:
        result = service.loadUserByUsername(credentials)

        then:
        1 * service.repositoryService.findUserByUsername(
                username
        ) >> userMocked
        1 * userMocked.getTenant() >> null
        1 * userMocked.getUserDetails() >> userDetailsMocked

        and:
        result == userDetailsMocked
    }

    void "Deverá dar erro ao carregar os dados do usuario quando não encontrar o usuario" () {

        given:
        def credentials = '''{
            "username": "usuario",
            "tenant_identifier": "123456"
        }'''
        def ex

        when:
        result = service.loadUserByUsername(credentials)

        then:
        1 * service.repositoryService.findUserByUsernameAndTenantIdentifier(
                username, tenant_identifier) >> null

        and:
        ex = thrown(UsernameNotFoundException)
        ex.message == "User not found"
    }

    void "Deverá dar erro ao carregar os dados do usuario quando o usuario que não esta associado a um tenant possuir um" () {

        given:
        def ex
        def credentials = '''{
            "username": "usuario"
        }'''

        when:
        result = service.loadUserByUsername(credentials)

        then:
        1 * service.repositoryService.findUserByUsername(
                username
        ) >> userMocked
        1 * userMocked.getTenant() >> Mock(Tenant)

        and:
        ex = thrown(UsernameNotFoundException)
        ex.message == "User and tenant found, but expected user doesn't have a tenant"
    }

    void "Deverá dar erro ao carregar os dados do usuario quando não informar as credenciais" () {

        given:
        def ex

        when:
        service.loadUserByUsername(null)

        then:
        ex = thrown(UsernameNotFoundException)
        ex.message == "Credentials is a required param"
    }
}
