package business.enums

enum StatusWkf {
    ENABLED, DISABLED, NOT_FOUND

    static marshalObject(object) {

        if (object) {
            StatusWkf statusWkf = (StatusWkf) object;

            return statusWkf.toString()
        }

        return null
    }
}