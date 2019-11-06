package business

import bridge.misc.ObjectDescription
import bridge.misc.ObjectDescriptionBuilt
import business.enums.DatabaseDriverType

class Contract implements ObjectDescription {

    String name
    String labelPt
    String labelEs
    String labelEn
    String code
    String logo
    byte[] logodata
    String background
    byte[] bgdata
    String databaseName
    String databaseUser
    String databasePass
    String databaseHost
    String databasePort
    String databaseExtra
    DatabaseDriverType databaseDriver

    static constraints = {
        name(maxSize: 40, blank: false, unique: true)
        labelPt(maxSize: 40, blank: false)
        labelEs(maxSize: 40, blank: true, nullable: true)
        labelEn(maxSize: 40, blank: true, nullable: true)
        code(maxSize: 20, blank: false, unique: true)
        logo(maxSize: 256, blank: false, nullable: true)
        logodata(nullable: true, maxSize: 1048576) // 1Mb // TODO - maxSize > isso tem que vir de alguma parametrização
        background(maxSize: 256, blank: false, nullable: true)
        bgdata(nullable: true, maxSize: 1048576) // 1Mb // TODO - maxSize > isso tem que vir de alguma parametrização
        databaseName(nullable: false, blank: false)
        databaseUser(nullable: false, blank: false)
        databasePass(nullable: false, blank: false)
        databaseHost(nullable: false, blank: false)
        databasePort(nullable: false, blank: false)
        databaseExtra(nullable: true)
        databaseDriver(nullable: false, inList: DatabaseDriverType.values().toList())
    }

    String toString() { "${name} (${code})" }

    String id
    static mapping = {
        id generator: 'uuid'
        sort "name"
        logodata sqlType: 'blob'
        bgdata sqlType: 'blob'
    }

    def getNameExtended() {
        return "${this.businessLine.name} / ${this.name}"
    }

    static marshalObject(object) {

        if (object) {
            Contract contractInstance = (Contract) object;

            def ret = [:]
            ret."id" = contractInstance.id
            ret."version" = contractInstance.version
            ret."name" = contractInstance.name
            ret."nameExtended" = contractInstance.getNameExtended()
            ret."code" = contractInstance.code
            ret."labelPt" = contractInstance.labelPt
            ret."labelEs" = contractInstance.labelEs
            ret."labelEn" = contractInstance.labelEn
            ret."databaseName" = contractInstance.databaseName
            ret."databaseUser" = contractInstance.databaseUser
            ret."databasePass" = contractInstance.databasePass
            ret."databaseHost" = contractInstance.databaseHost
            ret."databasePort" = contractInstance.databasePort
            ret."databaseExtra" = contractInstance.databaseExtra

            ret."hasBackground" = contractInstance.background ? true : false
            ret."hasLogo" = contractInstance.logo ? true : false

            if (contractInstance.businessLine) {
                ret."businessLine" = contractInstance.businessLine.getObjectDescription()
            } else {
                ret."businessLine" = ""
            }

            return ret
        }

        return null
    }

    @Override
    ObjectDescriptionBuilt getObjectDescription() {
        return ObjectDescriptionBuilt.build(
                this.id,
                this.getNameExtended()
        )
    }
}
