

class Pedido {

    Date dataHora
    Double valorTotal

    Cliente cliente

    static hasMany = [itens: ItemPedido]

    static constraints = {
        valorTotal min: Double.valueOf(0)
        cliente nullable: false
    }

    static mapping = {
        cliente column: "id_cliente"
    }
}
