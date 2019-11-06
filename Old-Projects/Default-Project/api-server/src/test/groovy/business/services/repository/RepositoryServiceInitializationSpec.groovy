package business.services.repository

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification
import business.RepositoryService
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import business.Tenant

@TestFor(RepositoryService)
@Mock([Tenant])
class RepositoryServiceInitializationSpec extends Specification {

    @Shared
    Tenant tenant,
           tenantUrlSupportPortal,
           tenantUrlWkf

    def setup() {
        tenant = new Tenant().save(validate: false)
        tenantUrlSupportPortal = new Tenant(
                urlSupportPortal: "http://portal-2"
        ).save(validate: false)
        tenantUrlWkf = new Tenant(
                urlWkf: "http://wkf-3"
        ).save(validate: false)
    }

    def cleanup() {
    }

    void "Deverá pesquisar tenants com sucesso"() {

        given:
        SearchParams searchParams

        when: // all
        searchParams = new SearchParams()
        SearchResult searchResult = service.findTenants(searchParams)

        then:
        searchResult.instanceList.contains(tenant)
        searchResult.instanceList.contains(tenantUrlSupportPortal)
        searchResult.instanceList.contains(tenantUrlWkf)
        searchResult.totalCount == 3


        when: // urlSupport
        searchParams = new SearchParams()
        searchParams.addFilter("urlSupportPortal", tenantUrlSupportPortal.urlSupportPortal)
        searchResult = service.findTenants(searchParams)

        then:
        searchResult.instanceList.contains(tenantUrlSupportPortal)
        searchResult.totalCount == 1


        when: // urlWkf
        searchParams = new SearchParams()
        searchParams.addFilter("urlWkf", tenantUrlWkf.urlWkf)
        searchResult = service.findTenants(searchParams)

        then:
        searchResult.instanceList.contains(tenantUrlWkf)
        searchResult.totalCount == 1
    }
}
