package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadClassAbsentTask;
import com.example.kma_application.Models.Absent;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;

import java.util.ArrayList;

public class TeacherAbsentActivity extends AppCompatActivity {
    Teacher teacher;
    ListView lvClass;
    ArrayList<Absent> absents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_absent);

        lvClass = (ListView)findViewById(R.id.listViewClassAbsent);

        Intent data = getIntent();
        //teacher = (Teacher) data.getSerializableExtra("info");
        String _class = data.getStringExtra("class");

        absents = new ArrayList<>();
        LoadClassAbsentTask loadClassAbsentTask = new LoadClassAbsentTask(
                this,
                lvClass,
                _class,
                absents
        );
        loadClassAbsentTask.execute();
        lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick(position);
            }
        });
    }

    private void onClick(int position) {
        Intent intent = new Intent(this, TeacherChildAbsentActivity.class);
        try {
            Absent absent = absents.get(position);
            System.out.println(absent.getContent());
            intent.putExtra("content", absent.getContent());
            intent.putExtra("startDate", absent.getStartDate());
            intent.putExtra("endDate", absent.getEndDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}