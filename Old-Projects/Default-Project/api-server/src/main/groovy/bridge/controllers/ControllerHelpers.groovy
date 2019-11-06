package bridge.controllers

import bridge.misc.ResultService
import bridge.exception.AppValidationException

class ControllerHelpers {

    private ControllerHelpers() {
    }

    static Map resolveResponse(ResultService resultService, ResponseErrorMode errorMode = ResponseErrorMode.ERROR_MODE_3) {

        if (!resultService) {
            throw new AppValidationException("param [resultService] is null")
        }

        if (resultService.isValid()) {
            return ControllerHelperResolveResponse.resolveResponseSuccess()
        } else {
            return ControllerHelperResolveResponse.resolveError(resultService.errorMessage,
                    resultService.fieldErrors, errorMode)
        }
    }

    static Map resolveResponseToGetWithMultipleResult(ResultService resultService,
                                                      ResponseErrorMode errorMode = ResponseErrorMode.ERROR_MODE_3) {

        if (!resultService) {
            throw new AppValidationException("param [resultService] is null")
        }

        if (resultService.isValid()) {
            return ControllerHelperResolveResponse.resolveResponseSuccessMultipleEntityReference(
                    resultService)
        } else {
            return ControllerHelperResolveResponse.resolveError(
                    resultService.errorMessage, resultService.fieldErrors, errorMode)
        }
    }

//    TODO - TESTES
    static Map resolveResponseToGetWithMultipleResultAndReturnObjectDescription(ResultService resultService,
                                                      ResponseErrorMode errorMode = ResponseErrorMode.ERROR_MODE_3) {
        if (!resultService) {
            throw new AppValidationException("param [resultService] is null")
        }

        if (resultService.isValid()) {
            return ControllerHelperResolveResponse.resolveResponseSuccessMultipleObjectDescription(
                    resultService)
        } else {
            return ControllerHelperResolveResponse.resolveError(
                    resultService.errorMessage, resultService.fieldErrors, errorMode)
        }
    }

    static Map resolveResponseToGetWithSingleResult(ResultService resultService,
                                                    ResponseErrorMode errorMode = ResponseErrorMode.ERROR_MODE_3) {
        if (!resultService) {
            throw new AppValidationException("param [resultService] is null")
        }

        if (resultService.isValid()) {
            return ControllerHelperResolveResponse.resolveResponseSuccessEntity(
                    resultService.data)
        } else {
            return ControllerHelperResolveResponse.resolveError(
                    resultService.errorMessage, resultService.fieldErrors, errorMode)
        }
    }

    static Map resolveResponseEntityReference(ResultService resultService, Integer code,
                                              ResponseErrorMode errorMode = ResponseErrorMode.ERROR_MODE_3) {

        if (!resultService) {
            throw new AppValidationException("param [resultService] is null")
        }

        if (resultService.isValid()) {
            return ControllerHelperResolveResponse.resolveResponseSuccessEntityReference(resultService.data, code)
        } else {
            return ControllerHelperResolveResponse.resolveError(
                    resultService.errorMessage, resultService.fieldErrors, errorMode)
        }
    }
}
