const mongoose = require('mongoose');

const CustomerSchema = new mongoose.Schema({
    name: {
        type: String,
        minlength: [2, 'O nome é muito curto'],
        maxlength: [100, 'O nome é muito longo'],
        required: [true, 'O nome do cliente é obrigatório'],
        unique: [true, 'O nome do cliente é unico']
    },
    email: {
        type: String,
        minlength: [2, 'O e-mail é muito curto'],
        maxlength: [100, 'O e-mail é muito longo']
    },
    cellphone: {
        type: String,
        minlength: [10, 'O telefone celular é muito curto'],
        maxlength: [11, 'O telefone celular é muito longo'],
        required: [true, 'O telefone celular é obrigatório']
    },
    cpf: {
        type: String,
        unique: [true, 'O CPF do cliente é unico'],
        validate: {
            validator: function(cpf) { 
                return /^([0-9]{3}\.?[0-9]{3}\.?[0-9]{3}\-?[0-9]{2})$/.test(cpf);
              },
              message: props => props.value +' não é um CPF válido!'
        }
    },
    cep: {
        type: String,
        minlength: [8, 'O CEP é muito curto'],
        maxlength: [10, 'O CEP é muito longo']
    }
});

CustomerSchema.index(
    {
        name: 'text',
        cpf: 'text'
    }, 
    {
        weights: {
            name: 2,
            cpf: 1
        },
    }
);

module.exports = mongoose.model('Customer', CustomerSchema);