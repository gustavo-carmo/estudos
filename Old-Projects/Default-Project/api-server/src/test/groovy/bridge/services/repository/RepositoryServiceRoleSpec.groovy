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

@TestFor(RepositoryService)
@Mock([Role])
class RepositoryServiceRoleSpec extends Specification {

    @Shared
    Role role1, role2, role3

    def setup() {
        role1 = new Role(authority: StringUtils.randomString(5)).save(validate: false)
        role2 = new Role(authority: StringUtils.randomString(5)).save(validate: false)
        role3 = new Role(authority: StringUtils.randomString(5)).save(validate: false)
    }

    def cleanup() {
    }

    void "Deverá pesquisar roles com sucesso"() {
        given:
        SearchParams searchParams

        when: //todos
        SearchResult searchResult = service.findRoles()
        then:
        searchResult.totalCount == 3

        when: //id
        searchParams = new SearchParams()
        searchParams.addFilter("id", role1.id)
        searchResult = service.findRoles(searchParams)
        then:
        searchResult.instanceList.contains(role1)
        searchResult.totalCount == 1

        when: //ids
        searchParams = new SearchParams()
        searchParams.addFilter("ids", [role1.id, role2.id])
        searchResult = service.findRoles(searchParams)
        then:
        searchResult.instanceList.contains(role1)
        searchResult.instanceList.contains(role2)
        searchResult.totalCount == 2
    }

}
