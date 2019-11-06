package business.enums

enum EnabledOrDisabled {

    ENABLED, DISABLED

    static marshalObject(object) {

        if (object) {
            EnabledOrDisabled enabledOrDisabledInstance = (EnabledOrDisabled) object;

            return enabledOrDisabledInstance.toString()
        }

        return null
    }
}
