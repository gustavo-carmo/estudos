package bridge.marshaller

import bridge.misc.ErrorMessage
import org.springframework.context.NoSuchMessageException
import org.springframework.context.i18n.LocaleContextHolder

//TODO - testes
class ErrorMessageCustomMarshaller {

    def messageSource;

    ErrorMessageCustomMarshaller(messageSource) {
        this.messageSource = messageSource
    }

    def marshalObject(object) {

        if (object) {

            ErrorMessage instance = (ErrorMessage) object

            def ret = [:]

            ret."field" = instance.field;
            ret."error-code" = instance.code;

            if (instance.message && instance.message != instance.code) {
                ret."message" = instance.message
            } else {

                Locale locale = LocaleContextHolder.getLocale();
                if (messageSource != null) {
                    try {
                        ret."message" = messageSource.getMessage(instance.code,
                                instance.args ? instance.args as Object[] : null,
                                locale)
                    } catch (NoSuchMessageException e) {
                        ret."message" = ""
                    }
                }
            }

            return ret
        }

        return ""
    }
}
