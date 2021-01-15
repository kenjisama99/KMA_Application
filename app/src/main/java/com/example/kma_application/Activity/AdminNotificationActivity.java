package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kma_application.R;

public class AdminNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification);

        Button btCreateNotification = (Button)findViewById(R.id.buttonCreateNotification);
        btCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateNotification();
            }
        });
    }

    private void onClickCreateNotification() {
        Intent createNotification = new Intent(this, CreateNotificationActivity.class);
        startActivity(createNotification);
    }
}