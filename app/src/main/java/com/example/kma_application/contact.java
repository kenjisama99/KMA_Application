package com.example.kma_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class contact extends AppCompatActivity {
    ListView lvChat;
    EditText editText;
    ImageButton btnCall, btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

    }
    private void anhxa() {
        lvChat = (ListView)findViewById(R.id.listviewChat);
        editText = (EditText)findViewById(R.id.editextContent);
        btnCall = (ImageButton)findViewById(R.id.buttonCall);
        btnSend = (ImageButton)findViewById(R.id.buttonSend);
    }


}