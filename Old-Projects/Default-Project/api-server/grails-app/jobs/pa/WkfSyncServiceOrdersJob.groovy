package pa

class WkfSynServiceOrdersJob {

    // TODO - tests
    def serviceOrdersService

    def concurrent = false

    static triggers = {
        cron name: 'wkfSynServiceOrdersJob',
                cronExpression: '0 0/5 * * * ?',
                timeZone:TimeZone.getTimeZone("America/Sao_Paulo") // every 5 minutes
    }

    void execute() {
        log.info("Start sync Service Orders with Wkf [${new Date()}]")
//        serviceOrdersService.synchronize()
    }
}
