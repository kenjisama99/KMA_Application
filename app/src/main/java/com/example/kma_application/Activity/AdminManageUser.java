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

import com.example.kma_application.AsyncTask.FindUserTask;
import com.example.kma_application.R;

public class AdminManageUser extends AppCompatActivity {
    EditText searchUser,editTextName, editTextTextPersonName6,
            editTextPhoneNumber,editTextEmailUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_user);

        searchUser = (EditText)findViewById(R.id.searchUser) ;
        editTextName = (EditText)findViewById(R.id.editTextName) ;
        editTextTextPersonName6 = (EditText)findViewById(R.id.editTextTextPersonName6) ;
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber) ;
        editTextEmailUser = (EditText)findViewById(R.id.editTextEmailUser);

        Button btModifyUser  = (Button)findViewById(R.id.buttonModifyUser);
        btModifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickModifyUser();
            }
        });
        
        Button btDeleteUser = (Button)findViewById(R.id.buttonDeleteUser);
        btDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDeleteUser();
            }
        });

        Button btaddUser = (Button)findViewById(R.id.buttonAddUser);
        btaddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddUser();
            }
        });
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSearchUser();
            }
        });
    }

    private void onClickSearchUser() {
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
    }

    private void onClickModifyUser() {
    }

    private void onClickAddUser() {
        Intent addUser = new Intent(this, AdminAddUser.class);
        startActivity(addUser);
    }
}