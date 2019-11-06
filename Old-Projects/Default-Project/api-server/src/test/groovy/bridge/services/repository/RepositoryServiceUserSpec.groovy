package bridge.services.repository

import business.Tenant
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification
import business.RepositoryService
import bridge.misc.SearchParams
import bridge.misc.SearchResult
import bridge.User

@TestFor(RepositoryService)
@Mock([User, Tenant])
class RepositoryServiceUserSpec extends Specification {

    @Shared
    User porId, nomeJoao, usernameMar, usernameMaria, disabled,
            enabled, email, email2, lastLogin20151010,
            lastLogin20151015, lastLogin20151020,
            userWithTenantAndUsernameJohn, customerIsNull,
            userWithUsernameAndTenant, userWithEmailAndTenant
    @Shared
    Tenant tenantToUser
    @Shared
    def username, emailString

    def setup() {
        0 *_
        tenantToUser = new Tenant().save(validate: false)
        username = 'usuarioT'
        emailString = 'email-teste@gmail.com'
        porId = new User().save(validate: false)
        nomeJoao = new User(name: "João").save(validate: false)
        usernameMaria = new User(username: "maria").save(validate: false)
        usernameMar = new User(username: "mar").save(validate: false)
        enabled = new User(enabled: true).save(validate: false)
        disabled = new User(enabled: false).save(validate: false)
        email = new User(email: "email@test.com").save(validate: false)
        email2 = new User(email: "email@").save(validate: false)
        lastLogin20151010 = new User(
                lastLogin: Date.parse("dd/MM/yyyy hh:mm:ss", "10/10/2015 00:00:00")
        ).save(validate: false)
        lastLogin20151015 = new User(
                lastLogin: Date.parse("dd/MM/yyyy hh:mm:ss", "15/10/2015 00:00:00")
        ).save(validate: false)
        lastLogin20151020 = new User(
                lastLogin: Date.parse("dd/MM/yyyy hh:mm:ss", "20/10/2015 00:00:00")
        ).save(validate: false)
        userWithTenantAndUsernameJohn = new User(
                username: "john",
                tenant: new Tenant(
                        identifier: "3rwefsda34er"
                ).save(validate: false)
        ).save(validate: false)
        userWithUsernameAndTenant = new User(
                username: username,
                tenant: tenantToUser
        ).save(validate: false)
        userWithEmailAndTenant = new User(
                email: emailString,
                tenant: tenantToUser
        ).save(validate: false)
        customerIsNull = new User(customer: null).save(validate: false)
    }

    def cleanup() {
    }

    void "Deverá pesquisar por id (findUserById)"() {
        when:
        User user = service.findUserById(porId.id)
        then:
        user != null
        user.id == porId.id


        when:
        user = service.findUserById(null)
        then:
        user == null


        when:
        def userNotExists = 9999L
        user = service.findUserById(userNotExists)
        then:
        user == null
    }

    void "Deverá pesquisar por username(findUserByUsername)"() {
        when:
        User user = service.findUserByUsername(usernameMaria.username)
        then:
        user != null
        user.username == usernameMaria.username

        when:
        user = service.findUserByUsername(null)
        then:
        user == null

        when:
        def userNotExists = "renata"
        user = service.findUserByUsername(userNotExists)
        then:
        user == null
    }

    void "Deverá pesquisar por username(findUserByUsernameEquals)"() {
        when:
        User user = service.findUserByUsernameEquals(usernameMar.username)
        then:
        user != null
        user.username == usernameMar.username

        when:
        user = service.findUserByUsername(null)
        then:
        user == null

        when:
        def userNotExists = "renata"
        user = service.findUserByUsername(userNotExists)
        then:
        user == null
    }

    void "Deverá pesquisar por email(findUserByEmailEquals)"() {
        when:
        User user = service.findUserByEmailEquals(email2.email)
        then:
        user != null
        user.email == email2.email

        when:
        user = service.findUserByEmailEquals(null)
        then:
        user == null

        when:
        def userNotExists = "emailNotExists@hotmail.com"
        user = service.findUserByEmailEquals(userNotExists)
        then:
        user == null
    }

    void "Deverá pesquisar por username e tenant(findUserByUsernameEqualsAndTenantEquals)" () {

        when: // sucesso
        User user = service.findUserByUsernameEqualsAndTenantEquals(
                username,
                tenantToUser
        )
        then:
        user != null
        user == userWithUsernameAndTenant

        when: // username null
        user = service.findUserByUsernameEqualsAndTenantEquals(
                null,
                tenantToUser
        )
        then:
        user == null

        when: // tenant null
        user = service.findUserByUsernameEqualsAndTenantEquals(
                username,
                null
        )
        then:
        user == null

        when: // username and tenant null
        user = service.findUserByUsernameEqualsAndTenantEquals(
                null,
                null
        )
        then:
        user == null
    }

    void "Deverá pesquisar por username e tenant vazio(findUserByUsernameEqualsAndTenantIsNull)" () {

        given:
        User user

        when: // sucesso
        user = service.findUserByUsernameEqualsAndTenantIsNull(
                usernameMaria.username)
        then:
        user != null
        user == usernameMaria

        when: // username null
        user = service.findUserByUsernameEqualsAndTenantIsNull(
                null)
        then:
        user == null

        when: // username with tenant
        user = service.findUserByUsernameEqualsAndTenantIsNull(
                username)
        then:
        user == null
    }

    void "Deverá pesquisar por email e tenant(findUserByEmailEqualsAndTenantEquals)" () {

        given:
        User user

        when: // sucesso
        user = service.findUserByEmailEqualsAndTenantEquals(
                emailString,
                tenantToUser
        )
        then:
        user != null
        user == userWithEmailAndTenant

        when: // email null
        user = service.findUserByEmailEqualsAndTenantEquals(
                null,
                tenantToUser
        )
        then:
        user == null

        when: // tenant null
        user = service.findUserByEmailEqualsAndTenantEquals(
                emailString,
                null
        )
        then:
        user == null

        when: // email and tenant null
        user = service.findUserByEmailEqualsAndTenantEquals(
                null,
                null
        )
        then:
        user == null
    }

    void "Deverá pesquisar por email e tenant vazio(findUserByEmailAndTenantIsNull)" () {

        given:
        User user

        when: // sucesso
        user = service.findUserByEmailAndTenantIsNull(
                email.email
        )
        then:
        user != null
        user == email

        when: // email null
        user = service.findUserByEmailAndTenantIsNull(
                null
        )
        then:
        user == null

        when: // user with email and tenant
        user = service.findUserByEmailAndTenantIsNull(
                emailString
        )
        then:
        user == null
    }

    void "Deverá pesquisar usuários com sucesso"() {
        given:
        SearchParams searchParams

        when:
        SearchResult searchResult = service.findUsers()

        then: // todos
        searchResult.totalCount == 15


        when: // id
        searchParams = new SearchParams()
        searchParams.addFilter("id", porId.id)
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(porId)
        searchResult.totalCount == 1


        when: // name
        searchParams = new SearchParams()
        searchParams.addFilter("name", "Joã")
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(nomeJoao)
        searchResult.totalCount == 1


        when: // username
        searchParams = new SearchParams()
        searchParams.addFilter("username", "ria")
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(usernameMaria)
        searchResult.totalCount == 1


        when: // usernameEq
        searchParams = new SearchParams()
        searchParams.addFilter("usernameEq", "mar")
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(usernameMar)
        searchResult.totalCount == 1


        when: // enabled
        searchParams = new SearchParams()
        searchParams.addFilter("enabled", false)
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(disabled)
        searchResult.totalCount == 1


        when: // email
        searchParams = new SearchParams()
        searchParams.addFilter("email", "il@te")
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(email)
        searchResult.totalCount == 1


        when: // emailEq
        searchParams = new SearchParams()
        searchParams.addFilter("emailEq", "email@")
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(email2)
        searchResult.totalCount == 1


        when: // lastLogin entre 20151012 e 20151017
        searchParams = new SearchParams()
        searchParams.addFilter("lastLoginStart", Date.parse("dd/MM/yyyy hh:mm:ss", "12/10/2015 00:00:00"))
        searchParams.addFilter("lastLoginEnd", Date.parse("dd/MM/yyyy hh:mm:ss", "17/10/2015 00:00:00"))
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(lastLogin20151010)
        searchResult.totalCount == 1


        when: // lastLogin desde 20151001
        searchParams = new SearchParams()
        searchParams.addFilter("lastLoginStart", Date.parse("dd/MM/yyyy hh:mm:ss", "01/10/2015 00:00:00"))
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(lastLogin20151010)
        searchResult.instanceList.contains(lastLogin20151015)
        searchResult.instanceList.contains(lastLogin20151020)
        searchResult.totalCount == 3


        when: // lastLogin desde 20151016
        searchParams = new SearchParams()
        searchParams.addFilter("lastLoginStart", Date.parse("dd/MM/yyyy hh:mm:ss", "16/10/2015 00:00:00"))
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(lastLogin20151020)
        searchResult.totalCount == 1

        /*when: // lastLogin até 20151030
        searchParams = new SearchParams()
        searchParams.addFilter("lastLoginEnd", Date.parse("dd/MM/yyyy hh:mm:ss", "30/10/2015 00:00:00"))
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.each {
            println "${it.name} - ${it.lastLogin}"
        }
        searchResult.instanceList.contains(lastLogin20151010)
        searchResult.instanceList.contains(lastLogin20151015)
        searchResult.instanceList.contains(lastLogin20151020)
        searchResult.totalCount == 3*/

        /*when: // lastLogin até 20151016
        searchParams = new SearchParams()
        searchParams.addFilter("lastLogin", Date.parse("dd/MM/yyyy hh:mm:ss", "16/10/2015 00:00:00"))
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(lastLogin20151010)
        searchResult.instanceList.contains(lastLogin20151015)
        searchResult.totalCount == 2*/

        when: // tenant
        searchParams = new SearchParams()
        searchParams.addFilter("tenant", userWithTenantAndUsernameJohn.tenant)
        searchResult = service.findUsers(searchParams)

        then:
        searchResult.instanceList.contains(userWithTenantAndUsernameJohn)
        searchResult.totalCount == 1

        when: // customerIsNull
        searchParams = new SearchParams()
        searchParams.addFilter("customerIsNull", true)
        searchResult = service.findUsers(searchParams)

        then: // O resultado da pesquisa ira trazer todos os usuarios que estão com o cliente vazio
        searchResult.instanceList.contains(customerIsNull)

    }

    void "Deverá pesquisar por username e tenantIdentifier"() {
        when:
        User user = service.findUserByUsernameAndTenantIdentifier(
                userWithTenantAndUsernameJohn.username,
                userWithTenantAndUsernameJohn.tenant.identifier
        )
        then:
        user != null
        user.username == userWithTenantAndUsernameJohn.username

        when:
        user = service.findUserByUsernameAndTenantIdentifier(null, null)
        then:
        user == null

        when:
        user = service.findUserByUsernameAndTenantIdentifier(
                "username not exists",
                userWithTenantAndUsernameJohn.tenant.identifier
        )
        then:
        user == null

        when:
        user = service.findUserByUsernameAndTenantIdentifier(
                userWithTenantAndUsernameJohn.username,
                "tenantIdentifier not exists"
        )
        then:
        user == null
    }
}