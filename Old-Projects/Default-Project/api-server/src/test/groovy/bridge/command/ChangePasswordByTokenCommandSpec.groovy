package bridge.command

import business.ChangePasswordByTokenCommand
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin(ControllerUnitTestMixin)
class ChangePasswordByTokenCommandSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new ChangePasswordByTokenCommand("$field": value)

        when:
        isValid = instance.validate([field])

        then:
        errorExpected == instance.errors["$field"]?.code

        where:
        field      | value | isValid | errorExpected
        "token"    | null  | false   | "nullable"
        "token"    | ""    | false   | "blank"
        "password" | null  | false   | "nullable"
        "password" | ""    | false   | "blank"
    }
}
