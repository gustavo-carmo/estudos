import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        // pattern = "%level %logger - %msg%n"
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

logger("bridge", INFO, ['STDOUT'], false)
logger("business", INFO, ['STDOUT'], false)

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            // pattern = "%level %logger - %msg%n"
            pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} --- %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)


    logger("org.springframework.security", ERROR, ['STDOUT'], false)
    logger("grails.plugin.springsecurity", ERROR, ['STDOUT'], false)
    logger("org.pac4j", ERROR, ['STDOUT'], false)

    logger("grails.app.services", INFO, ['STDOUT'], false)
    logger('grails.app.controllers', INFO, ['STDOUT'], false)

    // exibir queries do hibernate
    // logger 'org.hibernate.type.descriptor.sql.BasicBinder', TRACE, ['STDOUT']
    // logger 'org.hibernate.SQL', TRACE, ['STDOUT']
}

if (Environment.currentEnvironment == Environment.PRODUCTION && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)

    logger("org.springframework.security", ERROR, ['STDOUT'], false)
    logger("grails.plugin.springsecurity", ERROR, ['STDOUT'], false)
    logger("org.pac4j", ERROR, ['STDOUT'], false)

    logger("grails.app.services", INFO, ['STDOUT'], false)
    logger('grails.app.controllers', INFO, ['STDOUT'], false)
}
