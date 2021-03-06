package bridge.misc

//TODO - TESTES
interface ObjectDescription {

    ObjectDescriptionBuilt getObjectDescription()
}

class ObjectDescriptionBuilt {

    private Long id
    private String label

    private ObjectDescriptionBuilt() {
    }

    static constraints = {
        id nullable: false
        label nullable: false, blank: false
    }

    static ObjectDescriptionBuilt build(Long id, String label) {
        def objectDescriptionBuilt = new ObjectDescriptionBuilt()
        objectDescriptionBuilt.id = id
        objectDescriptionBuilt.label = label
        return objectDescriptionBuilt
    }

    static marshalObject(object) {

        if (object) {
            ObjectDescriptionBuilt instance = (ObjectDescriptionBuilt) object;

            def ret = [:]
            ret."id" = instance.id
            ret."label" = instance.label

            return ret
        }

        return null
    }
}