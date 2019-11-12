const mongoose = require('mongoose');

const ProductSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        maxlength: 50
    },
    value: {
        type: Number,
        required: true
    },
    description: {
        type: String,
        maxlength: 500
    }
});