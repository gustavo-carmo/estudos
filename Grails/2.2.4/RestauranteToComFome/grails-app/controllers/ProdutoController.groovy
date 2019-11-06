

class ProdutoController {

    def index() {
        render(view: "/produto/index", model: [produtos: Produto.list()])
    }

    def adicionar() {

        Produto produto = new Produto()
        produto.preco = 0
        produto.estoque = new Estoque()
        produto.estoque.quantidade = 0
        produto.estoque.quantidadeMinima = 0

        render(template: "/produto/form", model: [produto: produto])
    }

    def salvar() {

        Produto produto
        if (params.id) {
            produto = Produto.get(params.id)
        } else {
            produto = new Produto()
            produto.estoque = new Estoque()
        }

        produto.nome = params.nome
        produto.preco = params.preco.toDouble()
        produto.estoque.quantidade = params.quantidade.toInteger()
        produto.estoque.quantidadeMinima = params.quantidadeMinima.toInteger()
        produto.validate()

        if(!produto.hasErrors()) {
            produto.save(flush: true)
            render ("Salvou com sucesso")
        } else {
            render ("Ops... deu pau!")
        }

    }

    def alterar() {
        Produto produto = Produto.get(params.id)
        render(template: "/produto/form", model: [produto: produto])
    }

    def lista () {
        render(template: "/produto/lista", model: [produtos: Produto.list()])
    }

    def excluir () {
        println "ID -> ${params.id}"

        /*Produto.get(Long.valueOf(params.id))?.delete(flush: true)*/
        render(template: "/produto/lista", model: [produtos: Produto.list()])
    }

}
