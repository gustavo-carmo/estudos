package business.marshaller

import business.enums.EnabledOrDisabled
import spock.lang.Specification

class EnabledOrDisabledMarshallerSpec extends Specification {

    def setup() {
        0 * _
    }

    def cleanup() {
    }

    void "Deverá fazer o marshaller do EneabledOrDisabled com sucesso"() {

        given:
        EnabledOrDisabled enabledOrDisabled = EnabledOrDisabled.DISABLED


        when:
        def result = EnabledOrDisabled.marshalObject(
                enabledOrDisabled
        )

        then:
        result == "DISABLED"

    }

    void "Deverá me retornar vazio quando não passar um objeto para o metodo"() {

        when:
        def result = EnabledOrDisabled.marshalObject(null)
        then:
        result == null
    }
}
