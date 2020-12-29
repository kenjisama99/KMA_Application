package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.Models.Child;
import com.example.kma_application.R;

public class TeacherChildHealthActivity extends AppCompatActivity {
    EditText txtName, txtBirth, txtClass, txtHeight, txtWeight, txtBodyRatio;
    Child child;
    Button btComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child_health);

        txtName = (EditText)findViewById(R.id.edit_fullname);
        txtBirth = (EditText)findViewById(R.id.edit_birth);
        txtClass = (EditText)findViewById(R.id.edit_class);
        txtHeight = (EditText)findViewById(R.id.edit_hight);
        txtWeight = (EditText)findViewById(R.id.edit_weight);
        txtBodyRatio = (EditText)findViewById(R.id.edit_comment);
        btComplete = (Button) findViewById(R.id.button_complete);

        Intent data = getIntent();
        child = (Child) data.getSerializableExtra("info");

        if (child != null){
            //Toast.makeText(this.context, "Class: "+child.get_class(), Toast.LENGTH_LONG).show();
            txtName.setText(child.getName());
            txtBirth.setText(child.getBirth());
            txtClass.setText(child.get_class());
            txtHeight.setText(child.getHeight());
            txtWeight.setText(child.getWeight());
            txtBodyRatio.setText(child.getBody_ratio());
        }else
            Toast.makeText(this, "Child: "+child, Toast.LENGTH_LONG).show();
    }
}