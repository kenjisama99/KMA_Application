package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadClassAbsentTask;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;

public class TeacherAbsentActivity extends AppCompatActivity {
    Teacher teacher;
    ListView lvClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_absent);

        lvClass = (ListView)findViewById(R.id.listViewClassAbsent);

        Intent data = getIntent();
        teacher = (Teacher) data.getSerializableExtra("info");

        LoadClassAbsentTask loadClassAbsentTask = new LoadClassAbsentTask(
                this,
                lvClass,
                teacher.get_class()
        );
        loadClassAbsentTask.execute();
    }
}