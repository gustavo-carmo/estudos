package bridge.marshaller

import org.springframework.context.i18n.LocaleContextHolder

//TODO - testes
class FieldErrorCustomMarshaller {

    def messageSource;

    FieldErrorCustomMarshaller(messageSource) {
        this.messageSource = messageSource
    }

    def marshalObject(object) {

        def ret = [:]
        ret."field" = object.getField();
        ret."error-code" = object.code;
        ret."rejected-value" = object.getRejectedValue();
        Locale locale = LocaleContextHolder.getLocale();
        if (messageSource != null) {
            ret."message" = messageSource.getMessage(object, locale);
        } else {
            ret."message" = object.getDefaultMessage();
        }
        return ret
    }
}
