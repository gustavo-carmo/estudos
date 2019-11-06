package business.marshaller

import business.enums.StatusWkf
import spock.lang.Specification

class StatusWkfMarshallerSpec extends Specification {
    def setup() {
        0 * _
    }

    def cleanup() {
    }

    void "Deverá fazer o marshaller do StatusWkf com sucesso"() {

        given:
        StatusWkf statusWkf = StatusWkf.DISABLED


        when:
        def result = StatusWkf.marshalObject(
                statusWkf
        )

        then:
        result == "DISABLED"

    }

    void "Deverá me retornar vazio quando não passar um objeto para o metodo"() {

        when:
        def result = StatusWkf.marshalObject(null)
        then:
        result == null
    }
}
