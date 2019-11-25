module.exports = (app) => {
    const produtos = require('../controllers/produto.controller.js');

    // Cria um novo produto
    app.post('/produtos', produtos.create)
    // Lista todos os produtos
    app.get('/produtos', produtos.findAll)
    //Lista apenas um produto pelo ID
    app.get('/produtos/:produtoId', produtos.findOne)
    //Lista os produtos pelo texto
    app.get('/produtos/texto/:produtoTexto', produtos.findByTexto)
    //Altera um produto existente pelo Id
    app.put('/produtos/:produtoId', produtos.update)
    //Apaga um produto existente pelo Id
    app.delete('/produtos/:produtoId', 
                produtos.delete) 
}