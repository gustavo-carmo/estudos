package bridge.services.userService

import bridge.exception.AppValidationException
import business.ProfileService
import business.Tenant
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import bridge.GrailsHelperService
import business.RepositoryService
import bridge.misc.ResultService
import bridge.UpdateUserCommand
import bridge.User
import bridge.UserService

@TestFor(UserService)
class userServiceUpdateSpec extends Specification {

    @Shared
    UpdateUserCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    User userMocked
    @Shared
    Long id, tenantId
    @Shared
    Tenant tenantMocked
    @Shared
    def resultService, name, email, username, password, status
    @Shared
    GrailsHelperService grailsHelperServiceMocked
    @Shared
    RepositoryService repositoryServiceMocked
    @Shared
    ProfileService profileServiceMocked

    def setup() {
        0 *_
        id = 2
        commandMocked = Mock(UpdateUserCommand)
        errorsMocked = errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        userMocked = Mock(User)
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked
        grailsHelperServiceMocked = Mock(GrailsHelperService)
        service.grailsHelperService = grailsHelperServiceMocked
        tenantMocked = Mock(Tenant)
        name = "name example"
        email = "exemplodeemail@teste.com"
        username = "usuarioTeste"
        password = "123456"
        tenantId = 4
        status = EnabledOrDisabled.DISABLED
        profileServiceMocked = Mock(ProfileService)
        service.profileService = profileServiceMocked
    }

    def cleanup() {
    }

    void "Deverá atualizar os dados de um usuários com sucesso"() {

        when: //usuario admin tenant
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> userMocked

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> tenantId

        1 * service.repositoryService.findTenantById(
                tenantId
        ) >> tenantMocked
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
        1 * userMocked.setName(name)

        1 * userMocked.setEmail(email)
        1 * userMocked.setUsername(username)

        1 * commandMocked.getPassword() >> password
        1 * userMocked.setPassword(password)
        1 * userMocked.setEnabled(false)
        1 * userMocked.setTenant(tenantMocked)
        1 * userMocked.setCreatedDate(_ as Date)

        1 * service.repositoryService.saveUser(_ as User) >> { User it ->
            return Mock(ResultService)
        }

        and:
        resultService != null

        when: //usuario admin
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> userMocked

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
        1 * userMocked.setName(name)

        1 * userMocked.setEmail(email)
        1 * userMocked.setUsername(username)

        1 * commandMocked.getPassword() >> password
        1 * userMocked.setPassword(password)
        1 * userMocked.setEnabled(false)
        1 * userMocked.setTenant(null)
        1 * userMocked.setCreatedDate(_ as Date)

        1 * service.repositoryService.saveUser(_ as User) >> { User it ->
            return Mock(ResultService)
        }

        and:
        resultService != null
    }

    void "Devera dar erro quando commandObject for invalido"() {
        given:
        def resultService
        def ex


        when:
        service.updateUser(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }


    void "Devera dar erro quando o usuario logado não for do perfil administrador"() {

        when:
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> false
        1 * profileServiceMocked.hasAffiliationWithTenant() >> false

        and:
        resultService != null
        resultService.errorMessage != null
    }

    void "Deverá dar um erro quando não encontrar um usuario"() {

        when:
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> null

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "id",
                "updateUserCommand.error.id.not.found"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar um erro quando ja tiver um usuario cadastrado com o nome de usuario informado"() {

        given:
        User userFoundWithUsername = Mock(User)

        when: //usuario admin tenant
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> userMocked

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> tenantId

        1 * service.repositoryService.findTenantById(
                tenantId
        ) >> tenantMocked
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantEquals(
                username,
                tenantMocked
        ) >> userFoundWithUsername

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "username",
                "updateUserCommand.error.username.tenant.exist"
        ) >> errorsMocked

        and:
        !resultService.isValid()

        when: //usuario admin
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> userMocked

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> null

        1 * service.repositoryService.findUserByUsernameEqualsAndTenantIsNull(
                username
        ) >> userFoundWithUsername

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "username",
                "updateUserCommand.error.username.exist"
        ) >> errorsMocked

        and:
        !resultService.isValid()

    }

    void "Deverá dar um erro quando ja tiver um usuario cadastrado com o email informado"() {


        given:
        User userFoundWithEmail = Mock(User)

        when: //usuario admin tenant
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> userMocked

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> tenantId

        1 * service.repositoryService.findTenantById(
                tenantId
        ) >> tenantMocked
        1 * service.repositoryService.findUserByUsernameEqualsAndTenantEquals(
                username,
                tenantMocked
        ) >> null

        1 * commandMocked.getEmail() >> email

        1 * service.repositoryService.findUserByEmailEqualsAndTenantEquals(
                email,
                tenantMocked
        ) >> userFoundWithEmail

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "email",
                "updateUserCommand.error.email.tenant.exist"
        ) >> errorsMocked

        and:
        !resultService.isValid()

        when: //usuario admin
        resultService = service.updateUser(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * profileServiceMocked.isAdmin() >> true
        1 * commandMocked.getId() >> id
        1 * service.repositoryService.findUserById(
                id
        ) >> userMocked

        1 * commandMocked.getUsername() >> username
        1 * commandMocked.getTenantId() >> null

        1 * service.repositoryService.findUserByUsernameEqualsAndTenantIsNull(
                username
        ) >> null

        1 * commandMocked.getEmail() >> email

        1 * service.repositoryService.findUserByEmailAndTenantIsNull(
                email
        ) >> userFoundWithEmail

        1 * grailsHelperServiceMocked.resolveValidationErrors(
                commandMocked,
                "email",
                "updateUserCommand.error.email.exist"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }
}
