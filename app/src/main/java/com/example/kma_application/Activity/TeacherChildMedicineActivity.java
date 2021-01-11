package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.Models.Prescription;
import com.example.kma_application.R;

public class TeacherChildMedicineActivity extends AppCompatActivity {

    EditText txtContent, txtStartDate, txtEndDate;
    Prescription prescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child_medicine);

        txtContent = (EditText)findViewById(R.id.edit_content);
        txtStartDate = (EditText)findViewById(R.id.edit_dateStart);
        txtEndDate = (EditText)findViewById(R.id.edit_dateFinished);

//        Intent data = getIntent();
//        prescription = (Prescription) data.getSerializableExtra("info");

        if (prescription != null){
            //Toast.makeText(this.context, "Class: "+prescription.get_class(), Toast.LENGTH_LONG).show();

        }else
            Toast.makeText(this, "Prescription: "+ prescription, Toast.LENGTH_LONG).show();
    }
}