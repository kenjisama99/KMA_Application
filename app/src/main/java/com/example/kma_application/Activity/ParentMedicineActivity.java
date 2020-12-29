package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kma_application.R;

import java.util.Calendar;

public class ParentMedicineActivity extends AppCompatActivity {

    private EditText edit_content;
    private EditText edit_dateStart;
    private EditText edit_dateFinished;
    private Button selectDateStart;
    private Button selectDateFinished;
    private Button submitMedicine;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_medicine);

        this.edit_content = (EditText)this.findViewById(R.id.edit_content);
        this.edit_dateStart = (EditText)this.findViewById(R.id.edit_dateStart);
        this.edit_dateFinished = (EditText)this.findViewById(R.id.edit_dateFinished);
        this.selectDateStart = (Button)this.findViewById(R.id.selectDateStart);
        this.selectDateFinished = (Button)this.findViewById(R.id.selectDateFinished);
        this.submitMedicine = (Button)this.findViewById(R.id.submitMedicine);

        selectDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateStart();
            }
        });

        selectDateFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateFinished();
            }
        });

        submitMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitMedicine();
            }
        });

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    }

    private void submitMedicine() {
    }



    private void selectDateFinished() {
        DatePickerDialog.OnDateSetListener dateSetListenerFinished = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edit_dateFinished.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog datePickerDialogFinished = null;
        datePickerDialogFinished = new DatePickerDialog(this,
                dateSetListenerFinished, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialogFinished.show();
    }



    private void selectDateStart() {

        DatePickerDialog.OnDateSetListener dateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edit_dateStart.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog datePickerDialogStart = null;
//        datePickerDialog = new DatePickerDialog(this,
//                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
//                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialogStart = new DatePickerDialog(this,
                dateSetListenerStart, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialogStart.show();
    }
}