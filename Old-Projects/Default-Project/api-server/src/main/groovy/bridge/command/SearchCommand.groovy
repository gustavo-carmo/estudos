package bridge.command

import grails.validation.Validateable

class SearchCommand implements Validateable {

    Integer currentPage = 1
    Integer itemsPerPage = 25
    Integer ITEMS_PER_PAGE_LIMIT = 100

    def setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = (itemsPerPage <= ITEMS_PER_PAGE_LIMIT) ?
                itemsPerPage : ITEMS_PER_PAGE_LIMIT
    }

    static constraints = {
        currentPage nullable: true
        itemsPerPage nullable: true
    }
}
