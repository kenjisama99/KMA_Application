package com.example.kma_application;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.Admin;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Teacher;

public class MainActivity extends AppCompatActivity implements LoadInfosTask.AsyncResponse {
    Button btInfo,btChat;
    String phone;
    String role;

    InfoModel infoModel;
    Admin admin;
    Teacher teacher;
    Parent parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInfo = (Button)findViewById(R.id.buttonInfo);
        btChat = (Button)findViewById(R.id.buttonChat);

        Intent data = getIntent();
        phone = data.getStringExtra("phone");
        role = data.getStringExtra("role");

        LoadInfosTask loadInfosTask = new LoadInfosTask(phone, role, this);

        loadInfosTask.execute();

        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtInfo();
            }
        });

        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtChat();
            }
        });
    }

    private void onClickBtChat() {
        //Admin admin = (Admin) infoModel;
        //Parent parent = (Parent) infoModel;
//        Toast.makeText(this,"Role: "+parent.get_class(),Toast.LENGTH_LONG).show();
        Intent chatActivity = new Intent(this,contact.class);
        chatActivity.putExtra("role", role);
        chatActivity.putExtra("info", infoModel);
        startActivity(chatActivity);
    }

    private void onClickBtInfo() {

        //Toast.makeText(this,""+phone,Toast.LENGTH_LONG).show();
        Intent userActivity = new Intent(this,user.class);
        userActivity.putExtra("role", role);
        userActivity.putExtra("info", infoModel);
        startActivity(userActivity);
    }


    @Override
    public void processFinish(InfoModel output) {
        infoModel = output;
    }

}