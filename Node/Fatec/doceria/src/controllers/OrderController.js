const mongoose = require('mongoose');

const Order = mongoose.model('Order');

module.exports = {

    async index(req, res) {

        const orders = await Order.find();

        return res.json(orders);
    }
};