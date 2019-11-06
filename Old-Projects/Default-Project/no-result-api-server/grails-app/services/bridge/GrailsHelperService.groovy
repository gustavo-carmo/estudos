package bridge

import grails.transaction.Transactional
import org.springframework.validation.Errors

@Transactional(readOnly = true)
class GrailsHelperService {

    // TODO - tests
    def Errors resolveValidationErrors(commandOrEntityInstance,
                                       String field, String messageCode, List args) {

        Errors errors = commandOrEntityInstance.errors
        if (!args) {
            errors.rejectValue(field, messageCode)
        } else {
            errors.rejectValue(field, messageCode, args.toArray(), null)
        }
        return errors
    }

    // TODO - tests
    def Errors resolveValidationErrors(commandOrEntityInstance,
                                       String field, String messageCode) {
        return resolveValidationErrors(commandOrEntityInstance, field, messageCode, null)
    }
}
