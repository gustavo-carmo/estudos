package bridge.command

import bridge.UpdateUserCommand
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll
import bridge.utils.StringUtils

@TestMixin(ControllerUnitTestMixin)
class UpadateUserCommandSpec extends Specification {

    def setup() {
        0 * _
    }

    def cleanup() {
    }

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new UpdateUserCommand("$field": value)
        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field      | value                        | isValid | errorExpected
        "id"       | null                         | false   | "nullable"
        "id"       | 64                           | true    | null

        "name"     | null                         | false   | "nullable"
        "name"     | ""                           | false   | "blank"
        "name"     | "username valido"            | true    | null

        "username" | null                         | false   | "nullable"
        "username" | ""                           | false   | "blank"
        "username" | "username valido"            | true    | null

        "email"    | null                         | false   | "nullable"
        "email"    | ""                           | false   | "blank"
        "email"    | "email invalido"             | false   | "email.invalid"
        "email"    | "exemplo@hotmail.com"        | true    | null

        "password" | null                         | true    | null
        "password" | ""                           | false   | "blank"
        "password" | "1231"                       | false   | "minSize.notmet"
        "password" | StringUtils.randomString(21) | false   | "maxSize.exceeded"
        "password" | "password valido"            | true    | null

        "status"   | null                         | false   | "nullable"
        "status"   | EnabledOrDisabled.DISABLED   | true    | null
    }
}