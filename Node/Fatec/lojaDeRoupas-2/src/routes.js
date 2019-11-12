const express = require('express');
const routes = express.Router();

const CustomerController = require('./controllers/CustomerController.js');

routes.get('/customers', CustomerController.index);
routes.get('/customers/:id', CustomerController.show);
routes.put('/customers/:id', CustomerController.update);
routes.post('/customers', CustomerController.store);
routes.delete('/customers/:id', CustomerController.destroy);

module.exports = routes;