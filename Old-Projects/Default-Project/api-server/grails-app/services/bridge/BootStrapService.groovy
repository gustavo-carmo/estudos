package bridge

import bridge.marshaller.ErrorMessageCustomMarshaller
import bridge.marshaller.FieldErrorCustomMarshaller
import bridge.misc.ErrorMessage
import bridge.misc.ObjectDescriptionBuilt
import business.enums.DocumentType
import business.enums.EnabledOrDisabled
import business.enums.LoginField
import business.enums.ServiceOrderStatus
import business.enums.StatusWkf
import business.enums.YesOrNot
import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Environment
import groovy.util.logging.Log4j
import org.springframework.validation.FieldError

@Log4j
@Transactional
class BootStrapService {

    def messageSource
    def appConfigService

    def init() {

        log.info("Load common data start")
        loadCommonData()
        log.info("Load common data finish")

        log.info("[APP Config] adminUr: ${appConfigService.resolveAdminUrl()}")
        log.info("[APP Config] adminLoginField: ${appConfigService.resolveAdminLoginField()}")

        log.info("Register Custom Marshaller common data start")
        registerCustomMarshaller(messageSource)
        log.info("Register Custom Marshaller common data finish")

        switch (Environment.getCurrent()) {
            case Environment.DEVELOPMENT:
                loadTestData()
                break
        }
    }

    private static loadCommonData() {

        def rolesToCreate = ['ROLE_TENANT', 'ROLE_SERVICE_ORDER', 'ROLE_CONTRACTOR', 'ROLE_USER', 'ROLE_CUSTOMER']
        log.info("Roles to create: ${rolesToCreate}")
        rolesToCreate.each {
            if (Role.findByAuthority(it)) {
                log.info("Role [$it] found, nothing to do!")
            } else {
                log.info("Role [$it] not found, create it")
                new Role(it).save(failOnError: true)
            }
        }

        if (User.findByUsername("admin")) {
            log.info("User admin exists, nothing to do!")
        } else {
            log.info("User admin not found, create it")
            def userAdmin = new User(
                    name: "Administrador",
                    username: "admin",
                    email: "suporte.workfinity30@isolutions.com.br",
                    password: "Iso1981",
                    createdDate: new Date(),
            ).save(failOnError: true)
            UserRole.create(userAdmin, Role.findByAuthority('ROLE_TENANT'), true)
            UserRole.create(userAdmin, Role.findByAuthority('ROLE_USER'), true)
        }
    }

    private static loadTestData() {
    }

    private static registerCustomMarshaller(messageSource) {
        JSON.registerObjectMarshaller(FieldError) {
            return new FieldErrorCustomMarshaller(messageSource).marshalObject(it)
        }
        JSON.registerObjectMarshaller(ErrorMessage) {
            return new ErrorMessageCustomMarshaller(messageSource).marshalObject(it)
        }
        JSON.registerObjectMarshaller(User) {
            return User.marshalObject(it)
        }
        JSON.registerObjectMarshaller(EnabledOrDisabled) {
            return EnabledOrDisabled.marshalObject(it)
        }
        JSON.registerObjectMarshaller(StatusWkf) {
            return StatusWkf.marshalObject(it)
        }
        JSON.registerObjectMarshaller(ServiceOrderStatus) {
            return ServiceOrderStatus.marshalObject(it)
        }
        JSON.registerObjectMarshaller(LoginField) {
            return LoginField.marshalObject(it)
        }
        JSON.registerObjectMarshaller(ObjectDescriptionBuilt) {
            return ObjectDescriptionBuilt.marshalObject(it)
        }
        JSON.registerObjectMarshaller(DocumentType) {
            return DocumentType.marshalObject(it)
        }
        JSON.registerObjectMarshaller(YesOrNot) {
            return YesOrNot.marshalObject(it)
        }
    }
}
