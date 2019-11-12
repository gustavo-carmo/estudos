const express = require('express');
const routes = express.Router();

const OrderController = require('./controllers/OrderController');

routes.get('/orders', OrderController.index);

module.exports = routes;