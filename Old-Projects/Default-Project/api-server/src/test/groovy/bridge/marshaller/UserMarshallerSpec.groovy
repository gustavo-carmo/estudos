package bridge.marshaller

import bridge.User
import bridge.misc.ObjectDescriptionBuilt
import business.Tenant
import spock.lang.Specification

class UserMarshallerSpec extends Specification {

    def setup() {
        0 * _
    }

    def cleanup() {
    }

    void "Deverá fazer o marshaller do Usuario com sucesso"() {

        given:
        User userMocked = Mock(User)
        Long id = 1, version = 5
        String name = "Um nome para o Usuario",
            username = "nomeDeUsuario",
            email = "usuario@usuario.com"
        boolean enabled = true
        Date lastLogin = new Date(), createdDate = new Date() - 2
        Tenant tenantMocked = Mock(Tenant)
        ObjectDescriptionBuilt objectDescriptionBuiltMocked = Mock(ObjectDescriptionBuilt)

        when:
        def result = User.marshalObject(userMocked)

        then:
        1 * userMocked.getId() >> id
        1 * userMocked.getVersion() >> version
        1 * userMocked.getName() >> name
        1 * userMocked.getUsername() >> username
        1 * userMocked.getEmail() >> email
        1 * userMocked.getEnabled() >> enabled
        1 * userMocked.getLastLogin() >> lastLogin
        1 * userMocked.getCreatedDate() >> createdDate
        2 * userMocked.getTenant() >> tenantMocked
        1 * tenantMocked.getObjectDescription() >> objectDescriptionBuiltMocked

        and:

        result.id == id
        result.version == version
        result.name == name
        result.username == username
        result.email == email
        result.status == enabled ? 'ENABLED' : 'DISABLED'
        result.lastLogin == lastLogin
        result.createdDate == createdDate
        result.tenant == objectDescriptionBuiltMocked

    }

    void "Deverá me retornar vazio quando não passar um objeto para o metodo"() {

        when:
        def result = User.marshalObject(null)
        then:
        result == null
    }
}
