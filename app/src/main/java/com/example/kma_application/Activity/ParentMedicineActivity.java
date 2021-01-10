package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadMedicineTask;
import com.example.kma_application.AsyncTask.SubmitMedicineTask;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.R;

import java.util.Calendar;

public class ParentMedicineActivity extends AppCompatActivity {

    private EditText editTextTime;
    private Button buttonSelectTime;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;

    Parent parent;
    String content, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_medicine);

        this.editTextTime = (EditText) this.findViewById(R.id.editTextTime);
        this.buttonSelectTime = (Button) this.findViewById(R.id.buttonSelectTime);

        this.buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectTime();
            }
        });


//        Intent data = getIntent();
//        parent = (Parent) data.getSerializableExtra("info");
//
//        LoadMedicineTask loadMedicineTask = new LoadMedicineTask(this,parent.getPhone(),txtContent,txtStartDate,txtEndDate);
//        loadMedicineTask.execute();


    }

    private void buttonSelectTime() {
        if(this.lastSelectedHour == -1)  {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute = c.get(Calendar.MINUTE);
            //final boolean is24HView = this.checkBoxIs24HView.isChecked();
        }

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextTime.setText(hourOfDay + ":" + minute );
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour, lastSelectedMinute, true);

        timePickerDialog.show();
    }

//    private void submitMedicine() {
//        content = txtContent.getText().toString().trim();
//        startDate = txtStartDate.getText().toString().trim();
//        endDate = txtEndDate.getText().toString().trim();
//        String notification = null;
//
//        if (TextUtils.isEmpty(content))
//            notification.concat("Vui lòng nhập nội dung\n");
//        if (TextUtils.isEmpty(startDate))
//            notification.concat("Vui lòng chọn ngày bắt đầu\n");
//        if (TextUtils.isEmpty(endDate))
//            notification.concat("Vui lòng chọn ngày kết thúc");
//
//        if (!TextUtils.isEmpty(notification)){
//            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
//            return;
//        }else {
////            String[] lines = content.split("\r?\n");
////            content = "";
////            for (int i=0; i<lines.length ; i++) {
////                if(i == lines.length -1){
////                    content.concat(lines[i]);
////                }else {
////                    content.concat(lines[i]);
////                    content.concat("\\r\\n");
////                }
////            }
//            SubmitMedicineTask submitMedicineTask = new SubmitMedicineTask(this);
//            submitMedicineTask.execute(
//                medicineJson(
//                        parent.getPhone(),
//                        parent.getChildName(),
//                        parent.get_class(),
//                        startDate,
//                        endDate,
//                        content
//            ));
//        }
//    }
//
//    String medicineJson(String phone, String name, String _class, String startDate, String endDate, String content) {
//        return "{\"phone\":\"" + phone + "\","
//                +"\"name\":\"" + name + "\","
//                +"\"_class\":\"" + _class + "\","
//                +"\"startDate\":\"" + startDate + "\","
//                +"\"endDate\":\"" + endDate + "\","
//                +"\"content\":\"" + content +"\"}";
//    }


}