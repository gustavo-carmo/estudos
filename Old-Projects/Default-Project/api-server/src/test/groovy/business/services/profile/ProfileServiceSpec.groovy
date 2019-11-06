package business.services.profile

import bridge.User
import business.Contractor
import business.Customer
import business.ProfileService
import business.Tenant
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification

@TestFor(ProfileService)
class ProfileServiceSpec extends Specification {

    SpringSecurityService springSecurityService
    @Shared
    User userMocked
    @Shared
    Tenant tenantMocked
    @Shared
    Customer customerMocked

    def setup() {
        0 * _
        springSecurityService = Mock(SpringSecurityService)
        service.springSecurityService = springSecurityService
        userMocked = Mock(User)
        tenantMocked = Mock(Tenant)
        customerMocked = Mock(Customer)
    }

    def cleanup() {
    }

    void "Deverá retornar o tenant do usuário logado"() {
        when: // usuário com tenant
        Tenant tenant = service.resolveTenant()
        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getTenant() >> tenantMocked
        and:
        tenant == tenantMocked
    }

    void "Deverá retornar o cliente do usuário logado"() {
        when:
        Customer customer = service.resolveCustomer()
        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getCustomer() >> customerMocked
        and:
        customer == customerMocked
    }

    void "Deverá retornar o email do usuário logado"() {
        given:
        String emailMocked = "email@email.com"

        when:
        String email = service.resolveEmail()
        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getEmail() >> emailMocked
        and:
        email == emailMocked
    }

    void "Deverá retornar o nome do usuário logado"() {
        given:
        String nameMocked = "User Name Bla"

        when:
        String name = service.resolveName()
        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getName() >> nameMocked
        and:
        name == nameMocked
    }

    void "Deverá dizer se o usuario é do perfil admin tenant" () {

        given:
        def ret

        when: // É do perfil admin tenant
        ret = service.hasAffiliationWithTenant()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.hasAffiliationWithTenant() >> true

        and:
        ret != null
        ret == true

        when: // Não é do perfil admin tenant
        ret = service.hasAffiliationWithTenant()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.hasAffiliationWithTenant() >> false

        and:
        ret != null
        ret == false
    }

    void "Deverá dizer se o usuario é do perfil cliente" () {

        given:
        def ret

        when: // É do perfil cliente
        ret = service.hasAffiliationWithCustomer()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.hasAffiliationWithCustomer() >> true

        and:
        ret != null
        ret == true

        when: // Não é do perfil cliente
        ret = service.hasAffiliationWithCustomer()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.hasAffiliationWithCustomer() >> false

        and:
        ret != null
        ret == false
    }

    void "Deverá dizer se o usuario é do perfil admin" () {

        given:
        def ret

        when: // É do perfil admin
        ret = service.isAdmin()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.isAdmin() >> true

        and:
        ret != null
        ret == true

        when: // Não é do perfil cliente
        ret = service.isAdmin()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.isAdmin() >> false

        and:
        ret != null
        ret == false
    }

    void "Deverá retornar o contratante do usuário logado" () {

        given:
        def ret
        Customer customerMocked = Mock(Customer)
        Contractor contractorMocked = Mock(Contractor)

        when: // Encontrou um contratante
        ret = service.resolveContractor()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getCustomer() >> customerMocked
        1 * customerMocked.getContractor() >> contractorMocked

        and:
        ret != null
        ret == contractorMocked

        when: // Não encontrou um usuario
        ret = service.resolveContractor()

        then:
        1 * service.springSecurityService.getCurrentUser() >> null

        and:
        ret == null

        when: // Não encontrou um cliente
        ret = service.resolveContractor()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getCustomer() >> null

        and:
        ret == null

        when: // Não encontrou um contratante
        ret = service.resolveContractor()

        then:
        1 * service.springSecurityService.getCurrentUser() >> userMocked
        1 * userMocked.getCustomer() >> customerMocked
        1 * customerMocked.getContractor() >> null

        and:
        ret == null
    }
}
