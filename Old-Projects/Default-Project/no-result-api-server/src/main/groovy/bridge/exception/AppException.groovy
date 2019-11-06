package bridge.exception

import grails.util.Holders
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.i18n.LocaleContextHolder

class AppException extends RuntimeException {

    AppException(String code) {
        super(resolveCode(code, null))
    }

    AppException(String code, List args) {
        super(resolveCode(code, args))
        // TODO -  toda vez que der esse erro, precisa logar num arquivo separado
    }

    private static String resolveCode(String code, List args) {
        MessageSource messageSource = Holders?.grailsApplication?.mainContext?.getBean('messageSource')

        if (!messageSource) {
            return code
        }

        Locale locale = LocaleContextHolder.getLocale();
        String unexpected = messageSource.getMessage("error.unexpected", null, locale)
        String errorMessage
        try {
            errorMessage = messageSource.getMessage(code, args?.toArray() ?: null, locale)
        } catch (NoSuchMessageException e) {
            errorMessage = code
        }
        return "${unexpected}: ${errorMessage}"
    }

    static void throwIfNull(Object param, String message) {
        if (param == null) {
            throw new AppException(message)
        }
    }

    static void throwIfTrue(boolean param, String message) {
        if (param) {
            throw new AppException(message)
        }
    }

    static void throwIfRequiredParamsHasNull(Map requiredParams) {
        if (requiredParams) {
            Set paramsWithNullValue = requiredParams.findAll { key, value -> value == null }.keySet()
            throwIfTrue(
                    paramsWithNullValue.size() > 0,
                    "Params ${paramsWithNullValue} are required"
            )
        }

    }
}
