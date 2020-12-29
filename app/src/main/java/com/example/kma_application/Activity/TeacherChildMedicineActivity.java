package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.Models.Medicine;
import com.example.kma_application.R;

public class TeacherChildMedicineActivity extends AppCompatActivity {

    EditText txtContent, txtStartDate, txtEndDate;
    Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child_medicine);

        txtContent = (EditText)findViewById(R.id.edit_content);
        txtStartDate = (EditText)findViewById(R.id.edit_dateStart);
        txtEndDate = (EditText)findViewById(R.id.edit_dateFinished);

        Intent data = getIntent();
        medicine = (Medicine) data.getSerializableExtra("info");

        if (medicine != null){
            //Toast.makeText(this.context, "Class: "+medicine.get_class(), Toast.LENGTH_LONG).show();
            txtContent.setText(medicine.getContent());
            txtStartDate.setText(medicine.getStartDate());
            txtEndDate.setText(medicine.getEndDate());

        }else
            Toast.makeText(this, "Medicine: "+medicine, Toast.LENGTH_LONG).show();
    }
}