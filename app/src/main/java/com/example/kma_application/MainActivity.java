package com.example.kma_application;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btInfo,btChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInfo = (Button)findViewById(R.id.buttonInfo);
        btChat = (Button)findViewById(R.id.buttonChat);

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
//        Intent data = getIntent();
//        String phone = data.getStringExtra("phone");
//        //Toast.makeText(this,""+phone,Toast.LENGTH_LONG).show();
//        Intent chatActivity = new Intent(this,contact.class);
//        chatActivity.putExtra("phone", phone);
//        startActivity(chatActivity);
    }

    private void onClickBtInfo() {
        Intent data = getIntent();
        String phone = data.getStringExtra("phone");
        //Toast.makeText(this,""+phone,Toast.LENGTH_LONG).show();
        Intent userActivity = new Intent(this,user.class);
        userActivity.putExtra("phone", phone);
        startActivity(userActivity);
    }
}