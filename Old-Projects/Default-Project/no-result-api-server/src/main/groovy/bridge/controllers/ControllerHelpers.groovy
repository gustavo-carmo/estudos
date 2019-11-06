package bridge.controllers

import bridge.exception.AppException
import bridge.exception.AppValidationException
import bridge.misc.ResultService
import bridge.misc.SearchResult

class ControllerHelpers {

    private static CODE_SUCCESS = 200

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


    static Map resolveResponseSuccessEntity(instance, Integer code = null) {


        AppException.throwIfNull(instance, "Instance is required")

        def response = [:]
        response.meta = [:]
        response.meta.code = code ?: CODE_SUCCESS
        response.data = instance
        return response
    }

    // TODO - tests
    static Map resolveResponseSuccessListEntity(SearchResult paramSearchResult) {

        if (paramSearchResult == null) {
            throw new AppException("param [paramSearchResult] is required")
        }

        Map response = [:]
        response.meta = [:]
        response.meta.code = CODE_SUCCESS
        response.pagination = paramSearchResult.pagination()
        response.data = paramSearchResult.instanceList

        return response
    }
}
