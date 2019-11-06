package business.serviceOrders

import bridge.misc.SearchResult
import groovy.util.logging.Log4j

@Log4j
class WkfSyncServiceHelpers {

    static List extractIdAndWkfIdFromSearchResult(SearchResult searchResult) {

        log.info("extractIdAndWkfIdFromSearchResult")

        if (!searchResult) {
            log.info("searchResult not found")
            return []
        }

        def ret = searchResult.instanceList.collect {
            [id: Long.valueOf(it.id), wkfId: Long.valueOf(it.wkfId), statusWkf: String.valueOf(it.statusWkf)]
        }

        log.info("return [$ret]")

        return ret
    }

    static List itemsThatWillBeCreatedInTheSupportPortal(wkfItems, supportPortalItems) {

        log.info("itemsThatWillBeCreatedInTheSupportPortal")

        def wkfEnabled = []

        if (!wkfItems && !supportPortalItems) {
            log.info("wkfItems and supportPortalItems not found")
            return wkfEnabled
        }

        if (wkfItems) {

            def paWkfIds = supportPortalItems.collect { it.wkfId }
            log.info("paWkfIds add supportPortalItems, list of ids")

            wkfItems.each { item ->
                if (String.valueOf(item.status) == 'ENABLED' && !paWkfIds.contains(item.id)) {
                    wkfEnabled.push([
                            wkfId: item.id,
                            name : item.name
                    ])
                }
            }
        }

        log.info("return wkfEnabled [$wkfEnabled]")
        return wkfEnabled
    }

    static List itemsToBeDisabledInSupportPortal(wkfItems, supportPortalItems) {

        log.info("itemsToBeDisabledInSupportPortal")
        if (wkfItems && supportPortalItems) {
            log.info("wkfItems and supportPortalItems exist")

            def wkfNotEnabled = wkfItems.findAll { it.status != 'ENABLED' }.collect { it.id }
            log.info("wkfNotEnabled [$wkfNotEnabled]")

            def ret = supportPortalItems.findAll { wkfNotEnabled.contains(it.wkfId) }.collect { it.id }

            log.info("return $ret")
            return ret
        }

        return []
    }

    static List itemsToBeReenabledInSupportPortal(wkfItems, supportPortalItems) {

        log.info("itemsToBeReenabledInSupportPortal")
        if (wkfItems && supportPortalItems) {
            log.info("wkfItems and supportPortalItems exist")

            def wkfEnabled = wkfItems.findAll { it.status == 'ENABLED' }.collect { it.id }
            log.info("wkfEnabled [$wkfEnabled]")

            def ret = supportPortalItems.findAll { it.statusWkf != 'ENABLED'}
                    .findAll { wkfEnabled.contains(it.wkfId) }.collect { it.id }

            log.info("return $ret")
            return ret
        }

        return []
    }

    static List itemsNotFoundInTheWkf(wkfItems, supportPortalItems) {

        log.info("itemsNotFoundInTheWkf")
        if (supportPortalItems) {
            log.info("supportPortalItems exist")

            def idsWkf = wkfItems.collect { it.id }
            log.info("idsWkf $idsWkf")
            def ret = supportPortalItems.findAll {
                !idsWkf.contains(it.wkfId)
            }.collect { it.id }

            log.info("return ret")
            return ret
        }

        return []
    }
}
