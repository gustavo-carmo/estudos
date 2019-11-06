package bridge

import bridge.controllers.ControllerHelpers
import bridge.controllers.ResponseErrorMode
import bridge.misc.ErrorMessage
import bridge.misc.ResultService

/*
Apenas uma classe de exemplo
*/

class ExampleController {

    static responseFormats = ['json']

    def GET_multiple() {

        def resultService = new ResultService()

        resultService.data.instanceList = []

        def user = new User().with {
            id = 1
            version = 2
            username = "example..."
            return it
        }

        resultService.data.instanceList << user
        resultService.data.instanceList << user


        resultService.data.totalCount = 10
        resultService.data.currentPage = 1
        resultService.data.itemsPerPage = 25
        resultService.instanceList << user
        resultService.instanceList << user

        resultService.pagination = [
                "totalCount"   : 65,
                "totalPage"    : 7,
                "pageFirst"    : 1,
                "pagePrevious" : 3,
                "page"          : 4,
                "pageNext"     : 5,
                "pageLast"     : 7,
                "perPage"      : 10,
                "perPageLimit": 100
        ]
        respond ControllerHelpers.resolveResponseToGetWithMultipleResult(resultService)
    }

    def GET_single() {

        def resultService = new ResultService()

        resultService.instance = new User().with {
            id = 1
            version = 2
            username = "example..."
            return it
        }

        respond ControllerHelpers.resolveResponseToGetWithSingleResult(resultService)
    }

    def POST() {

        def resultService = new ResultService()

        resultService.instance = new User().with {
            id = 1
            version = 2
            username = "example..."
            return it
        }

        respond ControllerHelpers.resolveResponseEntityReference(resultService, 201)
    }

    def PUT() {

        def resultService = new ResultService()

        resultService.instance = new User().with {
            id = 1
            version = 2
            username = "example..."
            return it
        }

        respond ControllerHelpers.resolveResponseEntityReference(resultService, 200)
    }

    def DELETE() {

        def resultService = new ResultService()
        respond ControllerHelpers.resolveResponse(resultService)
    }

    def ERROR_MODE_1(ExampleCommand exampleCommand) {

        def resultService = new ResultService()
        resultService.errorMessage = ErrorMessage.build("Exemplo de erro, o correto é utilizar um 'code' que irá buscar no messages.code")
        resultService.addValidationErrors(exampleCommand.errors)
        respond ControllerHelpers.resolveResponseToGetWithSingleResult(resultService, ResponseErrorMode.ERROR_MODE_1)
    }

    def ERROR_MODE_2() {

        def resultService = new ResultService()
        resultService.errorMessage = ErrorMessage.build("Exemplo de erro, o correto é utilizar um 'code' que irá buscar no messages.code")
        respond ControllerHelpers.resolveResponseToGetWithSingleResult(resultService, ResponseErrorMode.ERROR_MODE_2)
    }

    def ERROR_MODE_3(ExampleCommand exampleCommand) {

        def resultService = new ResultService()
        resultService.addValidationErrors(exampleCommand.errors)
        respond ControllerHelpers.resolveResponseToGetWithSingleResult(resultService, ResponseErrorMode.ERROR_MODE_3)
    }

    def date(ExampleCommand command) {

        println 'params'
        println params

        println 'command.minhaData'
        println command.minhaData

        println 'command.minhaData2'
        println command.minhaData2


        render command
    }
}

class ExampleCommand {

    String email
    Date minhaData
    Date minhaData2

    static constraints = {
        email nulllable: false, blank: false
    }
}
