const express = require('express')
var app = express()

app.use(express.static('public'));

app.listen(3001, function(){
    console.log(`\n Servidor Web do Front End rodando na porta 3001`)
})