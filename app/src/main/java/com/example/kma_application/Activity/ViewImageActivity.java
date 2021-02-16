package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.DeleteImageTask;
import com.example.kma_application.AsyncTask.LoadImageTask;
import com.example.kma_application.Models.Image;
import com.example.kma_application.R;

public class ViewImageActivity extends AppCompatActivity {
    ImageView imageView;
    Image image;
    Button btDeleteImage;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        
        imageView = (ImageView) findViewById(R.id.imageDetail);
        btDeleteImage = (Button) findViewById(R.id.btDeleteImage);

        Intent data = getIntent();
        image = (Image) data.getSerializableExtra("info");
        role = data.getStringExtra("role");
        //Toast.makeText(this, "image ID"+image.get_id(), Toast.LENGTH_LONG).show();
        System.out.println("image ID"+image.getId());

        new LoadImageTask(
            this,
            imageView,
            image.getId(),
            "ViewImage",
            btDeleteImage,
            role
        ).execute();

        btDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteImage();
            }
        });
    }

    private void deleteImage() {
        new DeleteImageTask(
                this,
                image.getId()
        ).execute();
    }
}