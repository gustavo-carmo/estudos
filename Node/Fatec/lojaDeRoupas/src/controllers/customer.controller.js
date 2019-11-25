const Customer = require('../models/customer.model.js');

exports.create = (req, res) => {
    if(!req.body) {
        
        return res.status(400).send({
            message: "Conteúdo para criar o Cliente não pode estar vazio!"
        });
    }

    const customer = new Customer(req.body);

    customer.save()
    .then(data => {
        res.send(data);
    }).catch(err => {        
        
        res.status(500).send({
            message: err.message || "Ocorreu algo errado ao salvar o novo cliente."
        });
    });
};

exports.findAll = (req, res) => {
    Customer.find()
    .sort({nome:1})
    .then(customers => {
        res.send(customers);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Ocorreu algo errado ao obter os clientes do Banco de Dados."
        });
    });
};

exports.findOne = (req, res) => {
    Customer.findById(req.params.customerId)
    .then(customer => {
        if(!customer) {
            return res.status(404).send({
                message: "Cliente não encontrado com o ID " + req.params.customerId
            });            
        }
        res.send(customer);
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "Cliente não encontrado com o ID " + req.params.customerId
            });                
        }
        return res.status(500).send({
            message: "Aconteceu algo errado ao obter o Cliente com o id " + req.params.customerId
        });
    });
};

exports.update = (req, res) => {
       
    if(!req.body) {
        return res.status(400).send({
            message: "Conteúdo para alterar o cliente não pode estar vazio"
        });
    }

    Customer.findByIdAndUpdate(req.params.customerId, 
        req.body, {new: true})
    .then(customer => {
        if(!customer) {
            return res.status(404).send({
                message: "Cliente não encontrado com o Id " + req.params.customerId
            });
        }
        res.send(customer);
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "Cliente não encontrado com o Id " + req.params.customerId
            });                
        }
        return res.status(500).send({
            message: "Aconteceu algo errado ao tentar alterar o cliente com o Id " + req.params.customerId
        });
    });
};

exports.delete = (req, res) => {    
    Customer.findByIdAndRemove(req.params.customerId)
    .then(customer => {
        if(!customer) {
            return res.status(404).send({
                message: "Cliente não encontrado com o Id " + req.params.customerId
            });
        }
        res.send({message: "Cliente removido com sucesso!"});
    }).catch(err => {
        if(err.kind === 'ObjectId' || err.name === 'NotFound') {
            return res.status(404).send({
                message: "Cliente não encontrado com o Id " + req.params.customerId
            });                
        }
        return res.status(500).send({
            message: "Não foi possível apagar o Cliente com o Id " + req.params.customerId
        });
    });
};
