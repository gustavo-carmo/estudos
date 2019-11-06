package pa

class WkfSyncCustomersJob {

//  TODO - TESTS
    def wkfSyncJobService

    def concurrent = false

    static triggers = {
        cron name: 'wkfSynCustomersJob',
                cronExpression: '0 0/5 * * * ?',
                timeZone:TimeZone.getTimeZone("America/Sao_Paulo") // every 5 minutes
    }

    void execute() {
        log.info("Start sync Customers with Wkf [${new Date()}]")
//        wkfSyncJobService.loadTenantsAndSyncCustomersWithWkf()
    }
}
