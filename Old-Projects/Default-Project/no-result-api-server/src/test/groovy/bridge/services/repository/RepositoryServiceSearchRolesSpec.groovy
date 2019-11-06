package bridge.services.repository

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification
import business.RepositoryService
import bridge.Role
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.utils.StringUtils
import bridge.User
import bridge.UserRole

@TestFor(RepositoryService)
@Mock([User, Role, UserRole])
class RepositoryServiceSearchRolesSpec extends Specification {

    @Shared
    User user1, user2
    @Shared
    Role role1, role2, role3
    @Shared
    UserRole userRole1, userRole2, userRole3

    def setup() {
        user1 = new User().save(validate: false)
        user2 = new User().save(validate: false)

        role1 = new Role(authority: StringUtils.randomString(5)).save(validate: false)
        role2 = new Role(authority: StringUtils.randomString(5)).save(validate: false)
        role3 = new Role(authority: StringUtils.randomString(5)).save(validate: false)

        userRole1 = new UserRole(user: user1, role: role1).save(validate: false)
        userRole2 = new UserRole(user: user1, role: role2).save(validate: false)
        userRole3 = new UserRole(user: user2, role: role3).save(validate: false)
    }

    def cleanup() {
    }


    /* TODO - VERIFICAR POR QUE NÃO ESTA FUNCIONANDO O TESTE

    void "Deverá pesquisar userRoles com sucesso"() {
        given:
        SearchParams searchParams
        SearchResult searchResult

        when: // todos
        searchResult = service.findUserRoles()
        then:
        searchResult.instanceList.contains(userRole1)
        searchResult.instanceList.contains(userRole2)
        searchResult.instanceList.contains(userRole3)
        searchResult.totalCount == 3

        when: // user1
        searchParams = new SearchParams()
        searchParams.addFilter("user", user1)
        searchResult = service.findUserRoles(searchParams)
        then:
        searchResult.instanceList.contains(userRole1)
        searchResult.instanceList.contains(userRole2)
        searchResult.totalCount == 2

        when: // user2
        searchParams = new SearchParams()
        searchParams.addFilter("user", user2)
        searchResult = service.findUserRoles(searchParams)
        then:
        searchResult.instanceList.contains(userRole3)
        searchResult.totalCount == 1

    }*/
}
