module.exports = (app) => {

    const products  = require('./src/controllers/Product.js');

    app.post('/products', products.create);
};