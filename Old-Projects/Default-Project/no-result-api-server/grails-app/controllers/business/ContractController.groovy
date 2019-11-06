package business

import bridge.controllers.ControllerHelpers
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
@Transactional(readOnly = true)
class ContractController {

    static responseFormats = ['json']

    def contractService

    def index() {
        respond ControllerHelpers.resolveResponseSuccessListEntity(
                contractService.searchContracts()
        )
    }

    def show(String id) {
        respond ControllerHelpers.resolveResponseSuccessEntity(
                Contract.get(id)
        )
    }

    @Transactional(readOnly = false)
    def save(Contract contract) {

        respond ControllerHelpers.resolveResponseSuccessEntity(
                contractService.saveContract(contract)
        )
    }

    @Transactional(readOnly = false)
    def update(Contract contract) {
        respond ControllerHelpers.resolveResponseSuccessEntity(
                contractService.saveContract(contract)
        )
    }

    def deploy(String id) {
        respond ControllerHelpers.resolveResponseSuccessEntity(
                contractService.deploy(id)
        )
    }
}
