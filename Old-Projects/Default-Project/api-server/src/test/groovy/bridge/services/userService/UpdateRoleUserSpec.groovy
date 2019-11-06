package bridge.services.userService

import bridge.exception.AppValidationException
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import spock.lang.Shared
import spock.lang.Specification
import bridge.GrailsHelperService
import business.RepositoryService
import bridge.Role
import bridge.RolesUpdateCommand
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.User
import bridge.UserService

@TestFor(UserService)
class UpdateRoleUserSpec extends Specification {

    @Shared
    RolesUpdateCommand commandMocked
    @Shared
    Errors errorsMocked
    @Shared
    User userMocked
    @Shared
    SearchResult searchResultMocked

    def setup() {

        commandMocked = Mock(RolesUpdateCommand)
        errorsMocked = errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        userMocked = Mock(User)

        service.repositoryService = Mock(RepositoryService)
        service.grailsHelperService = Mock(GrailsHelperService)
        searchResultMocked = Mock(SearchResult)
    }

    def cleanup() {
    }

    void "Deverá atualizar as roles de um usuários com sucesso"() {

        given:
        def resultService

        List<Long> idRoles = [1]
        List<Role> roles = [Mock(Role)]

        when:
        resultService = service.updateRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * service.repositoryService.findUserById(commandMocked.getIdUser()) >> userMocked

        1 * service.repositoryService.removeAllRoles(userMocked)

        1 * commandMocked.getRoles() >> idRoles
        1 * service.repositoryService.findRoles(_ as SearchParams) >> { SearchParams params ->
            params.filters.get("ids") == idRoles
            return searchResultMocked
        }

        1 * searchResultMocked.getTotalCount() >> 1

        1 * searchResultMocked.instanceList >> roles

        1 * service.repositoryService.addRolesToUser(userMocked, roles)

        and:
        resultService != null
    }

    void "Deverá atualizar as roles de um usuários com sucesso(0 roles)"() {

        given:
        def resultService

        when:
        resultService = service.updateRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * service.repositoryService.findUserById(commandMocked.getIdUser()) >> userMocked

        1 * service.repositoryService.removeAllRoles(userMocked)

        1 * commandMocked.getRoles() >> null

        and:
        resultService.isValid()
        resultService != null
    }

    void "Devera dar erro quando commandObject for invalido"() {
        given:
        def resultService
        def ex


        when:
        service.updateRole(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.updateRole(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar um erro quando não encontrar um usuario"() {

        given:
        def resultService

        when:
        resultService = service.updateRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * service.repositoryService.findUserById(commandMocked.getIdUser()) >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked.idUser,
                "id",
                "default.not.found.message",
                ["User class", "id"]
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar um erro quando não encontrar as roles"() {

        given:
        def resultService

        List<Long> idRoles = [1]

        def roles = null

        when:
        resultService = service.updateRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * service.repositoryService.findUserById(commandMocked.getIdUser()) >> userMocked

        1 * service.repositoryService.removeAllRoles(userMocked)

        1 * commandMocked.getRoles() >> idRoles
        1 * service.repositoryService.findRoles(_ as SearchParams) >> { SearchParams params ->
            params.filters.get("ids") == idRoles
            return searchResultMocked
        }

        1 * searchResultMocked.getTotalCount() >> 0

        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "roles",
                "default.not.found.message",
                ["Role class", "roles"]
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar um erro quando não encontrar todas as roles desejadas"() {

        given:
        def resultService

        List<Long> idRoles = [1, 2, 4]

        def roles = [Mock(Role), Mock(Role)]

        when:
        resultService = service.updateRole(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * service.repositoryService.findUserById(commandMocked.getIdUser()) >> userMocked

        1 * service.repositoryService.removeAllRoles(userMocked)

        1 * commandMocked.getRoles() >> idRoles
        1 * service.repositoryService.findRoles(_ as SearchParams) >> { SearchParams params ->
            params.filters.get("ids") == idRoles
            return searchResultMocked
        }

        1 * searchResultMocked.getTotalCount() >> roles.size()

        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "roles",
                "roles.id.error.not.found"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

}
