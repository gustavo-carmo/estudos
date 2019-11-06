package bridge.domain

import bridge.Role
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.utils.StringUtils
import bridge.User
import bridge.UserRole
import business.Customer
import business.Tenant
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.security.core.userdetails.UserDetails
import business.RepositoryService
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(User)
@Mock([Role])
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "validate constraints: #field value is #value, validate is #isValid and expected error #errorExpected"() {
        def instance = new User()
        instance."${field}" = value

        def validate

        when:
        validate = instance.validate([field])

        then:
        isValid == validate
        errorExpected == instance.errors["$field"]?.code

        where:
        field             | value                        | isValid | errorExpected
        "name"            | null                         | false   | "nullable"
        "name"            | ""                           | false   | "blank"
        "name"            | StringUtils.randomString(4)  | false   | "minSize.notmet"
        "name"            | StringUtils.randomString(51) | false   | "maxSize.exceeded"
        "name"            | StringUtils.randomString(6)  | true    | null

        "username"        | null                         | false   | "nullable"
        "username"        | ""                           | false   | "blank"
        "username"        | StringUtils.randomString(4)  | false   | "minSize.notmet"
        "username"        | StringUtils.randomString(51) | false   | "maxSize.exceeded"
        "username"        | StringUtils.randomString(6)  | true    | null

        "email"           | null                         | false   | "nullable"
        "email"           | ""                           | false   | "blank"
        "email"           | StringUtils.randomString(4)  | false   | "minSize.notmet"
        "email"           | StringUtils.randomString(51) | false   | "maxSize.exceeded"
        "email"           | StringUtils.randomString(6)  | false   | "email.invalid"
        "email"           | "email@teste.com"            | true    | null

        "password"        | null                         | false   | "nullable"
        "password"        | ""                           | false   | "blank"
        "password"        | "123456"                     | true    | null

        "enabled"         | false                        | true    | null
        "accountExpired"  | false                        | true    | null
        "accountLocked"   | false                        | true    | null
        "passwordExpired" | false                        | true    | null

        "lastLogin"       | null                         | true    | null
        "lastLogin"       | new Date()                   | true    | null
        "lastLogin"       | null                         | true    | null

        "createdDate"     | null                         | false   | "nullable"
        "createdDate"     | new Date()                   | true    | null

        "customer"        | null                         | true    | null
        "customer"        | new Customer()               | true    | null

        "tenant"          | null                         | true    | null
        "tenant"          | new Tenant()                 | true    | null
    }

    void "Deverá construir com sucesso um UserDetails, quando o perfil do usuario for admin"() {

        given:
        User user = new User(
                enabled: true,
                username: "usernameExample",
                password: "passwordExample",
                accountExpired: false,
                accountLocked: false,

        )
        UserDetails userDetails

        when:
        userDetails = user.getUserDetails()

        then:
        userDetails != null
        userDetails.enabled == user.enabled
        userDetails.username == user.username
        userDetails.password == user.password
        userDetails.authorities.collect { it.authority }.size() == 2
        userDetails.authorities.collect { it.authority }.contains('ROLE_USER')
        userDetails.authorities.collect { it.authority }.contains('ROLE_TENANT')
        userDetails.accountNonExpired == !user.accountExpired
        userDetails.accountNonLocked == !user.accountLocked
        userDetails.credentialsNonExpired == user.enabled
    }

    void "Deverá construir com sucesso um UserDetails, quando o perfil do usuario for admin Tenant"() {

        given:
        User user = new User(
                enabled: true,
                username: "usernameExample",
                password: "passwordExample",
                accountExpired: false,
                accountLocked: false,
                tenant: Mock(Tenant)

        )
        UserDetails userDetails

        when:
        userDetails = user.getUserDetails()

        then:
        userDetails != null
        userDetails.enabled == user.enabled
        userDetails.username == user.username
        userDetails.password == user.password
        userDetails.authorities.collect { it.authority }.size() == 2
        userDetails.authorities.collect { it.authority }.contains('ROLE_CONTRACTOR')
        userDetails.authorities.collect { it.authority }.contains('ROLE_CUSTOMER')
        userDetails.accountNonExpired == !user.accountExpired
        userDetails.accountNonLocked == !user.accountLocked
        userDetails.credentialsNonExpired == user.enabled
    }

    void "Deverá construir com sucesso um UserDetails, quando o perfil do usuario for cliente"() {

        given:
        User user = new User(
                enabled: true,
                username: "usernameExample",
                password: "passwordExample",
                accountExpired: false,
                accountLocked: false,
                tenant: Mock(Tenant),
                customer: Mock(Customer)

        )
        UserDetails userDetails

        when:
        userDetails = user.getUserDetails()

        then:
        userDetails != null
        userDetails.enabled == user.enabled
        userDetails.username == user.username
        userDetails.password == user.password
        userDetails.authorities.collect { it.authority }.size() == 1
        userDetails.authorities.collect { it.authority }.contains('ROLE_SERVICE_ORDER')
        userDetails.accountNonExpired == !user.accountExpired
        userDetails.accountNonLocked == !user.accountLocked
        userDetails.credentialsNonExpired == user.enabled
    }

    void "hasAffiliationWithCustomer: Deverá devolver true quando usuário está associado a um cliente"() {
        given:
        Boolean hasAffiliationWithCustomer
        User user = new User()

        when:
        user.customer = Mock(Customer)
        hasAffiliationWithCustomer = user.hasAffiliationWithCustomer()

        then:
        hasAffiliationWithCustomer


        when:
        user.customer = null
        hasAffiliationWithCustomer = user.hasAffiliationWithCustomer()

        then:
        !hasAffiliationWithCustomer
    }

    void "hasAffiliationWithTenant: Deverá devolver true quando usuário está associado a um tenant"() {
        given:
        Boolean hasAffiliationWithTenant
        User user = new User()

        when:
        user.customer = null
        user.tenant = Mock(Tenant)
        hasAffiliationWithTenant = user.hasAffiliationWithTenant()

        then:
        hasAffiliationWithTenant

        when:
        user.customer = null
        user.tenant = null
        hasAffiliationWithTenant = user.hasAffiliationWithTenant()

        then:
        !hasAffiliationWithTenant
    }

    void "isAdmin: Deverá devolver true quando usuário é um admin"() {

        given:
        Boolean isAdmin
        User user = new User()

        when:
        user.customer = null
        user.tenant = null
        isAdmin = user.isAdmin()

        then:
        isAdmin

        when:
        user.customer = Mock(Customer)
        isAdmin = user.isAdmin()

        then:
        !isAdmin
    }
}

