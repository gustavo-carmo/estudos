package bridge.misc

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.validation.Errors

class ErrorMessage {

    def code
    def args
    def message

    private ErrorMessage() {
    }

    // TODO testes
    static ErrorMessage buildWithMessageCode(String code) {
        return build(code, null)
    }

    // TODO testes
    static ErrorMessage build(String code, List args) {
        def errorMessage = new ErrorMessage()
        errorMessage.code = code
        if (args) {
            errorMessage.args = args
        }
        return errorMessage
    }

    // TODO testes
    static ErrorMessage build(String message) {
        def errorMessage = new ErrorMessage()
        errorMessage.message = message
        return errorMessage
    }

    // TODO testes
    static Errors buildValidationError(command, String field, String messageCode, List args) {
        Errors errors = command.errors
        if (!args) {
            errors.rejectValue(field, messageCode)
        } else {
            errors.rejectValue(field, messageCode, args.toArray(), null)
        }
        return errors
    }

    // TODO testes
    static Errors buildValidationError(command, String field, String messageCode) {
        return buildValidationError(command, field, messageCode, null)
    }

    // TODO testes
    public String toString() {
        return this.message
    }
}