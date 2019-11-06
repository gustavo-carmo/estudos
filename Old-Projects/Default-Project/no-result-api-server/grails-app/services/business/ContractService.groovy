package business

import bridge.misc.SearchResult
import business.enums.DatabaseDriverType
import grails.transaction.Transactional

@Transactional
class ContractService {

    def repositoryService
    def deployService

    SearchResult searchContracts() {

        SearchResult searchResult = new SearchResult()
        def list = Contract.list()

        searchResult.instanceList = list
        searchResult.totalCount = list.size()

        return searchResult
    }

    Contract saveContract(Contract contract) {

        contract.databaseDriver = DatabaseDriverType.MYSQL

        return repositoryService.saveInstance(contract)
    }
}
