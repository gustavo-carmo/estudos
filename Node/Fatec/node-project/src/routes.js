const express = require('express');

const routes = express.Router();

routes.get('/', (req, res) => {

    /*
    Product.create({
        title: 'React Native',
        description: 'Build native apps with React',
        url: 'http://github.com/facebook/react-native'
    });

    Product.findByIdAndDelete("5d81a243b891ac2a8ca9391b");
*/

    return res.send(`
        <h1>Hello World</h1>
        Testando Hot Reloading
    `);
});

module.exports = routes;