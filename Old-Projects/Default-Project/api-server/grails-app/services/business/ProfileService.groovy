package business

import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProfileService {

    def springSecurityService
    
    String resolveEmail() {
        return springSecurityService.getCurrentUser()?.email
    }

    String resolveName() {
        return springSecurityService.getCurrentUser()?.name
    }

}
