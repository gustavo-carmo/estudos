package bridge.misc

import org.springframework.validation.Errors
import org.springframework.validation.FieldError

class ResultService {

    List<FieldError> fieldErrors = []
    ErrorMessage errorMessage
    def data

    // TODO - (1) remover as vars abaixo ESTÃO SENDO USADAS APENAS NA EXAMPLE_CONTROLLER
    def instance
    def instanceList
    def pagination

    // TODO - tests
    def addValidationErrors(Errors errors) {
        def allErrors = errors?.allErrors
        if (allErrors) {
            this.fieldErrors.addAll(allErrors)
        }
    }

    // TODO - tests
    def isValid() {
        !(fieldErrors || errorMessage)
    }
}