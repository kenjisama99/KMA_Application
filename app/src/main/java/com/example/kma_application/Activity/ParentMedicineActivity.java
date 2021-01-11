package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadMedicineTask;
import com.example.kma_application.AsyncTask.SubmitMedicineTask;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Prescription;
import com.example.kma_application.R;

import java.util.ArrayList;

public class ParentMedicineActivity extends AppCompatActivity {

    EditText txtMedicineName,txtMedicineDose, txtMedicineTime;
    ListView listViewMedicines;

    int lastSelectedHour = 7;
    int lastSelectedMinute = 0;

    Parent parent;
    String medicineName, medicineDose, time;

    ArrayList<Prescription.Medicine> medicines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_medicine);

        Button buttonSelectTime,buttonAddMedicine,buttonSendPrescription,buttonDeleteMedicines;

        listViewMedicines = (ListView) findViewById(R.id.listViewMedicineParent);

        txtMedicineName = (EditText) findViewById(R.id.editTextMedicineName);
        txtMedicineDose = (EditText) findViewById(R.id.editTextMedicineDose);
        txtMedicineTime = (EditText) findViewById(R.id.editTextMedicineTime);
        buttonSelectTime = (Button) findViewById(R.id.buttonSelectTime);
        buttonAddMedicine = (Button) findViewById(R.id.buttonAddMedicine);
        buttonSendPrescription = (Button) findViewById(R.id.buttonSendPrescription);
        buttonDeleteMedicines = (Button) findViewById(R.id.buttonDeleteMedicines);

        buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonSelectTime();
            }
        });
        buttonAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonAddMedicine();
            }
        });
        buttonSendPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonSendPrescription();
            }
        });
        buttonDeleteMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonDeleteMedicines();
            }
        });

        Intent data = getIntent();
        parent = (Parent) data.getSerializableExtra("info");

//        new LoadMedicineTask(
//            this,
//            parent.getPhone(),
//            listViewMedicines
//        ).execute();
    }

    private void onClickButtonDeleteMedicines() {

    }

    private void onClickButtonSendPrescription() {

    }

    private void onClickButtonAddMedicine() {

    }

    private void onClickButtonSelectTime() {
//        if(lastSelectedHour == -1)  {
//            // Get Current Time
//            final Calendar c = Calendar.getInstance();
//            lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
//            lastSelectedMinute = c.get(Calendar.MINUTE);
//            //final boolean is24HView = checkBoxIs24HView.isChecked();
//        }
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtMedicineTime.setText(hourOfDay + ":00");
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = 0;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour, lastSelectedMinute, true);

        timePickerDialog.show();
    }

    private void submitMedicine() {
        medicineName = txtMedicineName.getText().toString().trim();
        medicineDose = txtMedicineDose.getText().toString().trim();
        time = txtMedicineTime.getText().toString().trim();
        String notification = null;

        if (TextUtils.isEmpty(medicineName))
            notification.concat("Vui lòng nhập nội dung\n");
        if (TextUtils.isEmpty(medicineDose))
            notification.concat("Vui lòng chọn ngày bắt đầu\n");
        if (TextUtils.isEmpty(time))
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
                        medicineDose,
                        time,
                        medicineName
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


}