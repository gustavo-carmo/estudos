package bridge.misc

import bridge.exception.AppValidationException

class SearchParams {

    Integer currentPage
    Integer itemsPerPage
    Map filters = [:]

    private String sort = "id"
    private String order = "asc"

    def addFilter(String field, Object value) {
        filters.put("${field}", value)
    }

    private Integer getOffset() {
        if (currentPage && itemsPerPage) {
            return (currentPage - 1) * itemsPerPage
        } else {
            return 0
        }
    }

    def getField(String field) {
        return this.filters.get("${field}")
    }

    def orderBy(String sort, String order) {

        if (!sort) {

            throw new AppValidationException("searchParams.sort.error.required")
        }

        this.sort = sort
        this.order = order ?: "asc"

    }

    def orderBy(String sort) {
        this.orderBy(sort, null)
    }

    def paramsToPagination(List fieldsToSort = []) {

        fieldsToSort << "id"

        if(!fieldsToSort.contains(sort)) {
            throw new AppValidationException("searchParams.sort.error.unknownField")
        }

        return [
                sort: sort,
                order: order,
                max: itemsPerPage,
                offset: offset
        ]
    }
}
