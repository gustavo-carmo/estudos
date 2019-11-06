package bridge

import grails.transaction.Transactional

@Transactional(readOnly = true)
class AppConfigService {

    def propertiesService

    // TODO - tests
    def resolveAdminUrl() {
        return propertiesService.adminUr
    }

    // TODO - tests
    def resolveAdminLoginField() {
        return propertiesService.adminLoginField
    }
}
