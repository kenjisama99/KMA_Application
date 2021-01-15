package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadClassAbsentTask;
import com.example.kma_application.AsyncTask.LoadClassMessageTask;
import com.example.kma_application.R;

public class TeacherMessengerActivity extends AppCompatActivity {
    ListView lvClass;
    //String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_messenger);

        lvClass = (ListView)findViewById(R.id.listViewClassMessenger);

        Intent data = getIntent();
        //teacher = (Teacher) data.getSerializableExtra("info");
        String _class = data.getStringExtra("class");
        String role = data.getStringExtra("role");
        LoadClassMessageTask loadClassAbsentTask = new LoadClassMessageTask(
                this,
                lvClass,
                _class,
                role
        );
        loadClassAbsentTask.execute();
    }
}