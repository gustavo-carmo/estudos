package bridge

import bridge.controllers.ControllerHelperResolveResponse
import bridge.exception.AppBusinessException
import bridge.exception.AppException
import grails.converters.*

class ErrorController {

    def handleAppException() {

        response.status = 200

        render ControllerHelperResolveResponse.resolveError(
                request.exception.cause.targetException as AppException
        ) as JSON
    }

    def handleAppBusinessException() {

        response.status = 200

        render ControllerHelperResolveResponse.resolveError(
                request.exception.cause.targetException as AppBusinessException
        ) as JSON
    }
}
