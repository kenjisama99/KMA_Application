package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kma_application.AsyncTask.LoadClassHealthTask2;
import com.example.kma_application.AsyncTask.SubmitMealTask;
import com.example.kma_application.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class TeacherChildHeathActivity2 extends AppCompatActivity {
    ImageView imgMainMeal, imgSubMeal;
    Button btAddSubMeal, btAddMainMeal;
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