package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kma_application.Adapter.ListMedicineAdapter;
import com.example.kma_application.Models.Prescription;
import com.example.kma_application.R;

import java.util.ArrayList;

public class TeacherChildMedicineActivity extends AppCompatActivity {

    RecyclerView recyclerViewMedicines;
    TextView txtTeacherChildMedicinesTitle;
    ListMedicineAdapter listMedicineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child_medicine);

        recyclerViewMedicines = findViewById(R.id.recyclerViewMedicine);
        txtTeacherChildMedicinesTitle = (TextView) findViewById(R.id.txtTeacherChildAppBar);

        listMedicineAdapter = new ListMedicineAdapter(getLayoutInflater());
        recyclerViewMedicines.setAdapter(listMedicineAdapter);
        recyclerViewMedicines.setLayoutManager(new LinearLayoutManager(this));

        Intent data = getIntent();
        ArrayList<Prescription.Medicine> childrenMedicine = (ArrayList<Prescription.Medicine>) data.getSerializableExtra("info");

        if (!childrenMedicine.isEmpty()){
            //Toast.makeText(this.context, "Class: "+prescription.get_class(), Toast.LENGTH_LONG).show();
            txtTeacherChildMedicinesTitle.setText(
                    "Danh sách uống thuốc lúc "+
                    childrenMedicine.get(0).getTime()
            );
            for (int j = 0; j < childrenMedicine.size(); j++) {
                listMedicineAdapter.addItem(
                    childrenMedicine.get(j)
                        .toStringForTeacherUsed());
            }
        }else
            Toast.makeText(this, "Lỗi nạp danh sách", Toast.LENGTH_LONG).show();
    }
}