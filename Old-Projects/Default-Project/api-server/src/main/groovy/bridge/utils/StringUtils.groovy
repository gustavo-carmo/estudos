package bridge.utils

import org.apache.commons.validator.routines.EmailValidator

class StringUtils {

    static boolean isValidEmail(String email) {
        def emailValidator = EmailValidator.getInstance()
        return emailValidator.isValid(email)
    }

    public static String randomString(Integer size) {
        return size ? org.apache.commons.lang.RandomStringUtils.random(size, true, true) : ""
    }
}
