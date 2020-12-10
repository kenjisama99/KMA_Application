'use strict';

const auth = require('basic-auth');

const login = require('./functions/login');
const password = require('./functions/password');
const profile = require('./functions/profile');

module.exports = router => {

	router.get('/', (req, res) => res.end('This is default root end !'));

	router.post('/login', (req, res, next) => {
         var post_data = req.body;
         //console.log(post_data);
        // var phone = post_data.phone;
        // var pass

			login.loginUser(req.body.phone, req.body.password, (found) => {
                console.log(found);
			    res.json(found);
            });

	});
	router.post('/getInfo', (req, res, next) => {
         var post_data = req.body;
         //console.log(post_data);
        // var phone = post_data.phone;
        // var pass

        profile.checkInfo(req.body.phone,(found) => {
                console.log(found);
			    res.json(found);
            });

	});


	router.post('/changePass', (req,res) => {

			const oldPassword = req.body.password;
			const newPassword = req.body.newPassword;

				password.changePassword(req.body.phone, oldPassword, newPassword, (found) =>{
                    console.log(found);
			        res.json(found);
                });					
    });
    
}