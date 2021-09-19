package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kma_application.R;

public class PostStatusActivity extends AppCompatActivity {

    ImageButton imgBtClose, imgBtPostPicture, imgBtPostVideo, imgBttakeAPhoto;
    Button btPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_status);
    }
}