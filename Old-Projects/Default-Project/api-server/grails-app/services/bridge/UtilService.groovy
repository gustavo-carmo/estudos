package bridge

import bridge.exception.AppValidationException
import bridge.utils.StringUtils
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.grails.web.json.JSONObject

@Transactional(readOnly = true)
class UtilService {

    def mailService

    // TODO - tests
    def sendEmail(String paramTo, String paramSubject, String paramBody) {

        log.info("sendEmail: to: ${paramTo}, subject: ${paramSubject}, body: ${paramBody}")

        if (!paramTo) {
            throw new AppValidationException("Email(s) is a required value. Multiples emails must be separated by comma")
        }

        List to = [paramTo]
        if (paramTo.contains(",")) {
            to = paramTo.split(",")
        }

        sendToManyEmails(to, paramSubject, paramBody)
    }

    // TODO - tests
    def sendToManyEmails(List emails, String paramSubject, String paramBody) {

        log.info("sendToManyEmails: to: ${emails}, subject: ${paramSubject}, body: ${paramBody}")
        println "sendToManyEmails: to: ${emails}, subject: ${paramSubject}, body: ${paramBody}"

        if (!emails) {
            throw new AppValidationException("Emails is a required value")
        }

        def invalidEmails = emails.findAll { !StringUtils.isValidEmail(it) }
        if (invalidEmails) {
            throw new AppValidationException("Emails has invalid values: ${invalidEmails.collect { "'${it}'" }}")
        }

        if (!paramSubject) {
            throw new AppValidationException("Subject is a required value")
        }

        if (!paramBody) {
            throw new AppValidationException("Body is a required value")
        }

        mailService.sendMail {
            async true
            multipart true
            to emails
            subject paramSubject
            html paramBody
        }
    }

    // TODO - test
//    TODO - verificar se sera utilizado uma string randomica ou sera pego de outra forma essa string
    String generateRandomString(int size) {
        //        return String.valueOf(Calendar.instance.timeInMillis)
        return StringUtils.randomString(size)
    }

    // TODO - Refector test
    JSONObject httpPostWithJsonDataAndReturnJSON(String url, Map data) {

        return httpPostWithJsonDataAndReturnJSON(url, null, data)
    }

    // TODO - precisa melhorar isso aqui, devolver um mapa com code e data (data pode ser text, json, xml...) será melhor
    JSONObject httpPostWithJsonDataAndReturnJSON(String url, Map header, Map data) {

        log.info "POST to url [${url}], with header[$header] and data [${data}]"

        def builder = new RestBuilder()
        def restResponse = builder.post(url) {
            if(header) {
                header.each { key, value ->
                    headers[key] = value
                }
            }
            if (data) {
                json {
                    data
                }
            }
        }

        if (restResponse.statusCode.is4xxClientError()) {
            throw new AppValidationException("Erro on do POST: code [${restResponse.statusCode}], text: [${restResponse.text}]")
        }

        return (JSONObject)restResponse.getJson()
    }

    // TODO - precisa melhorar isso aqui, devolver um mapa com code e data (data pode ser text, json, xml...) será melhor
    JSONObject httpGetAndReturnJSON(String url, Map header) {

        log.info("get url [$url], with header [$header]")

        def builder = new RestBuilder()
        def restResponse = builder.get(url) {
            if (header) {
                log.info("build header")
                header.each { key, value ->
                    headers[key] = value
                }
            }
        }
        log.info("return of get [$restResponse]")

        if (restResponse.statusCode.is4xxClientError()) {
            throw new AppValidationException("Erro on do GET: code [${restResponse.statusCode}], text: [${restResponse.text}]")
        }

        def ret = (JSONObject)restResponse.getJson()
        log.info("get return [$ret]")

        return ret
    }
}
