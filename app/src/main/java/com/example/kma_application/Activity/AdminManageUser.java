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
import com.example.kma_application.R;

public class AdminManageUser extends AppCompatActivity {
    EditText searchUser,editTextName, editTextTextPersonName6,
            editTextPhoneNumber,editTextEmailUser;
    Button btAddUser, btModifyUser, btDeleteUser, btAdminLogout, btDoneModify;
    ImageButton btSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_user);

        searchUser = (EditText)findViewById(R.id.searchUser) ;
        editTextName = (EditText)findViewById(R.id.editTextName) ;
        editTextTextPersonName6 = (EditText)findViewById(R.id.editTextTextPersonName6) ;
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber) ;
        editTextEmailUser = (EditText)findViewById(R.id.editTextEmailUser);

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
                    editTextName,
                    editTextTextPersonName6,
                    editTextPhoneNumber,
                    editTextEmailUser
            ).execute();
        }
    }

    private void onClickDeleteUser() {
        String phone = editTextPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            String notification ="Vui lòng tìm kiếm tài khoản cần xóa";
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
        }
        else {
            new DeleteUserTask(
                    this,
                    phone,
                    editTextName,
                    editTextTextPersonName6,
                    editTextPhoneNumber,
                    editTextEmailUser
            ).execute();
        }
    }

    private void onClickModifyUser() {
        String phone = editTextPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            String notification ="Vui lòng tìm kiếm tài khoản cần sửa";
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
        }
        else {
            new DeleteUserTask(
                    this,
                    phone,
                    editTextName,
                    editTextTextPersonName6,
                    editTextPhoneNumber,
                    editTextEmailUser
            ).execute();
        }
    }
    private void onClickDoneModifyUser() {

    }

    private void onClickAddUser() {
        Intent addUser = new Intent(this, AdminAddUser.class);
        startActivity(addUser);
    }
    private void cleanInfo(){
        editTextPhoneNumber.setText("");
        editTextEmailUser.setText("");
        editTextName.setText("");
        editTextTextPersonName6.setText("");
    }
}