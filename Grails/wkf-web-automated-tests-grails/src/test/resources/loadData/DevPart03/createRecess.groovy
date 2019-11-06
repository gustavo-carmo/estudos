package loadData.DevPart03

import br.com.careman.domain.Recess
import br.com.careman.domain.address.City
import br.com.careman.domain.address.Country
import br.com.careman.domain.address.State

Recess.withTransaction {

    def recess = new Recess(name: "Confraternização Universal", month: 1, day: 1)
    println recess.validate()
    println recess.save()
    println recess.errors

    recess = new Recess(name: "Paixão de Cristo", year: 2013, month: 3, day: 29, country: Country.findByName("Brasil"))
    println recess.validate()
    println recess.save()
    println recess.errors

    recess = new Recess(name: "Revolução Constitucionalista de 1932", month: 7, day: 9, state: State.findByName("São Paulo"))
    println recess.validate()
    println recess.save()
    println recess.errors

    recess = new Recess(name: "Aniversário da Cidade de Recife", month: 3, day: 12, city: City.findByName("Recife"))
    println recess.validate()
    println recess.save()
    println recess.errors

    recess = new Recess(name: "Aniversário da Cidade de Novo Hamburgo", month: 4, day: 5, city: City.findByName("Novo Hamburgo"))
    println recess.validate()
    println recess.save()
    println recess.errors

}