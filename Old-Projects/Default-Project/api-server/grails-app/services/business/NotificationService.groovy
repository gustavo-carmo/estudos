package business

import grails.transaction.Transactional

@Transactional
class NotificationService {

    def utilService

    // TODO - (1*)
    // TODO - implement-me
    def serviceOrderCreated(String email, String name, String code, Date limitDate) {
        println "Enviar email para $email, nome $name sobre a criação código $code e data limite $limitDate"
//      TODO - MELHORAR MENSAGEM
        utilService.sendEmail(
                email,
                "Ordem de Serviço criada com sucesso",
                "<p>Prezado(a) Sr(a) $name.</p> " +
                "<p>A sua Ordem de Serviço foi criada com sucesso!</p>" +
                "<p>O codigo da Ordem de Serviço é $code, e a data limite para atendimento é ${limitDate.format("dd/MM/yyyy")}</p>"
        )
    }
}
