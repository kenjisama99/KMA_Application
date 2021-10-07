package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kma_application.AsyncTask.LoadClassHealthTask2;
import com.example.kma_application.AsyncTask.SubmitMealTask;
import com.example.kma_application.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class TeacherChildHeathActivity2 extends AppCompatActivity {

    private EditText editTextDateHealth;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    ImageView imgMainMeal, imgSubMeal;
    Button btAddSubMeal, btAddMainMeal, btScheduleHealth;
    ImageButton btBackHealth;
    String _class;
    private final int MAIN_MEAL_REQUEST_ID = 1;
    private final int SUB_MEAL_REQUEST_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_child_heath2);

        Intent data = getIntent();
        _class = data.getStringExtra("class");
        String role = data.getStringExtra("role");

        btAddSubMeal = (Button)findViewById(R.id.btSubMeal);
        btAddMainMeal = (Button)findViewById(R.id.btMainMeal);
        imgMainMeal = (ImageView)findViewById(R.id.imgMainMeal);
        imgSubMeal = (ImageView)findViewById(R.id.imgSubMeal);
        btBackHealth = (ImageButton)findViewById(R.id.btBackHealth);
        btScheduleHealth = (Button)findViewById(R.id.btScheduleHealth);
        editTextDateHealth = (EditText)findViewById( R.id.editTextDateHealth );

        if (role.equals("parent")){
            btAddMainMeal.setVisibility(View.INVISIBLE);
            btAddSubMeal.setVisibility(View.INVISIBLE);
        }
        LoadClassHealthTask2 loadClassHealthTask2 = new LoadClassHealthTask2(
                this,
                _class,
                imgMainMeal,
                imgSubMeal
        );
        loadClassHealthTask2.execute();
        btAddSubMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture(false);
            }
        });
        btAddMainMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture(true);
            }
        });

        btBackHealth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btScheduleHealth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtScheduleHealth();
            }
        } );

        //         Get Current Date
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
    }

    private void onClickBtScheduleHealth() {
        DatePickerDialog.OnDateSetListener dateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTextDateHealth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                editTextDateHealth.setVisibility( View.VISIBLE );
                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        new DatePickerDialog(
                this,
                dateSetListenerStart,
                lastSelectedYear,
                lastSelectedMonth,
                lastSelectedDayOfMonth
        ).show();
    }

    private void choosePicture(boolean forMainMeal) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if(forMainMeal)
            startActivityForResult(intent, MAIN_MEAL_REQUEST_ID);
        else
            startActivityForResult(intent, SUB_MEAL_REQUEST_ID);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("TAG", "RQ code: " + requestCode + "\tRS code: " + resultCode);
        //Toast.makeText(this,"RQ code: "+requestCode+"\tRS code: "+resultCode,Toast.LENGTH_LONG).show();
        Bitmap bitmap = null;
        try {
            Uri imageURI = data.getData();
            InputStream inputStream = getContentResolver().openInputStream(imageURI);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (requestCode == MAIN_MEAL_REQUEST_ID && bitmap != null) {
            new SubmitMealTask(
                    this,
                    imgMainMeal,
                    imgSubMeal,
                    true,
                    bitmap,
                    _class
            ).execute();
        }
        if (requestCode == SUB_MEAL_REQUEST_ID && bitmap != null) {
            new SubmitMealTask(
                    this,
                    imgMainMeal,
                    imgSubMeal,
                    false,
                    bitmap,
                    _class
            ).execute();
        }
    }
}