package bridge.misc

import spock.lang.Specification
import spock.lang.Unroll
import bridge.misc.SearchParams
import bridge.misc.SearchResult

class SearchResultSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "Devera calcular com sucesso a paginação"() {
        given:
        SearchResult searchResult = new SearchResult(
                new SearchParams(
                        currentPage: currentPage,
                        itemsPerPage: itemsPerPage
                )
        )
        searchResult.totalCount = totalCount
        Map pagination

        when:
        pagination = searchResult.pagination()

        then:
        pagination.totalCount == totalCount
        pagination.totalPage == totalPage
        pagination.pageFirst == pageFirst
        pagination.pagePrevious == pagePrevious
        pagination.page == page
        pagination.pageNext == pageNext
        pagination.pageLast == pageLast
        pagination.perPage == perPage
        pagination.perPageLimit == perPageLimit

        where:
        totalCount | currentPage | itemsPerPage | totalPage | pageFirst | pagePrevious | page | pageNext | pageLast | perPage | perPageLimit
        65         | 4           | 10           | 7         | 1         | 3            | 4    | 5        | 7        | 10      | 100
        65         | 8           | 10           | 7         | 1         | 0            | 1    | 2        | 7        | 10      | 100
        65         | 1           | 100          | 1         | 1         | 0            | 1    | 0        | 1        | 100     | 100
        135        | 1           | 110          | 2         | 1         | 0            | 1    | 2        | 2        | 100     | 100
        95         | 10          | 10           | 10        | 1         | 9            | 10   | 0        | 10       | 10      | 100
        11         | 2           | 10           | 2         | 1         | 1            | 2    | 0        | 2        | 10      | 100
        11         | 2           | 20           | 1         | 1         | 0            | 1    | 0        | 1        | 20      | 100
        11         | null        | 10           | 2         | 1         | 0            | 1    | 2        | 2        | 10      | 100
        26         | null        | null         | 2         | 1         | 0            | 1    | 2        | 2        | 25      | 100
        70         | 1           | 10           | 7         | 1         | 0            | 1    | 2        | 7        | 10      | 100


    }

    void "Devera retornar um mapa vazio quando o totalCount não tiver valor"() {
        given:
        SearchResult searchResult = new SearchResult(new SearchParams())
        Map pagination

        when:
        pagination = searchResult.pagination()

        then:
        pagination == [:]
    }

    void "Deverá retornar a primeira linha do resultado"() {

        given:
        SearchResult searchResult = new SearchResult(
                new SearchParams()
        )
        searchResult.totalCount = 3
        searchResult.instanceList = []
        def item1 = "Item 1"
        def item2 = "Item 2"
        def item3 = "Item 3"
        searchResult.instanceList << item1
        searchResult.instanceList << item2
        searchResult.instanceList << item3

        when: // success
        def items = searchResult.getFirstItem()
        then:
        items == item1


        when: // error
        searchResult.totalCount = 0
        searchResult.instanceList = []
        items = searchResult.getFirstItem()
        then:
        items == null
    }
}
