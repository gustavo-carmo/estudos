package restaurantetocomfome

class Prato extends Produto {

    Integer quantidadePessoas

    static constraints = {
        quantidadePessoas min: 1
    }

    static mapping = {
        discriminator column: "tipo", value: "PRATO"
    }
}
