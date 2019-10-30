const express = require('express');
const config = require('./server-config.js');
const mongoose = require('mongoose');

//Conecta no banco
mongoose.connect(
    config.mongodbUrl,
    { 
        useNewUrlParser: true,
        useUnifiedTopology: true 
    }
);

require('./src/models/Product.js')

//Iniciando o APP
const app = express();

//Rota Raiz
app.get('/', (req, res) => {
    res.send(`
        Hello World
    `);
});

//Rota não encontrada
app.get('*', (req, res) => {
    return res.send(config.errorResponseNotFound);
})

app.listen(config.serverPort);