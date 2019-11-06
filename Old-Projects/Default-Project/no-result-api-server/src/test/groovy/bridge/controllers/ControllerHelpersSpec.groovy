package bridge.controllers

import bridge.User
import bridge.controllers.ControllerHelpers
import bridge.exception.AppValidationException
import spock.lang.Specification
import bridge.controllers.EntityLocation
import bridge.misc.ResultService
import bridge.misc.SearchResult

class ControllerHelpersSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Should return response success (200) to GET with multiple result"() {
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
        response = ControllerHelpers.resolveResponseToGetWithMultipleResult(resultService)

        then:
        1 * resultService.isValid() >> true
        1 * resultService.getData() >> searchResultMocked
        1 * searchResultMocked.getInstanceList() >> [instance, instance]
        1 * searchResultMocked.pagination() >> ["total-count": 65]

        and:
        response.meta.code == 200
        response.keySet().size() == 3
        response.data.size() == 2
        response.data.collect { it instanceof EntityLocation }.size() == 2
        response.pagination == ["total-count": 65]


        when:
        ControllerHelpers.resolveResponseToGetWithMultipleResult(null)

        then:
        def ex = thrown(AppValidationException)
        ex.message == "param [resultService] is null"
    }

    void "Should return response success (200) to GET with single result"() {
        def response
        def resultService = Mock(ResultService)
        def instance = new User().with {
            id = 1
            version = 2
            username = 'john'
            return it
        }

        when:
        response = ControllerHelpers.resolveResponseToGetWithSingleResult(resultService)

        then:
        1 * resultService.isValid() >> true
        1 * resultService.getData() >> instance

        and:
        response.keySet().size() == 2
        response.meta.code == 200
        response.data == instance
    }

    void "Should return response success (201) to POST"() {
        def response
        def resultService = Mock(ResultService)
        def instance = new User().with {
            id = 1
            version = 2
            username = 'john'
            return it
        }

        when:
        response = ControllerHelpers.resolveResponseEntityReference(resultService, 201)

        then:
        1 * resultService.isValid() >> true
        1 * resultService.getData() >> instance

        and:
        response.keySet().size() == 2
        response.meta.code == 201
        response.data instanceof EntityLocation
    }

    void "Should return response success (200) to PUT"() {
        def response
        def resultService = Mock(ResultService)
        def instance = new User().with {
            id = 1
            version = 2
            username = 'john'
            return it
        }

        when:
        response = ControllerHelpers.resolveResponseEntityReference(resultService, 200)

        then:
        1 * resultService.isValid() >> true
        1 * resultService.getData() >> instance

        and:
        response.keySet().size() == 2
        response.meta.code == 200
        response.data instanceof EntityLocation
    }
}
