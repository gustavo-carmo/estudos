package bridge.controllers

import bridge.exception.AppBusinessException
import bridge.exception.AppException
import bridge.exception.AppValidationException
import bridge.misc.ErrorMessage
import bridge.misc.ResultService
import bridge.misc.SearchResult

class ControllerHelperResolveResponse {

    private static CODE = "code"
    private static CODE_SUCCESS = 200
    private static CODE_SUCCESS_CREATED = 201
    private static CODE_ERROR = 400
    private static CODE_ERROR_BUSINESS = 400
    private static CODE_ERROR_SERVER = 500

    private ControllerHelperResolveResponse() {
    }

    static Map resolveResponseSuccess() {
        def response = [:]
        response.meta = [:]
        response.meta."${CODE}" = CODE_SUCCESS
        return response
    }

    static Map resolveResponseSuccessEntity(instance) {

        if (instance == null) {
            throw new AppValidationException("param [instance] is required")
        }

        def response = [:]
        response.meta = [:]
        response.meta."${CODE}" = CODE_SUCCESS
        response.data = instance
        return response
    }

    static Map resolveResponseSuccessEntityReference(instance, Integer code = null) {

        def validSuccessCode = [CODE_SUCCESS, CODE_SUCCESS_CREATED]
        if (code && !validSuccessCode.contains(code)) {
            throw new AppValidationException("param [code] is invalid, valid codes $validSuccessCode")
        }

        if (instance == null) {
            throw new AppValidationException("param [instance] is required")
        }

        def response = [:]
        response.meta = [:]
        response.meta."${CODE}" = code ?: CODE_SUCCESS
        response.data = EntityLocation.build(instance.id, instance.version, instance)
        return response
    }

    static Map resolveResponseSuccessMultipleEntityReference(ResultService resultService) {

        if (resultService == null) {
            throw new AppValidationException("param [resultService] is required")
        }

        Map response = [:]
        response.meta = [:]
        response.meta."${CODE}" = CODE_SUCCESS
        response.data = []

        SearchResult searchResult = resultService.data

        searchResult.instanceList.each {
            response.data << EntityLocation.build(it.id, it.version, it)
        }

        response.pagination = searchResult.pagination()

        return response
    }

//    TODO - TESTES
    static Map resolveResponseSuccessMultipleObjectDescription(ResultService resultService) {

        if (resultService == null) {
            throw new AppValidationException("param [resultService] is required")
        }

        Map response = [:]
        response.meta = [:]
        response.meta."${CODE}" = CODE_SUCCESS
        response.data = []

        SearchResult searchResult = resultService.data

        searchResult.instanceList.each {
            response.data << it.getObjectDescription()
        }

        response.pagination = searchResult.pagination()

        return response
    }

    static Map resolveError(ErrorMessage errorMessage,
                            List fieldErrors,
                            ResponseErrorMode errorMode = ResponseErrorMode.ERROR_MODE_3) {

        if (errorMessage == null && fieldErrors == null) {
            throw new AppValidationException("param [errorMessage] or [fieldErrors] must be informed")
        }

        Map response = [:]
        response.meta = [:]
        response.meta."${CODE}" = CODE_ERROR

        if ([ResponseErrorMode.ERROR_MODE_1,
             ResponseErrorMode.ERROR_MODE_2].contains(errorMode) && errorMessage != null) {
            response.message = errorMessage
        }

        if ([ResponseErrorMode.ERROR_MODE_1,
             ResponseErrorMode.ERROR_MODE_3].contains(errorMode)) {
            response.errors = fieldErrors
        }

        return response
    }

    static Map resolveError(AppException exception) {

        Map response = [:]

        response.meta = [:]
        response.meta."${CODE}" = CODE_ERROR_SERVER
        response.error = exception.message

        return response
    }

    static Map resolveError(AppBusinessException appValidationException) {

        Map response = [:]

        response.meta = [:]
        response.meta."${CODE}" = CODE_ERROR_BUSINESS
        response.errors = appValidationException.errorMessages

        return response
    }
}
