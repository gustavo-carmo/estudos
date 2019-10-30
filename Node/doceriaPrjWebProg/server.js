const express = require('express');
const mongoose = require('mongoose');

const app = express();

mongoose.connect(
    "mongodb://localhost:27017/doceria",
    {
        useUnifiedTopology: true,
        useNewUrlParser: true
    }
)

app.get('/', (req, res) => {
    res.send(`
        <h1> Teste sem olhar códigos alheios</h1>
    `);
});

app.listen(3000);