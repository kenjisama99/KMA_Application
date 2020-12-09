package com.example.kma_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoginTask;

public class login extends AppCompatActivity {

    EditText txtPhone,txtPassword;
    Button btLogin;


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        
        //Init view
        txtPhone = (EditText)findViewById(R.id.editTextPhone);
        txtPassword = (EditText)findViewById(R.id.editTextTextPassword);
        btLogin = (Button)findViewById(R.id.buttonLogin);
        
        //Event handle
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLogin();
            }
        });
        
        
    }


    private void onClickBtLogin() {
        loginUser(txtPhone.getText().toString().trim(),
                txtPassword.getText().toString().trim());
    }


    // test data
    String userJson(String phone, String password) {
        return "{\"phone\":\"" + phone + "\","
                +"\"password\":\"" + password +"\"}";
    }
    private void loginUser(String phone, String password) {
        if (TextUtils.isEmpty(phone)){

            Toast.makeText(this,"Số điện thoại không được để trống",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"Mật khẩu không được để trống",Toast.LENGTH_LONG).show();
            return;
        }
        LoginTask loginTask = new LoginTask(this, phone);
        loginTask.execute(userJson(phone,password));

    }
}