package bridge

import bridge.controllers.ControllerHelpers
import bridge.command.SearchCommand
import bridge.command.ShowCommand
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RolesController {

    def roleService

    def index(SearchCommand command) {
        respond ControllerHelpers.resolveResponseToGetWithMultipleResult(
                roleService.searchRoles(command)
        )
    }

    def show(ShowCommand command) {
        respond ControllerHelpers.resolveResponseToGetWithSingleResult(
                roleService.showRole(command)
        )
    }
}