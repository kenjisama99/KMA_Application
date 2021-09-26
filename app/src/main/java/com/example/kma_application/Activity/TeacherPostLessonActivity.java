package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kma_application.R;

public class TeacherPostLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher_post_lesson );

        ImageButton btBackLesson = (ImageButton) findViewById(R.id.btBackLesson);
        Button btPostLesson = (Button)findViewById(R.id.btPostLesson);
        ImageButton btDelLesson = (ImageButton) findViewById( R.id.btDelLesson );
        ImageButton btAddImgLesson = (ImageButton) findViewById( R.id.btAddImgLesson );

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
                onClicjImgLesson();
            }
        } );

    }

    private void onClicjImgLesson() {
        //abc
    }

    private void onClickDelLesson() {
        //abc
    }

    private void onClickPostlesson() {
        //abc
    }
}