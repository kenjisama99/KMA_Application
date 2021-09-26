package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kma_application.R;

public class TeacherLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher_lesson);

        Button btCreateLesson = (Button)findViewById(R.id.buttonCreateLesson);
        Button btSelectDateLesson = (Button)findViewById(R.id.btSelectDateLesson);
        ImageButton btCloseLesson = (ImageButton) findViewById( R.id.btCloseLesson );



        btCreateLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateLesson();
            }
        });

        btCloseLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void onClickCreateLesson() {
        Intent createLesson = new Intent(this, TeacherPostLessonActivity.class);
//        createNotification.putExtra("role","admin");
        startActivity(createLesson);
    }
}
