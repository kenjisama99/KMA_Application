package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kma_application.AsyncTask.LikePostTask;
import com.example.kma_application.AsyncTask.LoadImageTask;
import com.example.kma_application.AsyncTask.UnlikePostTask;
import com.example.kma_application.R;

import org.json.JSONException;

public class ViewStatusDetailActivity extends AppCompatActivity {
    TextView tvName, tvTime, tvStatus, tvLikes, tvComment, txt_like_button;
    ImageView imgViewPostPic;
    LinearLayout btLikes, btComment, btMessage;
    String postId, date, teacherName, like, description, comment, name, phone;
    boolean isLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_status_detail );

        ImageButton btClose = (ImageButton)findViewById( R.id.imgBtClose );
         imgViewPostPic = (ImageView)findViewById( R.id.imageDetail );
         tvName = (TextView)findViewById( R.id.tv_name );
         tvTime = (TextView)findViewById( R.id.tv_time );
         tvStatus = (TextView)findViewById( R.id.tv_status );
         tvLikes = (TextView)findViewById( R.id.tv_like );
         txt_like_button = (TextView)findViewById( R.id.txt_like_button );
         tvComment = (TextView)findViewById( R.id.tv_comment );
         btLikes = (LinearLayout) findViewById( R.id.btnLike );
         btComment = (LinearLayout) findViewById( R.id.btnComment );
         btMessage = (LinearLayout) findViewById( R.id.btnMessage );

        Intent intent = getIntent();
        try {
            postId = intent.getStringExtra("postId");
            date = intent.getStringExtra("date");
            teacherName = intent.getStringExtra("teacherName");
            like = intent.getStringExtra("like");
            description = intent.getStringExtra("description");
            comment = intent.getStringExtra("comment");
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            isLike = intent.getBooleanExtra("isLike", false);

            tvName.setText(teacherName);
            tvTime.setText(date);
            tvStatus.setText(description);
            tvLikes.setText(like);
            tvComment.setText(comment+" bình luận");
            if (isLike){
                txt_like_button.setText("Đã thích");
                txt_like_button.setTextColor(Color.parseColor("#39569C"));
            }
            new LoadImageTask(
                    this,
                    imgViewPostPic,
                    postId,
                    "ViewStatus"
            ).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        Intent intent = new Intent(this, ViewCommentActivity.class );
        try {
            intent.putExtra("postId", postId);
            intent.putExtra("name", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    private void onClickBtLikes() {
        if (txt_like_button.getText().toString().trim().equals("Thích")){
            new LikePostTask(
                    this,
                    postId,
                    name,
                    phone,
                    tvLikes,
                    txt_like_button
            ).execute();
        }else
            new UnlikePostTask(
                    this,
                    postId,
                    name,
                    phone,
                    tvLikes,
                    txt_like_button
            ).execute();
    }
}