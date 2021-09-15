package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kma_application.R;

public class TeacherLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher_lesson );

        Button btCreateLesson = (Button)findViewById(R.id.buttonCreateLesson);
        btCreateLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateLesson();
            }
        });
    }

    private void onClickCreateLesson() {
        Intent createLesson = new Intent(this, AdminCreateNotificationActivity.class);
//        createNotification.putExtra("role","admin");
        startActivity(createLesson);
    }
}
