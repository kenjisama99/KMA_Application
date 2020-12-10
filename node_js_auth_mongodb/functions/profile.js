'use strict';

const info = require('../models/info');
var mongoose = require('mongoose');

exports.checkInfo = (phone, callback) => 
{
    //console.log(phone);
		info.find({'phone': phone}, (err,infos)=>{
            //console.log(infos);
            if (infos.length == 0) {

				callback({'response':"info not exist",'res':false});

			} else {
					console.log(infos[0]);
				    callback(infos[0]);

			}
        });

}