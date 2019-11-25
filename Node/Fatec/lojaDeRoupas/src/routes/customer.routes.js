module.exports = (app) => {
    const Customers = require('../controllers/customer.controller.js');

    app.post('/customers', Customers.create)
    
    app.get('/customers', Customers.findAll)
    
    app.get('/customers/:customerId', Customers.findOne)
    
    app.put('/customers/:customerId', Customers.update)
    
    app.delete('/customers/:customerId', Customers.delete) 
}