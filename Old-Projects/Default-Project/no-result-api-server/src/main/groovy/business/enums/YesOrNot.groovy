package business.enums

enum YesOrNot {

    YES, NOT

    static marshalObject(object) {

        if (object) {
            YesOrNot yesOrNotInstance = (YesOrNot) object;

            return yesOrNotInstance.toString()
        }

        return null
    }
}