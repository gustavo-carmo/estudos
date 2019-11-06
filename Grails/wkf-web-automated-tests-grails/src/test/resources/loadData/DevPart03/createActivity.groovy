package loadData.DevPart03

import br.com.careman.domain.Activity

Activity.withTransaction {

    def activity = new Activity(name: "Troca de Componente")
    println activity.validate()
    println activity.save()
    println activity.errors

    activity = new Activity(name: "Troca de Parte")
    println activity.validate()
    println activity.save()
    println activity.errors

    activity = new Activity(name: "Reparo")
    println activity.validate()
    println activity.save()
    println activity.errors

}