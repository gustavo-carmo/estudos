package bridge

import bridge.exception.AppValidationException
import bridge.misc.ResultService
import bridge.command.SearchCommand
import bridge.misc.SearchParams
import bridge.command.ShowCommand
import grails.transaction.Transactional

@Transactional
class RoleService {

    def repositoryService
    def grailsHelperService

    ResultService searchRoles(SearchCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        SearchParams searchParams = new SearchParams(
                currentPage: command.currentPage,
                itemsPerPage: command.itemsPerPage
        )

        resultService.data = repositoryService.findRoles(searchParams)

        return resultService
    }

    ResultService showRole(ShowCommand command) {

        if (command == null) {
            throw new AppValidationException("commandGeneric.error.required")
        }

        def resultService = new ResultService()

        if (!command.validate()) {
            resultService.addValidationErrors(command.errors)
            return resultService
        }

        def id = command.id

        def role = repositoryService.findRoleById(id)

        if (!role) {
            resultService.addValidationErrors(
                    grailsHelperService.resolveValidationErrors(
                            command,
                            "id",
                            "default.not.found.message",
                            ["ShowCommand class", "id"]
                    )
            )
        }

        resultService.data = role

        return resultService
    }
}
