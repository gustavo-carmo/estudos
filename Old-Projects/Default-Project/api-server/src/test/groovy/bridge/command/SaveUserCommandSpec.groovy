package bridge.command

import bridge.SaveUserCommand
import business.Customer
import business.enums.EnabledOrDisabled
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll
import bridge.utils.StringUtils

@TestMixin(ControllerUnitTestMixin)
class SaveUserCommandSpec extends Specification {

    def setup() {
        0 * _
    }

    def cleanup() {
    }

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new SaveUserCommand("$field": value)
        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field      | value                        | isValid | errorExpected
        "name"     | null                         | false   | "nullable"
        "name"     | ""                           | false   | "blank"
        "name"     | "nome valido"                | true    | null

        "username" | null                         | false   | "nullable"
        "username" | ""                           | false   | "blank"
        "username" | "username valido"            | true    | null

        "email"    | null                         | false   | "nullable"
        "email"    | ""                           | false   | "blank"
        "email"    | "email invalido"             | false   | "email.invalid"
        "email"    | "exemplo@hotmail"            | false   | "email.invalid"
        "email"    | "exemplo@hotmail.com"        | true    | null

        "password" | null                         | false   | "nullable"
        "password" | ""                           | false   | "blank"
        "password" | "1231"                       | false   | "minSize.notmet"
        "password" | StringUtils.randomString(21) | false   | "maxSize.exceeded"
        "password" | "password valido"            | true    | null

        "status"   | null                         | false   | "nullable"
        "status"   | EnabledOrDisabled.DISABLED   | true    | null

        "tenantId" | null                         | true    | null
        "tenantId" | 2                            | true    | null

        "customer" | null                         | true    | null
        "customer" | Mock(Customer)               | true    | null
    }
}
