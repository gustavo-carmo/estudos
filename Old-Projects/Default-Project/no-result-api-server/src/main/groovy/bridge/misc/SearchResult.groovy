package bridge.misc

class SearchResult {

    static int PAGE_LIMIT = 100
    static int PAGE_FIRST = 1
    static int ITEMS_PER_PAGE = 25

    List instanceList
    Integer totalCount
    Integer currentPage
    Integer itemsPerPage

    SearchResult() {
        this.currentPage = PAGE_FIRST
        this.itemsPerPage = PAGE_LIMIT
    }

    SearchResult(SearchParams searchParams) {
        this.currentPage = searchParams.currentPage
        this.itemsPerPage = searchParams.itemsPerPage
    }

    Map pagination() {

        def pagination = [:]

        if (!this.totalCount) {
            return pagination
        }

        def calculateItemsPerPage = calcItemsPerPage(this.itemsPerPage)
        def totalPages = calcTotalPage(this.totalCount, calculateItemsPerPage)
        def calculateCurrentPage = calcCurrentPage(this.currentPage, totalPages)
        def pagePrevious = calcPrevious(calculateCurrentPage)
        def pageNext = calcNext(calculateCurrentPage, totalPages)

        pagination."totalCount" = this.totalCount
        pagination."totalPage" = totalPages
        pagination."pageFirst" = this.PAGE_FIRST
        pagination."pagePrevious" = pagePrevious
        pagination."page" = calculateCurrentPage
        pagination."pageNext" = pageNext
        pagination."pageLast" = totalPages
        pagination."perPage" = calculateItemsPerPage
        pagination."perPageLimit" = this.PAGE_LIMIT

        return pagination
    }

    private int calcItemsPerPage(Integer itemsPerPage) {
        if (!itemsPerPage) {
            itemsPerPage = ITEMS_PER_PAGE
        }
        return itemsPerPage <= PAGE_LIMIT ? itemsPerPage : PAGE_LIMIT
    }

    private int calcCurrentPage(Integer currentPage, int totalPages) {
        if (!currentPage) {
            currentPage = 1
        }
        return currentPage <= totalPages ? currentPage : 1
    }

    private int calcNext(int currentPage, int totalPages) {
        return currentPage < totalPages ? currentPage + 1 : 0
    }

    private int calcPrevious(int currentPage) {
        return currentPage > 1 ? currentPage - 1 : 0
    }

    private int calcTotalPage(int totalCount, int itemsPerPage) {
        if (totalCount%itemsPerPage) {
            return (int) Math.round(((double) totalCount / itemsPerPage) + 0.5d)
        } else {
            return (int) Math.round((double) totalCount / itemsPerPage)
        }
    }

    def getFirstItem() {
        if (totalCount) {
            return this.instanceList.get(0)
        }
    }
}
