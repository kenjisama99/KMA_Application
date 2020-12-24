'use strict';

const express    = require('express');        
const app        = express();                
const bodyParser = require('body-parser');
const logger 	 = require('morgan');
const router 	 = express.Router();
const port 	     = process.env.PORT || 3000;

app.use(bodyParser.json());
app.use(logger('dev'));

require('./routes')(router);
app.use('/', router);

//app.listen(port);

var server = require("http").createServer(app);
var io = require("socket.io")(server);
//var ioServer = SocketIO(server);
var fs = require("fs");
server.listen(port);

console.log(`App Runs on ${port}`);
server.on('connection', function(socket){

    //console.log('New server connection ' + socket.address().address);

    // when the server recieves data, send it to the connected socket clients

});

io.sockets.on('connection', function (socket) {
	
    console.log("Co nguoi connect ne "+socket.id);
    
    // io.sockets.emit('serverguitinnhan', { noidung: "okbaby" });
    
    // socket.on('servernhantinnhan', function (data) {
    //   // emit toi tat ca moi nguoi
    //   io.sockets.emit('serverguitinnhan', { noidung: data });
      
    //   // emit tới máy nguoi vừa gửi
    //   socket.emit('serverguitinnhan', { noidung: data });
    // });
    
  });