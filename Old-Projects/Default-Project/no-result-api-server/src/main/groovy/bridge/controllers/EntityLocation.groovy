package bridge.controllers

import bridge.Role
import bridge.User
import bridge.exception.AppValidationException
import business.*

class EntityLocation {

    def id
    def version
    def location
    def name

    private static BASE_PATH = '/api'

    static private locations = [
            [instance: User, location: "users"],
            [instance: Role, location: "role"]
    ]

    private EntityLocation() {
    }

    static EntityLocation build(idParam, versionParam, instanceParam) {

        if (!idParam) {
            throw new AppValidationException("param id é requerido [${idParam}]")
        }

        if (versionParam == null) {
            throw new AppValidationException("param version é requerido [${versionParam}]")
        }

        if (!instanceParam) {
            throw new AppValidationException("param instance é requerido [${instanceParam}]")
        }

        def entityReference = new EntityLocation()
        entityReference.id = idParam
        entityReference.version = versionParam
        entityReference.location = resolveLocation(idParam, instanceParam)
        entityReference.name = resolveName(instanceParam)

        return entityReference
    }

    static private resolveLocation(idParam, instanceParam) {

        def location = locations.find { it.instance == instanceParam.class }?.location

        if (!location) {
            throw new AppValidationException("location not found to class [${instanceParam.class}]")
        }

        return "$BASE_PATH/$location/$idParam"
    }

    static private resolveName(instanceParam) {

        def instance = locations.find { it.instance == instanceParam.class }?.instance

        if (!instance) {
            throw new AppValidationException("instance not found to class [${instanceParam.class}]")
        }

        return String.valueOf(instance.getSimpleName())
    }
}
