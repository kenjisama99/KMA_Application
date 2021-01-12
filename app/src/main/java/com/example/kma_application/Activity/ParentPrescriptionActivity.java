package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadTomorrowMedicinesTask;
import com.example.kma_application.AsyncTask.SubmitPrescriptionTask;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Prescription;
import com.example.kma_application.R;

import java.util.ArrayList;

public class ParentPrescriptionActivity extends AppCompatActivity implements  LoadTomorrowMedicinesTask.CallbackForMedicine{

    EditText txtMedicineName,txtMedicineDose, txtMedicineTime;
    ListView listViewMedicines;
    Button buttonSelectTime,buttonAddMedicine,
           buttonSendPrescription, buttonUndoAddMedicines;

    int lastSelectedHour = 7;
    int lastSelectedMinute = 0;
    boolean hasMedicinesFromServer = false;

    Parent parent;
    String medicineName, medicineDose, time;

    ArrayList<Prescription.Medicine> medicines = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_medicine);

        //Init View
        {
            listViewMedicines = (ListView) findViewById(R.id.listViewMedicineParent);
            txtMedicineName = (EditText) findViewById(R.id.editTextMedicineName);
            txtMedicineDose = (EditText) findViewById(R.id.editTextMedicineDose);
            txtMedicineTime = (EditText) findViewById(R.id.editTextMedicineTime);
            buttonSelectTime = (Button) findViewById(R.id.buttonSelectTime);
            buttonAddMedicine = (Button) findViewById(R.id.buttonAddMedicine);
            buttonSendPrescription = (Button) findViewById(R.id.buttonSendPrescription);
            buttonUndoAddMedicines = (Button) findViewById(R.id.buttonUndoAddMedicines);
        }
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
        buttonUndoAddMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonUndoAddMedicines();
            }
        });

        adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                this.medicines
        );
        listViewMedicines.setAdapter(adapter);

        Intent data = getIntent();
        parent = (Parent) data.getSerializableExtra("info");

        new LoadTomorrowMedicinesTask(
            this,
            parent.getPhone()
        ).execute();
    }

    private void onClickButtonUndoAddMedicines() {
        medicines.remove(medicines.size() - 1);
        adapter.notifyDataSetChanged();

        if (medicines.isEmpty()){
            buttonSendPrescription.setVisibility(View.INVISIBLE);
            buttonUndoAddMedicines.setVisibility(View.INVISIBLE);
        }
    }

    private void onClickButtonSendPrescription() {
        new SubmitPrescriptionTask(
                this
        ).execute(
                medicineJson(
                        parent.getPhone(),
                        parent.getChildName(),
                        parent.get_class(),
                        medicineDose,
                        time,
                        medicineName
                ));
    }

    private void onClickButtonAddMedicine() {
        if (hasMedicinesFromServer) {
            medicines.clear();
            adapter.notifyDataSetChanged();

            hasMedicinesFromServer = false;
        }

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
            //push data to listView
            medicines.add(new Prescription.Medicine(
                medicineName,
                medicineDose,
                time
            ));
            adapter.notifyDataSetChanged();

            //Allow Submit and Undo add medicine
            buttonUndoAddMedicines.setVisibility(View.VISIBLE);
            buttonSendPrescription.setVisibility(View.VISIBLE);
        }
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
        new TimePickerDialog(
            this,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
            timeSetListener,
            lastSelectedHour,
            lastSelectedMinute,
            true
        ).show();
    }

    String medicineJson(String phone, String name, String _class, String startDate, String endDate, String content) {
        return "{\"phone\":\"" + phone + "\","
                +"\"name\":\"" + name + "\","
                +"\"_class\":\"" + _class + "\","
                +"\"startDate\":\"" + startDate + "\","
                +"\"endDate\":\"" + endDate + "\","
                +"\"content\":\"" + content +"\"}";
    }


    @Override
    public void callback(boolean hasData, ArrayList<Prescription.Medicine> medicines) {
        this.hasMedicinesFromServer = hasData;
        if (hasData) {
            this.medicines =
                (ArrayList<Prescription.Medicine>)medicines.clone();
            adapter.notifyDataSetChanged();
        }
    }
}