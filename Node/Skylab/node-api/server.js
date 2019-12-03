const express = require('express');
const mongoose = require('mongoose');
const requireDir = require('require-dir');
//var path = require('path'); projetos html
const config = require('./server-config.js');
const cors = require('cors');

// Iniciando o App
const app = express();

app.use(express.json());
app.use(cors());

// Iniciando o DB
mongoose.connect(
    'mongodb://localhost:27017/nodeapi', 
    {
        useNewUrlParser: true,
        useUnifiedTopology: true
    }
);

requireDir('./src/models');

const Product = mongoose.model('Product');

// Importando as rotas
app.use('/api', require('./src/routes'));

//Rota default de recurso não encontrado
app.get('*', (req, res) => {
    
    //res.send({"error-code": 404, "message": "Resource not found"});
    res.send(config.notFoundReturn);
    //somente em projetos web
    //res.sendFile(path.join(__dirname + '/error-page/index.html'));
});

app.post('*', (req, res) => {
    
    res.send(config.notFoundReturn);
});

// Subindo a aplicação
app.listen(config.serverPort, (req, res) => {

    console.log("Servidor conectado!");
});