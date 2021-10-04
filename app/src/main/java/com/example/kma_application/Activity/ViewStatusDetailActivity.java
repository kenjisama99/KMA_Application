package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kma_application.R;

public class ViewStatusDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_status_detail );

        ImageButton btClose = (ImageButton)findViewById( R.id.imgBtClose );
        ImageView imgViewPostPic = (ImageView)findViewById( R.id.imgView_postPic );
        TextView tvName = (TextView)findViewById( R.id.tv_name );
        TextView tvTime = (TextView)findViewById( R.id.tv_time );
        TextView tvStatus = (TextView)findViewById( R.id.tv_status );
        TextView tvLikes = (TextView)findViewById( R.id.tv_like );
        TextView tvComment = (TextView)findViewById( R.id.tv_comment );
        LinearLayout btLikes = (LinearLayout) findViewById( R.id.btnLike );
        LinearLayout btComment = (LinearLayout) findViewById( R.id.btnComment );
        LinearLayout btMessage = (LinearLayout) findViewById( R.id.btnMessage );
        
        btClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        
        btLikes.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLikes();
            }
        } );
        
        btComment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtComment();
            }
        } );
        
        btMessage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtMessage();
            }
        } );

    }

    private void onClickBtMessage() {
    }

    private void onClickBtComment() {
    }

    private void onClickBtLikes() {
    }
}