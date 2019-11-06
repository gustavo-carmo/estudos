package bridge.controllers

import bridge.User
import bridge.controllers.ControllerHelperResolveResponse
import bridge.controllers.ResponseErrorMode
import bridge.exception.AppValidationException
import spock.lang.Specification
import bridge.controllers.EntityLocation
import bridge.misc.ErrorMessage
import bridge.misc.ResultService
import bridge.misc.SearchResult

class ControllerHelperResolveResponseSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Should resolve response success"() {
        given:
        def response

        when:
        response = ControllerHelperResolveResponse.resolveResponseSuccess()

        then:
        response.keySet().size() == 1
        response.meta == [code: 200]
    }

    void "Should resolve response success entity"() {
        given:
        def response
        def instance = Mock(User)

        when:
        response = ControllerHelperResolveResponse.resolveResponseSuccessEntity(instance)

        then:
        response.keySet().size() == 2
        response.meta == [code: 200]
        response.data == instance
    }

    void "Should be invalid to resolve response success entity"() {
        given:
        def ex

        when:
        ControllerHelperResolveResponse.resolveResponseSuccessEntity(null)

        then:
        ex = thrown(AppValidationException)

        and:
        ex.message == "param [instance] is required"
    }

    void "Should resolve response success entity reference"() {
        given:
        def response
        def instance = new User().with {
            id = 1
            version = 2
            username = 'john'
            return it
        }

        when:
        response = ControllerHelperResolveResponse.resolveResponseSuccessEntityReference(instance)

        then:
        response.keySet().size() == 2
        response.meta == [code: 200]
        response.data instanceof EntityLocation

        when:
        response = ControllerHelperResolveResponse.resolveResponseSuccessEntityReference(instance, 200)

        then:
        response.keySet().size() == 2
        response.meta == [code: 200]
        response.data instanceof EntityLocation


        when:
        response = ControllerHelperResolveResponse.resolveResponseSuccessEntityReference(instance, 201)

        then:
        response.keySet().size() == 2
        response.meta == [code: 201]
        response.data instanceof EntityLocation
    }

    void "Should be invalid to resolve response success entity reference"() {
        given:
        def ex
        def instance = new User().with {
            id = 1
            version = 2
            username = 'john'
            return it
        }

        when:
        ControllerHelperResolveResponse.resolveResponseSuccessEntityReference(null)

        then:
        ex = thrown(AppValidationException)

        and:
        ex.message == "param [instance] is required"


        when:
        ControllerHelperResolveResponse.resolveResponseSuccessEntityReference(instance, 202)

        then:
        ex = thrown(AppValidationException)

        and:
        ex.message == 'param [code] is invalid, valid codes [200, 201]'
    }

    void "Should resolve response success multiple entity reference"() {
        given:
        def response
        def resultService = Mock(ResultService)
        def searchResultMocked = Mock(SearchResult)
        def instance = new User().with {
            id = 1
            version = 2
            username = 'john'
            return it
        }

        when:
        response = ControllerHelperResolveResponse.resolveResponseSuccessMultipleEntityReference(resultService)

        then:
        1 * resultService.getData() >> searchResultMocked
        1 * searchResultMocked.getInstanceList() >> [instance, instance]
        1 * searchResultMocked.pagination() >> [:]

        and:
        response.keySet().size() == 3
        response.meta == [code: 200]
        response.data.size() == 2
        response.data.collect { it instanceof EntityLocation }.size() == 2
    }

    void "Should be invalid to resolve response success multiple entity reference"() {
        given:
        def ex

        when:
        ControllerHelperResolveResponse.resolveResponseSuccessMultipleEntityReference(null)

        then:
        ex = thrown(AppValidationException)

        and:
        ex.message == "param [resultService] is required"
    }

    void "Should resolve response error (400) to ERROR_MODE_1"() {
        given:
        def response
        def errorMessage = GroovyMock(ErrorMessage)
        def fieldErrors = ["Error message 1"]

        when:
        response = ControllerHelperResolveResponse.resolveError(errorMessage, fieldErrors,
                ResponseErrorMode.ERROR_MODE_1)

        then:
        response != null
        response.keySet().size() == 3
        response.meta.code == 400
        response.message == errorMessage
        response.errors == ["Error message 1"]
    }

    void "Should resolve response error (400) to ERROR_MODE_2"() {
        given:
        def response
        def errorMessage = GroovyMock(ErrorMessage)

        when:
        response = ControllerHelperResolveResponse.resolveError(errorMessage, null,
                ResponseErrorMode.ERROR_MODE_2)

        then:
        response != null
        response.keySet().size() == 2
        response.meta.code == 400
        response.message == errorMessage
    }

    void "Should resolve response error (400) to ERROR_MODE_3 (default)"() {
        given:
        def response
        def fieldErrors = ["Error message 1"]

        when:
        response = ControllerHelperResolveResponse.resolveError(null, fieldErrors)

        then:
        response != null
        response.keySet().size() == 2
        response.meta.code == 400
        response.errors == ["Error message 1"]

        when:
        response = ControllerHelperResolveResponse.resolveError(null, fieldErrors,
                ResponseErrorMode.ERROR_MODE_3)

        then:
        response != null
        response.keySet().size() == 2
        response.meta.code == 400
        response.errors == ["Error message 1"]
    }

    void "Should be invalid to resolve response error (400) with invalid params"() {
        given:
        def ex

        when:
        ControllerHelperResolveResponse.resolveError(null, null)

        then:
        ex = thrown(AppValidationException)

        and:
        ex.message == 'param [errorMessage] or [fieldErrors] must be informed'
    }
}
