package bridge.services.userService

import bridge.GrailsHelperService
import bridge.exception.AppValidationException
import business.Customer
import business.ProfileService
import business.Tenant
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import business.RepositoryService
import bridge.misc.ResultService
import bridge.SaveUserCommand
import bridge.User
import bridge.UserService

@TestFor(UserService)
class userServiceSaveSpec extends Specification {

    @Shared
    SaveUserCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    def name
    @Shared
    def email
    @Shared
    def username
    @Shared
    def password
    @Shared
    def tenantId
    @Shared
    def resultService
    @Shared
    Customer customerMocked
    @Shared
    GrailsHelperService grailsHelperServiceMocked
    @Shared
    User userMocked
    @Shared
    ProfileService profileServiceMocked
    @Shared
    Tenant tenantMocked

    def setup() {
        0 *_
        commandMocked = Mock(SaveUserCommand)
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        service.repositoryService = Mock(RepositoryService)
        name = "example"
        email = "example@hotmail.com"
        username = "example"
        password = "123456"
        tenantId = 32
        grailsHelperServiceMocked = Mock(GrailsHelperService)
        service.grailsHelperService = grailsHelperServiceMocked
        userMocked = Mock(User)
        customerMocked = Mock(Customer)
        profileServiceMocked = Mock(ProfileService)
        service.profileService = profileServiceMocked
        tenantMocked = Mock(Tenant)
    }

    def cleanup() {
    }

    void "Deverá criar usuários com sucesso"() {
        given:
        def status = EnabledOrDisabled.DISABLED
        User userExpected

        when: // usuario admin(tenant)
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> tenantId
        1 * service.repositoryService.findTenantById(
                tenantId) >> tenantMocked
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantEquals(
                username,
                tenantMocked
        ) >> null

        1 * commandMocked.getEmail() >> email
        1 * service.repositoryService.findUserByEmailEqualsAndTenantEquals(
                email,
                tenantMocked
        ) >> null

        1 * commandMocked.getStatus() >> status

        1 * commandMocked.getName() >> name
        1 * commandMocked.getPassword() >> password
        1 * commandMocked.getCustomer() >> customerMocked


        1 * service.repositoryService.saveUser(_ as User) >> { User it ->
            userExpected = it
            return Mock(ResultService)
        }


        and:
        userExpected.name == name
        userExpected.email == email
        userExpected.username == username
        userExpected.password == password
        userExpected.enabled == false
        userExpected.tenant == tenantMocked
        userExpected.customer == customerMocked
        userExpected.createdDate != null
        resultService != null

        when: // usuario admin
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> null
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantIsNull(
                username
        ) >> null

        1 * commandMocked.getEmail() >> email
        1 * service.repositoryService.findUserByEmailAndTenantIsNull(
                email
        ) >> null

        1 * commandMocked.getStatus() >> status

        1 * commandMocked.getName() >> name
        1 * commandMocked.getPassword() >> password
        1 * commandMocked.getCustomer() >> customerMocked


        1 * service.repositoryService.saveUser(_ as User) >> { User it ->
            userExpected = it
            return Mock(ResultService)
        }


        and:
        userExpected.name == name
        userExpected.email == email
        userExpected.username == username
        userExpected.password == password
        userExpected.enabled == false
        userExpected.tenant == null
        userExpected.customer == customerMocked
        userExpected.createdDate != null
        resultService != null
    }

    void "Devera dar erro quando commandObject for invalido"() {
        given:
        def resultService
        def ex


        when:
        service.saveUser(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Devera dar erro quando o usuario logado não for do perfil administrador"() {

        when:
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> false
        1 * profileServiceMocked.hasAffiliationWithTenant() >> false

        and:
        resultService != null
        resultService.errorMessage != null
    }

    void "Deverá dar um erro quando ja tiver um usuario cadastrado com o nome de usuario informado"() {

        when: // usuario admin(tenant)
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> tenantId
        1 * service.repositoryService.findTenantById(
                tenantId) >> tenantMocked
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantEquals(
                username,
                tenantMocked
        ) >> userMocked

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "username",
                "saveUserCommand.error.username.tenant.exist"
        ) >> errorsMocked

        and:
        !resultService.isValid()

        when: // usuario admin
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> null
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantIsNull(
                username
        ) >> userMocked

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "username",
                "saveUserCommand.error.username.exist"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar um erro quando ja tiver um usuario cadastrado com o email informado"() {

        when: // usuario admin(tenant)
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> tenantId
        1 * service.repositoryService.findTenantById(
                tenantId) >> tenantMocked
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantEquals(
                username,
                tenantMocked
        ) >> null

        1 * commandMocked.getEmail() >> email
        1 * service.repositoryService.findUserByEmailEqualsAndTenantEquals(
                email,
                tenantMocked
        ) >> userMocked

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "email",
                "saveUserCommand.error.email.tenant.exist"
        ) >> errorsMocked

        when: // usuario admin
        resultService = service.saveUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> null
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantIsNull(
                username
        ) >> null

        1 * commandMocked.getEmail() >> email
        1 * service.repositoryService.findUserByEmailAndTenantIsNull(
                email
        ) >> userMocked

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "email",
                "saveUserCommand.error.email.exist"
        ) >> errorsMocked
    }
}