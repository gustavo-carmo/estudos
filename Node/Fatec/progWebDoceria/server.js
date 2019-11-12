const express = require('express');
const mongoose = require('mongoose');
const config = require('./server-config.js');
const requireDir = require('require-dir');
const bodyParser = require('body-parser');

const app = express();

//conecta no banco
mongoose.connect(
    config.mongodbUrl,
    { 
        useNewUrlParser: true,
        useUnifiedTopology: true
    }
);

requireDir('./src/models');

app.use(function (req, res, next) {
    res.header("Acess-Control-Allow-Origin", "*");
    res.header("Acess-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
    res.header("Acess-Control-Allow-Headers", "Orign, X-Requested-With, Content-Type, Accept");
    next();
});

app.get('/', (req, res) => {
    res.json({"message": `Seja bem vindo a API ${config.apiName} versão ${config.apiVersion}`})
});

//app.use('/api', require("./src/routes/routes.js"));

app.get('*', (req, res) => {
    res.json({
        "code": "400",
        "message": "A rota solicitada não foi encontrada"
    });
});

if(require.main === module) {

    app.listen(config.serverPort, () => {
        console.log('Servidor WEB Online . . . ');
        console.log(`Aplicação ${config.apiName}, rodando na porta ${config.serverPort}`)
    });
}
