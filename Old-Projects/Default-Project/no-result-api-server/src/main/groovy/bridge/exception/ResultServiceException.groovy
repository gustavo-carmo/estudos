package bridge.exception

import bridge.misc.ErrorMessage

class ResultServiceException extends RuntimeException {

    ResultServiceException(ErrorMessage errorMessage) {
        super(errorMessage)
    }

    ResultServiceException(List fieldErrors) {
        super(fieldErrors)
    }
}
