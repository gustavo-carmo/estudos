package bridge.exception

import bridge.misc.ErrorMessage
import grails.util.Holders
import groovy.util.logging.Log4j
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceResolvable
import org.springframework.context.NoSuchMessageException
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

@Log4j
class AppBusinessException extends RuntimeException {

    MessageSource messageSource = Holders?.grailsApplication?.mainContext?.getBean('messageSource')
    List<ErrorMessage> errorMessages = []

    AppBusinessException(String message) {
        super(message)
    }

    AppBusinessException() {
    }

    AppBusinessException(Errors errors) {
        addErrors(errors)
    }

    static def throwIfTrue(boolean throwException, Errors errors) {
        if (throwException) {
            throw new AppBusinessException(errors)
        }
    }

    static def throwIfTrue(boolean throwException, String code) {
        if (throwException) {
            AppBusinessException appBusinessException = new AppBusinessException()
            appBusinessException.addCodeError(code)
            throw appBusinessException
        }
    }

    // TODO - tests
    static def throwIfTrue(boolean throwException, String code, List args) {
        if (throwException) {
            AppBusinessException appBusinessException = new AppBusinessException()
            appBusinessException.addCodeErrorWithArgs(code, args)
            throw appBusinessException
        }
    }

    def addCodeError(String code) {
        errorMessages.add(
                ErrorMessage.buildWithMessageCode(code)
        )
    }

    // TODO - tests
    def addCodeErrorWithArgs(String code, List args) {
        errorMessages.add(
                ErrorMessage.buildWithMessageCodeAndArgs(code, args)
        )
    }

    def addErrors(Errors errors) {
        errors?.allErrors?.each { ObjectError messageSourceResolvable ->

            String field = ""

            if (messageSourceResolvable instanceof FieldError) {
                messageSourceResolvable = (FieldError) messageSourceResolvable
                field = messageSourceResolvable.field
            }

            errorMessages.add(ErrorMessage.build(
                    field,
                    messageSourceResolvable.code,
                    resolveMessage(messageSourceResolvable),
                    messageSourceResolvable
            ))
        }
    }

    private String resolveFieldValue(MessageSourceResolvable messageSourceResolvable) {
        if (messageSourceResolvable instanceof FieldError) {
            return (FieldError) messageSourceResolvable.field
        } else if (messageSourceResolvable instanceof ObjectError) {
            return null
        }

    }

    private resolveMessage(MessageSourceResolvable paramFieldError) {
        try {
            messageSource.getMessage(paramFieldError, null)
        } catch (NoSuchMessageException e) {
            log.error paramFieldError
            log.error e.message
        }
    }

    String getMessage() {
        String message = super.message
        if (message && this.errorMessages) {
            return "Message: ${super.message}, Error Messages: ${errorMessages}"
        } else if (message) {
            return message
        } else {
            return this.errorMessages
        }
    }
}

