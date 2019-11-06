package business

import bridge.User
import bridge.exception.AppBusinessException
import bridge.exception.AppException
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import grails.transaction.Transactional

@Transactional
class RepositoryService {

    def saveInstance(instance, boolean failOnError = false) {

        AppException.throwIfNull(instance, "Instance is required")

        if (instance.validate()) {
            return instance.save(failOnError: failOnError)
        }

        throw new AppBusinessException(instance.errors)
    }

    def findUserByUsernameEquals(String username) {
        if (!username) {
            return null
        }

        def searchParams = new SearchParams()
        searchParams.addFilter("usernameEq", username)

        return findUsers(searchParams).getFirstItem()
    }

    SearchResult findUsers(SearchParams searchParams = new SearchParams()) {

        def result = User.createCriteria().list(
                searchParams.paramsToPagination([
                        "name"
                ])
        ) {
            def usernameEq = searchParams?.getField("usernameEq")

            if (usernameEq) {
                eq 'username', usernameEq
            }
        }

        SearchResult searchResult = new SearchResult(searchParams)
        searchResult.instanceList = result
        searchResult.totalCount = result.totalCount

        return searchResult
    }
}