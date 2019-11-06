package restaurantetocomfome

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

    def alterar() {
        Produto produto = Produto.get(params.id)
        render(template: "/produto/form", model: [produto: produto])
    }

    def salvar() {

    }
}
