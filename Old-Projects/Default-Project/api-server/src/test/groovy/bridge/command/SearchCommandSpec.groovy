package bridge.command

import spock.lang.Specification

class SearchCommandSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Devera retornar os valores default quando nenhum valor é informado"() {
        given:
        Integer currentPageDefault = 1
        Integer itemsPerPageDefault = 25
        SearchCommand searchCommand

        when:
        searchCommand = new SearchCommand()

        then:
        searchCommand.currentPage == currentPageDefault
        searchCommand.itemsPerPage == itemsPerPageDefault
    }

    void "Devera retornar os valores informados quando são valores válidos"() {
        given:
        Integer currentPage = 3
        Integer itemsPerPage = 10
        SearchCommand searchCommand

        when:
        searchCommand = new SearchCommand(
                currentPage: currentPage,
                itemsPerPage: itemsPerPage
        )

        then:
        searchCommand.currentPage == currentPage
        searchCommand.itemsPerPage == itemsPerPage
    }

    void "Devera retornar itemsPerPageLimit default quando itemsPerPage é um valor inválido"() {
        given:
        Integer currentPage = 5
        Integer itemsPerPage = 101
        Integer itemsPerPageLimit = 100
        SearchCommand searchCommand

        when:
        searchCommand = new SearchCommand(
                currentPage: currentPage,
                itemsPerPage: itemsPerPage
        )

        then:
        searchCommand.currentPage == currentPage
        searchCommand.itemsPerPage == itemsPerPageLimit
    }
}
