package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.kma_application.Models.Absent;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.R;

public class TeacherChildAbsentActivity extends AppCompatActivity {

    private EditText txtContent;
    private EditText txtStartDate;
    private EditText txtEndDate;
    private Button selectDateStart;
    private Button selectDateFinished;
    private Button submitAbsent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child_absent);
        Intent data = getIntent();
        Absent absent = (Absent) data.getSerializableExtra("info");
        this.txtContent = (EditText)this.findViewById(R.id.edit_contentReason);
        this.txtStartDate = (EditText)this.findViewById(R.id.edit_dateStart);
        this.txtEndDate = (EditText)this.findViewById(R.id.edit_dateFinished);
        this.selectDateStart = (Button)this.findViewById(R.id.selectDateStart);
        this.selectDateStart = (Button)this.findViewById(R.id.selectDateStart);
        this.selectDateFinished = (Button)this.findViewById(R.id.selectDateFinished);
        this.submitAbsent = (Button)this.findViewById(R.id.submitAbsent);

        //txtContent.setText(absent.getContent());
        //txtStartDate.setText(absent.getStartDate());
        //txtEndDate.setText(absent.getEndDate());
    }
}