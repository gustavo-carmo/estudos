package bridge

import grails.plugin.springsecurity.userdetails.GrailsUser
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService
    def repositoryService

    String name
    String username
    String email
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Date lastLogin
    Date createdDate

    User() {
    }

    User(String username, String password) {
        this()
        this.username = username
        this.password = password
    }

    //TODO - refatorar este metodo
    Set<Role> getAuthorities() {

        Set<Role> authorities = []

        return authorities
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        println "beforeUpdate"
        println isDirty('password')
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        println 'springSecurityService?.passwordEncoder'
        println springSecurityService?.passwordEncoder
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
        println password
    }

    static transients = ['springSecurityService']

    static constraints = {
        name nullable: false, blank: false, minSize: 5, maxSize: 50
        username nullable: false, blank: false, minSize: 5, maxSize: 50
        email nullable: false, blank: false, minSize: 5, maxSize: 50, email: true
        password nullable: false, blank: false
        lastLogin nullable: true
        createdDate nullable: false
    }

    static mapping = {
        password column: '`password`'
    }

    UserDetails getUserDetails() {
        return new GrailsUser(
                this.username,
                this.password,
                this.enabled,
                !this.accountExpired,
                this.enabled,
                !this.accountExpired,
                this.authorities.collect { new SimpleGrantedAuthority(it.authority) },
                this.id
        )
    }

    static marshalObject(object) {

        if (object) {

            User userInstance = (User) object;

            def ret = [:]
            ret."id" = userInstance.id
            ret."version" = userInstance.version
            ret."name" = userInstance.name
            ret."username" = userInstance.username
            ret."email" = userInstance.email
            ret."status" = userInstance.enabled ? 'ENABLED' : 'DISABLED'
            ret."lastLogin" = userInstance.lastLogin
            ret."createdDate" = userInstance.createdDate

            return ret
        }

        return null
    }
}