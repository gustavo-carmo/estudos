package loadData.DevPart03

import br.com.careman.domain.Contractor
import br.com.careman.domain.ImportProcessFromTo
import br.com.careman.domain.Model
import br.com.careman.domain.ReasonForCancellation
import br.com.careman.domain.Service
import br.com.careman.domain.StepStatus

ImportProcessFromTo.withTransaction {

    def list = [

        [entityType: "CONTRACTOR", fromId: 1, toId: Contractor.findByAlias("BANRISUL").id],
        [entityType: "MODEL", fromId: 5,  toId: Model.findByName("BANJO").id],
        [entityType: "MODEL", fromId: 6,  toId: Model.findByName("I5100").id],
        [entityType: "MODEL", fromId: 9,  toId: Model.findByName("I8200").id],
        [entityType: "MODEL", fromId: 10, toId: Model.findByName("I7910").id],
        [entityType: "MODEL", fromId: 16, toId: Model.findByName("I5100").id],
        [entityType: "MODEL", fromId: 21, toId: Model.findByName("ACQUA").id],
        [entityType: "MODEL", fromId: 27, toId: Model.findByName("EFT930G").id],
        [entityType: "MODEL", fromId: 28, toId: Model.findByName("EFT930S").id],
        [entityType: "MODEL", fromId: 35, toId: Model.findByName("ICT220").id],
        [entityType: "MODEL", fromId: 42, toId: Model.findByName("IWL250").id],
        [entityType: "MODEL", fromId: 41, toId: Model.findByName("ICT250").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 11, toId: ReasonForCancellation.findByName("NOK - ENDERECO NAO LOCALIZADO").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 12, toId: ReasonForCancellation.findByName("NOK - SEM COMUNICACAO").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 13, toId: ReasonForCancellation.findByName("NOK - ESTABELECIMENTO FECHADO").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 14, toId: ReasonForCancellation.findByName("NOK - NAO AUTORIZADO PELO CLIENTE").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 15, toId: ReasonForCancellation.findByName("NOK - FALTA ASSINATURA DO CONTRATO").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 16, toId: ReasonForCancellation.findByName("NOK - INSCRICAO ESTADUAL INVALIDA").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 17, toId: ReasonForCancellation.findByName("NOK - CEP INVALIDO").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 18, toId: ReasonForCancellation.findByName("NOK - RAZAO SOCIAL INVALIDA").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 19, toId: ReasonForCancellation.findByName("NOK - EQUIPAMENTO NAO ESTA NO LOCAL").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 30, toId: ReasonForCancellation.findByName("NOK Contato EC, endereço divergente").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 31, toId: ReasonForCancellation.findByName("NOK Contato EC não autorizou instal").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 32, toId: ReasonForCancellation.findByName("NOK Contato cliente EC sem infraest").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 33, toId: ReasonForCancellation.findByName("NOK Contato EC Banco solicitou canc").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 34, toId: ReasonForCancellation.findByName("NOK Contato EC, POS de outro fornec").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 35, toId: ReasonForCancellation.findByName("NOK Chamado duplicado").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 36, toId: ReasonForCancellation.findByName("NOK CNPJ baixado no SINTEGRA").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 41, toId: ReasonForCancellation.findByName("PENDENTE NO ESTABELECIMENTO").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 42, toId: ReasonForCancellation.findByName("PENDENTE NA AGENCIA").id],
        [entityType: "REASON_FOR_CANCELLATION", fromId: 99, toId: ReasonForCancellation.findByName("NOK - OUTRO").id],
        [entityType: "SERVICE", fromId: 2, toId: Service.findByName("Instalação Venda").id],
        [entityType: "SERVICE", fromId: 3, toId: Service.findByName("Reconfiguração").id],
        [entityType: "SERVICE", fromId: 4, toId: Service.findByName("Desinstalação").id],
        [entityType: "SERVICE", fromId: 6, toId: Service.findByName("Cancelamento").id],
        [entityType: "SERVICE", fromId: 7, toId: Service.findByName("Troca de Tecnologia (geral)").id],
        [entityType: "SERVICE", fromId: 8, toId: Service.findByName("Troca de Tecnologia (geral)").id],
        [entityType: "SERVICE", fromId: 9, toId: Service.findByName("Instalação Venda").id],
        [entityType: "STEP_STATUS", fromId: 1, toId: StepStatus.findByName("Nova").id],
        [entityType: "STEP_STATUS", fromId: 2, toId: StepStatus.findByName("Cancelado").id],
        [entityType: "STEP_STATUS", fromId: 3, toId: StepStatus.findByName("Finalizado").id],

    ]

    def importProcessFromTo
    list.each {
        importProcessFromTo = new ImportProcessFromTo(contractor: Contractor.findByAlias("BANRISUL"), type:'REMESSA_SOLICITACAO_DE_SERVICOS', entityType: it.entityType, fromId: it.fromId, toId: it.toId)
        println importProcessFromTo.validate()
        println importProcessFromTo.save()
        println importProcessFromTo.errors
    }

}
