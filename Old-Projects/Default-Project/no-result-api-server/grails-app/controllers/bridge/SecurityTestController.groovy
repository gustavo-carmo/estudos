package bridge

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
class SecurityTestController {

    static responseFormats = ['json']

    def index1() {
        respond "msg": "no ROLE"
    }

    @Secured(['permitAll'])
    def index2() {
        respond "msg" : "permitAll"
    }

    @Secured(['ROLE_USER'])
    def index3() {
        respond "msg" : "ROLE_USER"
    }
}
