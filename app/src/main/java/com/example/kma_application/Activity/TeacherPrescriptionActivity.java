package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadClassPrescriptionTask;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;

public class TeacherPrescriptionActivity extends AppCompatActivity {

    Teacher teacher;
    ListView lvClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_medicine);

        lvClass = (ListView)findViewById(R.id.listViewClassMedicine);

        Intent data = getIntent();
        //teacher = (Teacher) data.getSerializableExtra("info");
        String _class = data.getStringExtra("class");
        new LoadClassPrescriptionTask(
                this,
                lvClass,
                _class
        ).execute();
    }
}