package pa

class WkfSyncAllJob {

    //  TODO - TESTS
    def wkfSyncJobService
    def serviceOrdersService

    def concurrent = false

    static triggers = {
        cron name: 'wkfSyncAllJob',
                cronExpression: '0 0/10 * * * ?',
                timeZone:TimeZone.getTimeZone("America/Sao_Paulo") // every 15 minutes
    }

    void execute() {
        log.info("Start sync Customers with Wkf [${new Date()}]")
        wkfSyncJobService.loadTenantsAndSyncCustomersWithWkf()

        log.info("Start sync Service Orders with Wkf [${new Date()}]")
        serviceOrdersService.synchronize()

        log.info("Start load Tenants and sync with Wkf [${new Date()}]")
        wkfSyncJobService.loadTenantsAndSyncWithWkf()
    }
}
