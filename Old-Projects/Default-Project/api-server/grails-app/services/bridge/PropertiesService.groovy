package bridge

import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Value

@Transactional(readOnly = true)
class PropertiesService {

    // TODO - migrar essas configs para um PropertiesService
    @Value('${adminUrl:http://localhost:8090}')
    String adminUr

    @Value('${adminLoginField:USERNAME}')
    String adminLoginField
}
