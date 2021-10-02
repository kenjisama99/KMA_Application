package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.example.kma_application.R;

import java.util.Calendar;

public class TeacherPostLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher_post_lesson);

        ImageButton btBackLesson = (ImageButton)findViewById( R.id.btBackLesson );
        Button btPostLesson = (Button)findViewById( R.id.btPostLesson );
        ImageButton btDelLesson = (ImageButton)findViewById( R.id.btDelLesson);
        ImageButton btAddImgLesson = (ImageButton)findViewById( R.id.btAddImgLesson);
        ImageButton btSchedule = (ImageButton)findViewById( R.id.imgBtSchedule);

        btBackLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btPostLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPostlesson();
            }
        } );

        btDelLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelLesson();
            }
        } );

        btAddImgLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImgLesson();
            }
        } );

        btSchedule.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSchedule();
            }
        } );

        // Get Current Date
//        final Calendar c = Calendar.getInstance();
//        this.lastSelectedYear = c.get(Calendar.YEAR);
//        this.lastSelectedMonth = c.get(Calendar.MONTH);
//        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    }

    private void onClickSchedule() {
//        DatePickerDialog.OnDateSetListener dateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                txtStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                lastSelectedYear = year;
//                lastSelectedMonth = monthOfYear;
//                lastSelectedDayOfMonth = dayOfMonth;
//            }
//        };
//
//        new DatePickerDialog(
//                this,
//                dateSetListenerStart,
//                lastSelectedYear,
//                lastSelectedMonth,
//                lastSelectedDayOfMonth
//        ).show();
    }

    private void onClickImgLesson() {
        //abc
    }

    private void onClickDelLesson() {
        //abc
    }

    private void onClickPostlesson() {
        //abc
    }
}