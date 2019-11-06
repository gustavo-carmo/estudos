package bridge.command

import bridge.SearchUserCommand
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin(ControllerUnitTestMixin)
class SearchUserCommandSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new SearchUserCommand("$field": value)
        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field          | value                      | isValid | errorExpected
        "name"         | null                       | true    | null
        "name"         | "nome valido"              | true    | null

        "username"     | null                       | true    | null
        "username"     | "username valido"          | true    | null

        "status"       | null                       | true    | null
        "status"       | EnabledOrDisabled.DISABLED | true    | null

        "tenantId"     | null                       | true    | null
        "tenantId"     | 8                          | true    | null

        "currentPage"  | null                       | true    | null
        "currentPage"  | 3                          | true    | null

        "itemsPerPage" | null                       | true    | null
        "itemsPerPage" | 3                          | true    | null
    }
}
