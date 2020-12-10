package com.example.kma_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.ChangePassTask;
import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.AsyncTask.LoginTask;
import com.example.kma_application.Models.InfoModel;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.Transport;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Manager;
import com.github.nkzawa.socketio.client.Socket;


import java.net.URISyntaxException;

public class user extends AppCompatActivity {
    Button btLogout, btChangePassword;
    EditText txtName, txtPhone, txtId;
    Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btLogout = (Button)findViewById(R.id.buttonLogout);
        btChangePassword = (Button)findViewById(R.id.buttonChangePass);
        txtPhone = (EditText)findViewById(R.id.editTextPersonPhone);
        txtId = (EditText)findViewById(R.id.editTextPersonID);
        txtName = (EditText)findViewById(R.id.editTextPersonName);
        {
            try {
                mSocket = IO.socket("http://192.168.1.68:3000/");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        mSocket.connect();

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("TAG", "Socket Connected!");
                mSocket.disconnect();
            }

        });

        mSocket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Transport transport = (Transport) args[0];
                transport.on(Transport.EVENT_ERROR, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Exception e = (Exception) args[0];
                        Log.e("Socket_Mess", "Transport error " + e);
                        e.printStackTrace();
                        e.getCause().printStackTrace();
                    }
                });
            }
        });

        loadInfos();

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLogout();
            }
        });

        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePass();
            }
        });
    }
    String infoJson(String phone) {
        return "{\"phone\":\"" + phone +"\"}";
    }
    private void loadInfos(){

        Intent data = getIntent();
        String phone = data.getStringExtra("phone");
        LoadInfosTask loadInfosTask = new LoadInfosTask(this, txtName, txtPhone, txtId);
        //loadInfosTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        loadInfosTask.execute(infoJson(phone));

    }

    private void onClickChangePass() {
        Intent data = getIntent();
        String phone = data.getStringExtra("phone");
        Intent changePassActivity = new Intent(this,changePassword.class);
        changePassActivity.putExtra("phone",phone);
        startActivity(changePassActivity);
    }

    private void onClickBtLogout() {
        Intent loginActivity = new Intent(this,login.class);
        startActivity(loginActivity);
        finish();
    }
}