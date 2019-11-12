const express = require("express");
const config = require("./server-config.js");
const mongoose = require("mongoose");
const requireDir = require("require-dir");

//Iniciando o app
const app = express();

//Conexão com o Mongoose
mongoose.connect(
    config.mongodbUrl,
    { 
        useNewUrlParser: true,
        useUnifiedTopology: true
    }
);
requireDir("./src/models");

const Product = mongoose.model('Product');

//Rotas
app.use('/api', require("./src/routes.js"));

//Página para qual será redirecionado caso não encontre a rota
app.get("*", (req, res) => {
    res.send({
        meta: {
            code: 400
        },
        data: {
            error: {
                code: "page.not.found",
                message: "A API solicitada não foi encontrada"
            }
        }
    });
});

app.listen(config.serverPort);