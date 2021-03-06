package bridge.command

import business.ResetPasswordCommand
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin(ControllerUnitTestMixin)
class ResetPasswordCommandSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new ResetPasswordCommand("$field": value)
        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field      | value             | isValid | errorExpected
        "username" | null              | false   | "nullable"
        "username" | ""                | false   | "blank"
        "username" | "username valido" | true    | null
    }
}
