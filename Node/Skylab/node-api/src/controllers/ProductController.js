const mongoose = require('mongoose');

const Product = mongoose.model('Product');

module.exports = {

    async index(req, res) {

        const { page = 1 } = req.query;

        // Para os parametros page e limit eu posso usar a short sintaxe que seria { page, limit }
        const products = await Product.paginate({}, { page: page, limit: 10 });

        return res.json(products);
    },

    async show(req, res) {

        const product = await Product.findById(req.params.id);

        return res.json(product);
    },

    async store(req, res) {

        const product = await Product.create(req.body);

        return res.json(product);
    },

    async update(req, res) {

        const product = await Product.findByIdAndUpdate(req.params.id, req.body, { new: true });

        return res.json(product);
    },

    async destroy(req, res) {

        await Product.findByIdAndRemove(req.params.id);

        return res.send();
    }
};