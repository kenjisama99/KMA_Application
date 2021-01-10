package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadImageTask;
import com.example.kma_application.Models.Image;
import com.example.kma_application.R;

public class ViewImageActivity extends AppCompatActivity {
    ImageView imageView;
    Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        
        imageView = (ImageView) findViewById(R.id.imageDetail);

        Intent data = getIntent();
        image = (Image) data.getSerializableExtra("info");
        //Toast.makeText(this, "image ID"+image.get_id(), Toast.LENGTH_LONG).show();
        System.out.println("image ID"+image.getId());

        new LoadImageTask(
            this,
            imageView,
            image.getId(),
            "ViewImage"
        ).execute();
    }
}