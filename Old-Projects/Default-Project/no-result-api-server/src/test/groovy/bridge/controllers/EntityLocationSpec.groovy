package bridge.controllers

import bridge.Role
import bridge.User
import bridge.exception.AppValidationException
import spock.lang.Specification

class EntityLocationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Should be invalid with invalid params"() {
        given:
        def id = 1
        def version = 2
        def instance = new User()
        def ex


        when:
        EntityLocation.build(null, version, instance)
        then:
        ex = thrown(AppValidationException)
        and:
        ex.message == 'param id é requerido [null]'


        when:
        EntityLocation.build(id, null, instance)
        then:
        ex = thrown(AppValidationException)
        and:
        ex.message == 'param version é requerido [null]'


        when:
        EntityLocation.build(id, 0, instance)
        then:
        noExceptionThrown()


        when:
        EntityLocation.build(id, version, null)
        then:
        ex = thrown(AppValidationException)
        and:
        ex.message == 'param instance é requerido [null]'


        /*when:
        EntityLocation.build(id, version, new Todo())
        then:
        ex = thrown(AppValidationException)
        and:
        ex.message == 'location not found to class [class todo.Todo]'*/
    }

    void "Should build User location with success"() {
        given:
        def id = 1
        def version = 2
        def instance = new User()

        when:
        def entityLocation = EntityLocation.build(id, version, instance)

        then:
        entityLocation != null
        entityLocation instanceof EntityLocation
        entityLocation.id == id
        entityLocation.version == version
        entityLocation.location == "/api/users/${id}"
        entityLocation.name == "User"
    }

    void "Should build Role location with success"() {
        given:
        def id = 1
        def version = 2
        def instance = new Role()

        when:
        def entityLocation = EntityLocation.build(id, version, instance)

        then:
        entityLocation != null
        entityLocation instanceof EntityLocation
        entityLocation.id == id
        entityLocation.version == version
        entityLocation.location == "/api/role/${id}"
        entityLocation.name == "Role"
    }
}
