const express = require('express');
const mongoose = require('mongoose');
const config = require('./server-config.js');
const requireDir = require('require-dir');

const app = express();
app.use(express.json());

mongoose.connect(config.mongoUrl, { 
    useNewUrlParser: true,
    useUnifiedTopology: true
});
requireDir('./src/models');

app.use('/api', require('./src/routes.js'));

app.get('*', (req, res) => {
    res.json(config.resNotFound);
});

app.listen(config.serverPort);