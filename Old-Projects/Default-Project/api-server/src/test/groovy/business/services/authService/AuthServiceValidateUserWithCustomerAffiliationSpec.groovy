package business.services.authService

import bridge.misc.SearchParams
import bridge.misc.SearchResult
import business.Contractor
import business.Customer
import business.Tenant
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestFor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Shared
import spock.lang.Specification
import business.AuthService
import business.RepositoryService
import bridge.User

@TestFor(AuthService)
class AuthServiceValidateUserWithCustomerAffiliationSpec extends Specification {

    @Shared
    RepositoryService repositoryServiceMocked
    @Shared
    UserDetails loadUserByUsernameReturn

    @Shared
    String username
    @Shared
    String tenantIdentifier
    @Shared
    String credentials
    @Shared
    User userMocked
    @Shared
    Tenant tenantMocked
    @Shared
    UserDetails userDetailsMocked
    @Shared
    def expectedException

    def setup() {
        0 *_
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked

        username = "username"
        tenantIdentifier = "123n13n"
        credentials = "{username=$username, tenant_identifier=$tenantIdentifier}"
        userMocked = Mock(User)
        userDetailsMocked = Mock(UserDetails)
        tenantMocked = Mock(Tenant)
    }

    def cleanup() {
    }

    void "Deverá fazer login com sucesso quando usuário está associado a um cliente"() {

        given:
        SearchResult searchResultMocked = Mock(SearchResult)
        Customer customerMocked = Mock(Customer)
        Contractor contractorMocked = Mock(Contractor)

        when:
        loadUserByUsernameReturn = service.loadUserByUsername(credentials)

        then:
        1 * service.repositoryService.findUserByUsernameAndTenantIdentifier(
                username, tenantIdentifier) >> userMocked
        1 * userMocked.getTenant() >> tenantMocked
        1 * tenantMocked.getStatus() >> EnabledOrDisabled.ENABLED
        1 * userMocked.getUsername() >> "username"
        1 * repositoryServiceMocked.findCustomers(_ as SearchParams) >> searchResultMocked
        1 * searchResultMocked.getFirstItem() >> customerMocked
        1 * customerMocked.getContractor() >> contractorMocked
        1 * contractorMocked.getStatus() >> EnabledOrDisabled.ENABLED
        1 * userMocked.getUserDetails() >> userDetailsMocked

        and:
        loadUserByUsernameReturn == userDetailsMocked
    }

    void "Devera dar erro quando credentials for inválido"() {
        given:
        def ex

        when:
        service.loadUserByUsername(null)
        then:
        ex = thrown(UsernameNotFoundException)
        ex.message == "Credentials is a required param"

        when:
        service.loadUserByUsername("{tenant_identifier=tenant_identifier}")
        then:
        ex = thrown(UsernameNotFoundException)
        ex.message == "Credentials malformed, required username"
    }

    void "Deverá dar erro quando não encontrar um usuário"() {
        when:
        service.loadUserByUsername(credentials)

        then:
        1 * service.repositoryService.findUserByUsernameAndTenantIdentifier(username, tenantIdentifier) >> null

        and:
        expectedException = thrown(UsernameNotFoundException)
        expectedException.message == "User not found"
    }
}

