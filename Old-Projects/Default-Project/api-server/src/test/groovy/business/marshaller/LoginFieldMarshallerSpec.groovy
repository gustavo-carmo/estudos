package business.marshaller

import business.enums.LoginField
import spock.lang.Specification

class LoginFieldMarshallerSpec extends Specification {

    def setup() {
        0 * _
    }

    def cleanup() {
    }

    void "Deverá fazer o marshaller do LoginField com sucesso"() {

        given:
        LoginField loginField = LoginField.DOCUMENT_NUMBER


        when:
        def result = LoginField.marshalObject(
                loginField
        )

        then:
        result == "DOCUMENT_NUMBER"

    }

    void "Deverá me retornar vazio quando não passar um objeto para o metodo"() {

        when:
        def result = LoginField.marshalObject(null)
        then:
        result == null
    }
}
