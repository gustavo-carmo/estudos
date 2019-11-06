package bridge.services

import bridge.UtilService
import grails.test.mixin.TestFor
import org.grails.web.json.JSONElement
import org.grails.web.json.JSONObject
import spock.lang.Ignore
import spock.lang.Specification

@TestFor(UtilService)
class UtilServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Ignore
    void "Deverá fazer uma chamada GET com sucesso"() {
        given:
        String url = "http://echo.jsontest.com/nomeDaChave/valorDaChave/outraChave/umOutroValor"

        when:
        JSONElement ret = service.httpGetAndReturnJSON(url, null)

        then:
        ret != null
        ret.outraChave == "umOutroValor"
        ret.nomeDaChave == "valorDaChave"
    }

    @Ignore
    void "Deverá fazer uma chamada POST com sucesso"() {
        given:
        String url = "http://localhost:8080/osmanager-tenant/api/serviceOrders"

        when:
        Map ret = (Map) service.httpPostWithJsonDataAndReturnJSON(url, [
                "serviceOfferedId": 123,
                "customerId"      : 38071,
                "userId"          : 1,
                "defectId"        : 18,
                "notes"           : "bla bla bla bla"
        ])

        then:
        ret != null
        ret.meta.code == 201
        ret.data.code != null
    }
}

