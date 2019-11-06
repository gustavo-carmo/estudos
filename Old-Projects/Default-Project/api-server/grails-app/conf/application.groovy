

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'bridge.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'bridge.UserRole'
grails.plugin.springsecurity.authority.className = 'bridge.Role'

grails.plugin.springsecurity.rest.login.usernamePropertyName='credentials'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/api/login',      filters: 'restAuthenticationFilter'],
        [pattern: '/api/**',         filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter']
]

// Optimistic approach (restrict access by URL only) to allow 'OPTIONS' access for CORS
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false

//grails.plugin.springsecurity.roleHierarchy = '''
//   ROLE_ADMIN > ROLE_USER
//'''

// TODO - definir uma melhor estratégia
grails.plugin.springsecurity.rest.token.storage.jwt.expiration = 604800

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "suporte1@avatek.com.br"
        password = "Avatek@1941"
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.socketFactory.port":"465",
                 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback":"false"]
    }
}