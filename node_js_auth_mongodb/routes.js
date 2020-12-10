'use strict';

const auth = require('basic-auth');
var fs = require('fs');
const path       = require('path');
const express    = require('express'); 
const login = require('./functions/login');
const password = require('./functions/password');
const profile = require('./functions/profile');

var dir = path.join(__dirname, '');

var mime = {
    html: 'text/html',
    txt: 'text/plain',
    css: 'text/css',
    gif: 'image/gif',
    jpg: 'image/jpeg',
    png: 'image/png',
    svg: 'image/svg+xml',
    js: 'application/javascript'
};

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
                //console.log(found);
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

    router.get('*', function (req, res) {
        var file = path.join(dir, req.path.replace(/\/$/, '/index.html'));
        //console.log(file);
        if (file.indexOf(dir + path.sep) !== 0) {
            return res.status(403).end('Forbidden');
        }
        var type = mime[path.extname(file).slice(1)] || 'text/plain';
        //console.log(file);
        //console.log(type);
        var s = fs.createReadStream(file);
        s.on('open', function () {
            res.set('Content-Type', type);
            s.pipe(res);
        });
        s.on('error', function () {
            res.set('Content-Type', 'text/plain');
            res.status(404).end('Not found');
        });
    });
    
}