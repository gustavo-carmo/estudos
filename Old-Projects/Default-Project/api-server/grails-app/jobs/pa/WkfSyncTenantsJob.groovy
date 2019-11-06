package pa

import groovy.util.logging.Log4j

@Log4j
class WkfSyncTenantsJob {

    def concurrent = false

    def wkfSyncJobService

    static triggers = {
        cron name: 'wkfSyncTenantsJob',
                cronExpression: '0 0/30 * * * ?',
                timeZone:TimeZone.getTimeZone("America/Sao_Paulo") // nos minutos 0 e 30 de cada hora
    }

    void execute() {
        log.info("Start load Tenants and sync with Wkf [${new Date()}]")
//        wkfSyncJobService.loadTenantsAndSyncWithWkf()
    }
}
