package bridge.misc

import bridge.exception.AppValidationException
import spock.lang.Specification
import spock.lang.Unroll

class SearchParamsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Devera retornar os valores para pesquisa"() {
        given:
        Integer currentPage = 1
        Integer itemsPerPage = 10
        String fieldFilter = "name"
        String fieldValue = "Example name"
        String sort = "name"
        String order = "desc"
        List fieldsToSort = [
                "name"
        ]
        SearchParams searchParams = new SearchParams(
                currentPage: currentPage,
                itemsPerPage: itemsPerPage
        )
        searchParams.addFilter(fieldFilter, fieldValue)
        searchParams.orderBy(sort, order)
        Map paramsToPagination

        when:
        paramsToPagination = searchParams.paramsToPagination(fieldsToSort)

        then:
        searchParams.filters != null
        searchParams.filters.keySet().size() == 1
        searchParams.filters.get("${fieldFilter}") == fieldValue
        paramsToPagination != null
        paramsToPagination == [
                sort: sort,
                order: order,
                max: itemsPerPage,
                offset: 0
        ]
    }

    @Unroll
    void "Devera calcular offset [#offset] quando currentPage [#currentPage] e itemsPerPage [#itemsPerPage]"() {
        given:
        SearchParams searchParams = new SearchParams()
        searchParams.currentPage = currentPage
        searchParams.itemsPerPage = itemsPerPage
        Map paramsToPagination

        when:
        paramsToPagination = searchParams.paramsToPagination()

        then:
        paramsToPagination.offset == offset

        where:
        currentPage | itemsPerPage | offset
        1           | 10           | 0
        1           | 53           | 0
        2           | 10           | 10
        3           | 20           | 40
        null        | null         | 0

    }

    void "Devera retornar os valores para a ordenação"() {

        given:
        String order = "asc"
        String sort = "name"
        SearchParams searchParams = new SearchParams()
        Map paramsToPagination

        when:
        paramsToPagination = searchParams.paramsToPagination()

        then:
        paramsToPagination.order == "asc"
        paramsToPagination.sort == "id"

        when:
        searchParams.orderBy(sort, order)
        paramsToPagination = searchParams.paramsToPagination(["name"])

        then:
        paramsToPagination.order == "asc"
        paramsToPagination.sort == "name"


        when:
        searchParams.orderBy(sort, null)

        then:
        paramsToPagination.order == "asc"
        paramsToPagination.sort == "name"
    }

    void "Devera retornar os valores para a ordenação somente com sort"() {

        given:
        String sort = "name"
        SearchParams searchParams = new SearchParams()
        Map paramsToPagination

        when:
        searchParams.orderBy(sort)
        paramsToPagination = searchParams.paramsToPagination(["name"])

        then:
        paramsToPagination.order == "asc"
        paramsToPagination.sort == "name"
    }

    void "Devera dar erro quando order não for informado"() {

        given:
        def ex
        SearchParams searchParams = new SearchParams()

        when:
        searchParams.orderBy(null, null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "searchParams.sort.error.required"
    }

    void "Devera dar erro quando tentar ordernar por um campo desconhecido"() {

        given:
        SearchParams searchParams = new SearchParams()
        def ex

        when:
        searchParams.orderBy("abobrinha")

        and:
        searchParams.paramsToPagination()

        then:
        ex = thrown(AppValidationException)
        ex.message == "searchParams.sort.error.unknownField"
    }


}
