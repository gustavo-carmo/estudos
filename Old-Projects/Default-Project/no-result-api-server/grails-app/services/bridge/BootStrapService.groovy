package bridge

import bridge.marshaller.ErrorMessageCustomMarshaller
import bridge.marshaller.FieldErrorCustomMarshaller
import bridge.misc.ErrorMessage
import bridge.misc.ObjectDescriptionBuilt
import business.Contract
import business.enums.EnabledOrDisabled
import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Environment
import groovy.util.logging.Log4j
import org.springframework.validation.FieldError

@Log4j
@Transactional
class BootStrapService {

    def messageSource

    def init() {

        log.info("Load common data start")
        loadCommonData()
        log.info("Load common data finish")

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

        def rolesToCreate = []
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
                    name: "SGS Admin",
                    username: "admin",
                    email: "admin@admin.com",
                    password: "123456",
                    createdDate: new Date(),
            ).save(failOnError: true)
        }
    }

    private static loadTestData() {
    }

    private static registerCustomMarshaller(messageSource) {
        // Bridge
        JSON.registerObjectMarshaller(FieldError) {
            return new FieldErrorCustomMarshaller(messageSource).marshalObject(it)
        }
        JSON.registerObjectMarshaller(ErrorMessage) {
            return new ErrorMessageCustomMarshaller(messageSource).marshalObject(it)
        }
        JSON.registerObjectMarshaller(EnabledOrDisabled) {
            return EnabledOrDisabled.marshalObject(it)
        }
        JSON.registerObjectMarshaller(ObjectDescriptionBuilt) {
            return ObjectDescriptionBuilt.marshalObject(it)
        }

        JSON.registerObjectMarshaller(User) {
            return User.marshalObject(it)
        }

        JSON.registerObjectMarshaller(Contract) {
            return Contract.marshalObject(it)
        }
    }
}
