package business.enums

enum MenuType {

    NO_LINK, FORM

    static marshalObject(object) {

        if (object) {
            MenuType menuTypeInstance = (MenuType) object;

            return menuTypeInstance.toString()
        }

        return null
    }
}