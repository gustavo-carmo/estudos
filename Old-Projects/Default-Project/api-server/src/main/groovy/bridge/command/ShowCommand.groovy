package bridge.command

import grails.validation.Validateable

class ShowCommand implements Validateable {

    Long id

    static constraints = {
        id nullable: false
    }
}
