package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kma_application.R;

public class AdminAddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);

        Button btSignUp =  (Button) findViewById(R.id.buttonSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });
    }

    private void onClickSignUp() {
    };
}