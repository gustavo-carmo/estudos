package business.enums

enum ServiceOrderStatus {

    PRE_START, START, CLOSED, IN_PROGRESS, CANCELED, ERROR


    static marshalObject(object) {

        if (object) {
            ServiceOrderStatus serviceOrderStatusInstance = (ServiceOrderStatus) object;

            return serviceOrderStatusInstance.toString()
        }

        return null
    }
}