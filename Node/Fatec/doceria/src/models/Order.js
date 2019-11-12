const mongoose = require('mongoose');

const OrderSchema = new mongoose.Schema({

    product: {
        type: String,
        required: true,
        maxlength: 50
    },
    quantity: {
        type: Number,
        required: true
    },
    value: {
        type: Number,
        required: true
    },
    total: {
        type: Number
    },
    description: {
        type: String,
        maxlength: 500
    },
    createdAt: {
        type: Date,
        default: Date.now
    },
    deliveryDate: {
        type: Date
    }
});

mongoose.model('Order', OrderSchema);