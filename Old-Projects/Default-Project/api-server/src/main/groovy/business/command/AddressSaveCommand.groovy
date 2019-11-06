package business.command

import grails.validation.Validateable

class AddressSaveCommand implements  Validateable {

    String phone
    String mobile
    String zipCode
    String address
    String number
    String addressDetails
    String district
    String city
    String state

    static constraints = {
        phone nullable: true
        mobile nullable: true
        zipCode nullable: false, blank: false
        address nullable: false, blank: false, minSize: 3, maxSize: 50
        number nullable:  true
        addressDetails nullable: true
        district nullable:  true
        city nullable: false, blank: false
        state nullable: false, blank: false
    }
}
