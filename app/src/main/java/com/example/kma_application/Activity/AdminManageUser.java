package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.DeleteUserTask;
import com.example.kma_application.AsyncTask.FindUserTask;
import com.example.kma_application.AsyncTask.ModifyUserTask;
import com.example.kma_application.R;

public class AdminManageUser extends AppCompatActivity {
    EditText searchUser, txtName, txtPassword, txtPhone, txtEmail;
    Button btAddUser, btModifyUser, btDeleteUser, btAdminLogout, btDoneModify;
    ImageButton btSearch;
    String oldPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_user);

        searchUser = (EditText)findViewById(R.id.searchUser) ;
        txtName = (EditText)findViewById(R.id.editTextName) ;
        txtPassword = (EditText)findViewById(R.id.editTextTextPersonName6) ;
        txtPhone = (EditText)findViewById(R.id.editTextPhoneNumber) ;
        txtEmail = (EditText)findViewById(R.id.editTextEmailUser);

        btAddUser = (Button)findViewById(R.id.buttonAddUser);
        btModifyUser  = (Button)findViewById(R.id.buttonModifyUser);
        btDeleteUser = (Button)findViewById(R.id.buttonDeleteUser);
        btDoneModify = (Button)findViewById(R.id.btDoneModify);
        btAdminLogout = (Button)findViewById(R.id.btAdminLogout);

        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddUser();
            }
        });

        btModifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickModifyUser();
            }
        });

        btDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDeleteUser();
            }
        });

        btDoneModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDoneModifyUser();
            }
        });

        btAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogout();
            }
        });

        btSearch = (ImageButton) findViewById(R.id.imageButton);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSearchUser();
            }
        });


    }

    private void onClickLogout() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    private void onClickSearchUser() {
        cleanInfo();

        String phone = searchUser.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            String notification ="Vui lòng nhập tiêu đề";
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
        }
        else {
            new FindUserTask(
                    this,
                    phone,
                    txtName,
                    txtPassword,
                    txtPhone,
                    txtEmail
            ).execute();
        }
    }

    private void onClickDeleteUser() {
        String phone = txtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            String notification ="Vui lòng tìm kiếm tài khoản cần xóa";
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
        }
        else {
            new DeleteUserTask(
                    this,
                    phone,
                    txtName,
                    txtPassword,
                    txtPhone,
                    txtEmail
            ).execute();
        }
    }

    private void onClickModifyUser() {
        oldPhone = txtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(oldPhone)) {
            String notification ="Vui lòng tìm kiếm tài khoản cần sửa";
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
        }
        else {
            switchToModifyMode();
        }
    }
    private void onClickDoneModifyUser() {
        String  name = txtName.getText().toString().trim();
        String phone = txtPhone.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        String notification = "";
        boolean OK = true;

        if (TextUtils.isEmpty(name)) {
            notification ="Vui lòng nhập tên";
            OK = false;
        }if (TextUtils.isEmpty(phone)) {
            notification = "Vui lòng nhập số điện thoại";
            OK = false;
        }if (TextUtils.isEmpty(email)) {
            notification = "Vui lòng nhập email";
            OK = false;
        }if (TextUtils.isEmpty(password)) {
            notification = "Vui lòng nhập mật khẩu";
            OK = false;
        }

        if ( !OK){
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
            return;
        }else {
            new ModifyUserTask(
                    this,
                    txtName,
                    txtPassword,
                    txtPhone,
                    txtEmail,
                    btAddUser,
                    btModifyUser,
                    btDeleteUser,
                    btAdminLogout,
                    btDoneModify,
                    btSearch,
                    oldPhone).execute();
        }
    }

    private void onClickAddUser() {
        Intent addUser = new Intent(this, AdminAddUser.class);
        startActivity(addUser);
    }
    private void cleanInfo(){
        txtPhone.setText("");
        txtEmail.setText("");
        txtName.setText("");
        txtPassword.setText("");
    }
    public void switchToModifyMode(){
        btAddUser.setVisibility(View.INVISIBLE);
        btModifyUser.setVisibility(View.INVISIBLE);
        btDeleteUser.setVisibility(View.INVISIBLE);
        btAdminLogout.setVisibility(View.INVISIBLE);
        btDoneModify.setVisibility(View.VISIBLE);
        btSearch.setVisibility(View.INVISIBLE);
        txtPassword.setEnabled(true);
        txtName.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
    }

    public void exitModifyMode(){
        btAddUser.setVisibility(View.VISIBLE);
        btModifyUser.setVisibility(View.VISIBLE);
        btDeleteUser.setVisibility(View.VISIBLE);
        btAdminLogout.setVisibility(View.VISIBLE);
        btDoneModify.setVisibility(View.INVISIBLE);
        btSearch.setVisibility(View.VISIBLE);
        txtPassword.setEnabled(false);
        txtName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
    }
}