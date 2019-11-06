package business.controllers

import business.ConfigCommand
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin(ControllerUnitTestMixin)
class ConfigCommandSpec extends Specification {

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new ConfigCommand("$field": value)
        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field | value                     | isValid | errorExpected
        "url" | null                      | false   | "nullable"
        "url" | ""                        | false   | "blank"
        "url" | "http://api.com.br/wkf-1" | true    | null
    }
}
