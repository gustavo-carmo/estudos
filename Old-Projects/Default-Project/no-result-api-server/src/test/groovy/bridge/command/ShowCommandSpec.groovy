package bridge.command

import spock.lang.Specification
import spock.lang.Unroll

class ShowCommandSpec extends Specification {

    @Unroll
    def "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {

        given:
        def instance = new ShowCommand("$field": value)
        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field | value | isValid | errorExpected
        "id"  | null  | false   | "nullable"
        "id"  | 3     | true    | null
    }
}
