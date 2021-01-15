package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kma_application.R;

public class AdminManageUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_user);

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