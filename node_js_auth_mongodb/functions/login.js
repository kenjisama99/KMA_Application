'use strict';

const user = require('../models/user');
var mongoose = require('mongoose');

exports.loginUser = (phone, password, callback) => 
{
    //console.log(phone);
		user.find({'phone': phone}, (err,users)=>{
            //console.log(users);
            if (users.length == 0) {

				callback({'response':"User not exist",'res':false});

			} else {

				const db_password = users[0].password;

			    if (password === db_password) {

				    callback({'response':"Login Sucess",'res':true});

			    } else {

				    callback({'response':"Invalid Password",'res':false});
			    }

			}
        });

}