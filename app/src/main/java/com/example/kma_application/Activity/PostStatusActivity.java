package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kma_application.R;

public class PostStatusActivity extends AppCompatActivity {

    ImageButton imgBtClose, imgBtPostPicture, imgBtPostVideo, imgBttakeAPhoto;
    Button btPost;
    ImageButton btClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_status);

        btClose = (ImageButton)findViewById( R.id.imgBtClose );
        btPost = (Button)findViewById( R.id.btPost );

        btClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btPost.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtPost();
            }
        } );
    }

    private void onClickBtPost() {
//        abc
    }
}