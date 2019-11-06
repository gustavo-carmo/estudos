package business.services

import bridge.GrailsHelperService
import business.serviceOrders.AppConfigService
import business.enums.LoginField
import business.Tenant
import business.ConfigCommand
import bridge.exception.AppValidationException
import grails.test.mixin.TestFor
import org.springframework.validation.Errors
import business.InitializationService
import business.RepositoryService
import spock.lang.Shared
import spock.lang.Specification
import bridge.misc.ErrorMessage
import bridge.misc.ResultService

@TestFor(InitializationService)
class InitializationServiceSpec extends Specification {

    @Shared
    ConfigCommand commandMocked
    @Shared
    RepositoryService repositoryServiceMocked
    @Shared
    GrailsHelperService grailsHelperServiceMocked
    @Shared
    AppConfigService appConfigServiceMocked
    @Shared
    Errors errorsMocked
    @Shared
    String urlSupportPortal
    @Shared
    Tenant tenantMocked
    @Shared
    String tenantIdentifier
    @Shared
    LoginField loginField

    def setup() {
        0 * _
        commandMocked = Mock(ConfigCommand)
        tenantMocked = Mock(Tenant)
        repositoryServiceMocked = Mock(RepositoryService)
        service.repositoryService = repositoryServiceMocked
        grailsHelperServiceMocked = Mock(GrailsHelperService)
        service.grailsHelperService = grailsHelperServiceMocked
        appConfigServiceMocked = Mock(AppConfigService)
        service.appConfigService = appConfigServiceMocked
        errorsMocked = Mock(Errors) {
            getAllErrors() >> ['Errors']
        }
        urlSupportPortal = "http://api.com.br/wkf-1"
        tenantIdentifier = '12312123'
        loginField = LoginField.DOCUMENT_NUMBER
    }

    def cleanup() {
    }

    void "Deverá retornar as configurações com sucesso quando informo uma urlSupportPortal relacionada a um tenant"() {

        given:
        ResultService resultService
        def config = [
                tenant_identifier: tenantIdentifier,
                login_field      : loginField.toString()
        ]

        when:
        resultService = service.config(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUrl() >> urlSupportPortal
        1 * service.repositoryService.findTenantByUrlSupportPortal(urlSupportPortal) >> {
            return tenantMocked
        }
        1 * tenantMocked.getIdentifier() >> tenantIdentifier
        1 * tenantMocked.getLoginField() >> loginField

        and:
        resultService.isValid()
        resultService.data == config
    }

    void "Devera dar erro quando ConfigCommand for invalido"() {

        given:
        def resultService
        def ex


        when:
        service.config(null)

        then:
        ex = thrown(AppValidationException)
        ex.message == "commandGeneric.error.required"


        when:
        resultService = service.config(commandMocked)

        then:
        1 * commandMocked.validate() >> false
        1 * commandMocked.getErrors() >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar erro quando o tenant encontrado não tem um identifier"() {
        given:
        def resultService
        GroovyMock(ErrorMessage)

        when:
        resultService = service.config(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUrl() >> urlSupportPortal
        1 * service.repositoryService.findTenantByUrlSupportPortal(urlSupportPortal) >> {
            return tenantMocked
        }
        1 * tenantMocked.getIdentifier() >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "url",
                "configCommand.url.error.tenantIdentifierIsNull"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá dar erro quando o tenant encontrado não tem um loginField"() {
        given:
        def resultService
        GroovyMock(ErrorMessage)

        when:
        resultService = service.config(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUrl() >> urlSupportPortal
        1 * service.repositoryService.findTenantByUrlSupportPortal(urlSupportPortal) >> {
            return tenantMocked
        }
        1 * tenantMocked.getIdentifier() >> tenantIdentifier
        1 * tenantMocked.getLoginField() >> null
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "url",
                "configCommand.url.error.tenantLoginFieldIsNull"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }

    void "Deverá retornar as configurações com sucesso quando informo uma urlSupportPortal que não esta associada a um tenant"() {

        given:
        ResultService resultService
        def urlGeted = 'pa-admin.com.br'
        def urlAdm = 'pa-admin.com.br'
        def config = [
                tenant_identifier: null,
                login_field      : loginField.toString()
        ]

        when:
        resultService = service.config(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUrl() >> urlGeted
        1 * service.repositoryService.findTenantByUrlSupportPortal(urlGeted) >> {
            return null
        }
        1 * service.appConfigService.resolveAdminUrl() >> urlAdm
        1 * service.appConfigService.resolveAdminLoginField() >> loginField

        and:
        resultService.isValid()
        resultService.data == config
    }

    void "Deverá dar erro quando a Url enviado não for a URL de adm"() {
        given:
        def resultService
        def urlNoAdm = 'url.com.br'
        GroovyMock(ErrorMessage)

        when:
        resultService = service.config(commandMocked)

        then:
        1 * commandMocked.validate() >> true
        1 * commandMocked.getUrl() >> urlSupportPortal
        1 * service.repositoryService.findTenantByUrlSupportPortal(urlSupportPortal) >> null
        1 * service.appConfigService.resolveAdminUrl() >> urlNoAdm
        1 * service.grailsHelperService.resolveValidationErrors(
                commandMocked,
                "url",
                "configCommand.url.error.urlNotFound"
        ) >> errorsMocked

        and:
        !resultService.isValid()
    }
}
