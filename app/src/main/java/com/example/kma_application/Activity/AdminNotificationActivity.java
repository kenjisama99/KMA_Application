package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadNotifiTask;
import com.example.kma_application.R;

public class AdminNotificationActivity extends AppCompatActivity {
    ListView listView;
    Boolean preload = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification);

        listView = (ListView)findViewById(R.id.listViewHistory);

        new LoadNotifiTask(
                this,
                listView,
                "admin"
        ).execute();

        Button btCreateNotification = (Button)findViewById(R.id.buttonCreateNotification);
        btCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateNotification();
            }
        });
    }

    private void onClickCreateNotification() {
        Intent createNotification = new Intent(this, AdminCreateNotificationActivity.class);
        createNotification.putExtra("role","admin");
        startActivity(createNotification);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!preload)
            new LoadNotifiTask(
                    this,
                    listView,
                    "admin"
            ).execute();
        preload = false;
    }
}