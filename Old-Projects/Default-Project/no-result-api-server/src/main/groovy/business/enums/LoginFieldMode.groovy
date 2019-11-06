package business.enums

enum LoginField {
    USERNAME, DOCUMENT_NUMBER

    static marshalObject(object) {

        if (object) {
            LoginField loginFieldInstance = (LoginField) object;

            return loginFieldInstance.toString()
        }

        return null
    }
}
