package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.kma_application.AsyncTask.LoadHealthTask;
import com.example.kma_application.Models.Person;
import com.example.kma_application.R;

public class ParentHealthActivity extends AppCompatActivity {
    EditText txtName, txtBirth, txtClass, txtHeight, txtWeight, txtBodyRatio;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_health);

        txtName = (EditText)findViewById(R.id.edit_fullname);
        txtBirth = (EditText)findViewById(R.id.edit_birth);
        txtClass = (EditText)findViewById(R.id.edit_class);
        txtHeight = (EditText)findViewById(R.id.edit_hight);
        txtWeight = (EditText)findViewById(R.id.edit_weight);
        txtBodyRatio = (EditText)findViewById(R.id.edit_comment);

        Intent data = getIntent();
        person = (Person) data.getSerializableExtra("info");

        LoadHealthTask loadHealthTask = new LoadHealthTask(this, person.getPhone(),txtName,txtBirth,txtClass,txtHeight,txtWeight,txtBodyRatio);
        loadHealthTask.execute();
    }
}