'use strict';

const user = require('../models/user');

exports.changePassword = (phone, password, newPassword, callback) => 
{

		user.find({ phone: phone }, (err,users)=> {

			let user = users[0];
			const db_password = user.password;

			if (password === db_password) {

				user.password = newPassword;

                user.save();
                
                callback({'response':"Password Sucessfully Changed",'res':true});

			} else {

				callback({'response':"Old Passwords do not match. Try Again !",'res':false});
			}
		});

}