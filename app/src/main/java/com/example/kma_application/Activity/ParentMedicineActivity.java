package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadMedicineTask;
import com.example.kma_application.AsyncTask.SubmitMedicineTask;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.R;

import java.util.Calendar;

public class ParentMedicineActivity extends AppCompatActivity {

    private EditText txtContent;
    private EditText txtStartDate;
    private EditText txtEndDate;
    private Button selectDateStart;
    private Button selectDateFinished;
    private Button submitMedicine;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    Parent parent;
    String content, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_medicine);

        this.txtContent = (EditText)this.findViewById(R.id.edit_content);
        this.txtStartDate = (EditText)this.findViewById(R.id.edit_dateStart);
        this.txtEndDate = (EditText)this.findViewById(R.id.edit_dateFinished);
        this.selectDateStart = (Button)this.findViewById(R.id.selectDateStart);
        this.selectDateFinished = (Button)this.findViewById(R.id.selectDateFinished);
        this.submitMedicine = (Button)this.findViewById(R.id.submitMedicine);

        Intent data = getIntent();
        parent = (Parent) data.getSerializableExtra("info");

        LoadMedicineTask loadMedicineTask = new LoadMedicineTask(this,parent.getPhone(),txtContent,txtStartDate,txtEndDate);
        loadMedicineTask.execute();

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
        content = txtContent.getText().toString().trim();
        startDate = txtStartDate.getText().toString().trim();
        endDate = txtEndDate.getText().toString().trim();
        String notification = null;

        if (TextUtils.isEmpty(content))
            notification.concat("Vui lòng nhập nội dung\n");
        if (TextUtils.isEmpty(startDate))
            notification.concat("Vui lòng chọn ngày bắt đầu\n");
        if (TextUtils.isEmpty(endDate))
            notification.concat("Vui lòng chọn ngày kết thúc");

        if (!TextUtils.isEmpty(notification)){
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
            return;
        }else {
//            String[] lines = content.split("\r?\n");
//            content = "";
//            for (int i=0; i<lines.length ; i++) {
//                if(i == lines.length -1){
//                    content.concat(lines[i]);
//                }else {
//                    content.concat(lines[i]);
//                    content.concat("\\r\\n");
//                }
//            }
            SubmitMedicineTask submitMedicineTask = new SubmitMedicineTask(this);
            submitMedicineTask.execute(
                medicineJson(
                        parent.getPhone(),
                        parent.getChildName(),
                        parent.get_class(),
                        startDate,
                        endDate,
                        content
            ));
        }
    }

    String medicineJson(String phone, String name, String _class, String startDate, String endDate, String content) {
        return "{\"phone\":\"" + phone + "\","
                +"\"name\":\"" + name + "\","
                +"\"_class\":\"" + _class + "\","
                +"\"startDate\":\"" + startDate + "\","
                +"\"endDate\":\"" + endDate + "\","
                +"\"content\":\"" + content +"\"}";
    }

    private void selectDateFinished() {
        DatePickerDialog.OnDateSetListener dateSetListenerFinished = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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
                txtStartDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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