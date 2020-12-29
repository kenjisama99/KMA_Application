package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadClassHealthTask;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;

public class TeacherHealthActivity extends AppCompatActivity {
    Teacher teacher;
    ListView lvClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_health);

        lvClass = (ListView)findViewById(R.id.listViewClass);

        Intent data = getIntent();
        teacher = (Teacher) data.getSerializableExtra("info");

        LoadClassHealthTask loadClassHealthTask = new LoadClassHealthTask(
                this,
                lvClass,
                teacher.get_class()
        );
        loadClassHealthTask.execute();
    }
}