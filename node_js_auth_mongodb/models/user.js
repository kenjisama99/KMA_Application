'use strict';

const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const userSchema = mongoose.Schema({ 
	phone 		: String,
	password	: String
}, {collection: 'users'});

mongoose.Promise = global.Promise;
mongoose.connect('mongodb+srv://kenjisama:ayanami@cluster0.akt6g.mongodb.net/node-android?retryWrites=true&w=majority',
    {   
        useNewUrlParser: true,
        useUnifiedTopology: true
    });

module.exports = mongoose.model('user', userSchema);