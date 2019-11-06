package loadData.DevPart03

import br.com.careman.domain.ProfileSettings
import br.com.careman.domain.ProfileStatus
import br.com.careman.domain.StepStatus
import br.com.careman.groovy.enums.Profile
import br.com.careman.groovy.enums.WorkflowType

ProfileSettings.withTransaction {

    def profileSettings = new ProfileSettings(profile: Profile.ADMINISTRATOR)

    def list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Aprovado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Asignada")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Bodega Baja")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Bodega Egreso")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado (Pendente Autorização)")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Conferência no Destino")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "DESPACHADO BAD")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "DESPACHADO GOOD")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Despachado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Em Campo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Em Transito")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "En Reparo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado Manut")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Entregado NOK")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Entregado OK")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado (Sem Troca)")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "INGRESSO BAD")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "INGRESSO GOOD")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Ingresso de Equiptos")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Irreparable")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Nova")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "PRÉ-DESPACHO")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Para Aprovação")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Para Encerramento")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Pre-Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Preparação")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA Aprobado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA Rechazado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA_Lab")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "RR Abierta")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Recebido OK PS")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Recusado no Destino")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Reencaminhado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Reparacion OK")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors



    profileSettings = new ProfileSettings(profile: Profile.ATTENDANT)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.CONTRACTOR)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Aprovado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Para Aprovação")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Pre-Finalizado")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.LABORATORY_SUPERVISOR)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Asignada")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Bodega Egreso")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Conferência no Destino")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "En Reparo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado Manut")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado (Sem Troca)")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Irreparable")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA Aprobado")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA Rechazado")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA_Lab")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "RR Abierta")],
            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Reparacion OK")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.LABORATORY_TECHNICIAN)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Asignada")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Bodega Egreso")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Conferência no Destino")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "En Reparo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado Manut")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado (Sem Troca)")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Irreparable")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA Aprobado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA Rechazado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "QA_Lab")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "RR Abierta")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Reparacion OK")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.LOGISTIC_TECHNICIAN)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Bodega Baja")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "DESPACHADO BAD")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "DESPACHADO GOOD")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Despachado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Em Transito")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Entregado NOK")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Entregado OK")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Nova")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "PRÉ-DESPACHO")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Preparação")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Recebido OK PS")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SHIPMENT_ORDER, "Recusado no Destino")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.QUALITY_TECHNICIAN)

    list = [

            [edit: false, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "En Reparo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Irreparable")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.REPAIR_ORDER, "Reparacion OK")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.SERVICE_PROVIDER_ATTENDANT)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors




    profileSettings = new ProfileSettings(profile: Profile.SERVICE_PROVIDER)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Aprovado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado (Pendente Autorização)")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Em Campo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Para Aprovação")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Para Encerramento")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Pre-Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Reencaminhado")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors





    profileSettings = new ProfileSettings(profile: Profile.SERVICE_PROVIDER_TECHNICIAN)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors





    profileSettings = new ProfileSettings(profile: Profile.SUPERVISOR)

    list = [

            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Aprovado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Cancelado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Em Campo")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Encaminhado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Nova")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Para Aprovação")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Pre-Finalizado")],
            [edit: true, status: StepStatus.findByWorkflowTypeAndName(WorkflowType.SERVICE_ORDER, "Reencaminhado")],

    ]

    list.each {
        profileSettings.addToStatus(new ProfileStatus(status: it.status, edit: it.edit))
    }

    println profileSettings.validate()
    println profileSettings.save()
    println profileSettings.errors

}