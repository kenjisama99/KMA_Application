package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kma_application.Adapter.ListCommentAdapter;
import com.example.kma_application.Models.Comment;
import com.example.kma_application.R;

import java.util.ArrayList;

public class ViewCommentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Comment> modelCommentArrayList = new ArrayList<>();
    ListCommentAdapter listCommentAdapter;
    ImageButton imgBtClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_comment );

        imgBtClose = (ImageButton)findViewById( R.id.imgBtClose );
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewComment);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listCommentAdapter = new ListCommentAdapter( this, modelCommentArrayList );
        recyclerView.setAdapter( listCommentAdapter );

        populateRecyclerView();

        imgBtClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void populateRecyclerView() {

        Comment modelComment = new Comment( 1, 5, R.drawable.teacherava,"Lê Thị Thanh", "Ảnh đẹp quá...", "30 phút" );
        modelCommentArrayList.add( modelComment );
        modelComment = new Comment(2, 9, R.drawable.techaerava1, R.drawable.smile,
                "Lê Thị Thanh", "Ảnh đẹp quá...", "30 phút");
        modelCommentArrayList.add( modelComment );

        listCommentAdapter.notifyDataSetChanged();
    }
}