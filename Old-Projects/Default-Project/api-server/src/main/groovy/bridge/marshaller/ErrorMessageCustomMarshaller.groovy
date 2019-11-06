package bridge.marshaller

import bridge.misc.ErrorMessage
import org.springframework.context.i18n.LocaleContextHolder

//TODO - testes
class ErrorMessageCustomMarshaller {

    def messageSource;

    ErrorMessageCustomMarshaller(messageSource) {
        this.messageSource = messageSource
    }

    def marshalObject(object) {

        if (object) {

            ErrorMessage errorMessage = (ErrorMessage) object
            Locale locale = LocaleContextHolder.getLocale();

            if (errorMessage.message) {
                return errorMessage.message
            }

            if (errorMessage.code) {

                // getMessage(String code, Object[] args, Locale locale)
                return messageSource.getMessage(
                        errorMessage.code,
                        errorMessage.args ? errorMessage.args as Object[] : null,
                        locale
                )
            }
        }

        return ""
    }
}
