package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kma_application.Adapter.ListCommentAdapter;
import com.example.kma_application.Models.Comment;
import com.example.kma_application.R;

import java.util.ArrayList;

public class ViewCommentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Comment> modelCommentArrayList = new ArrayList<>();
    ListCommentAdapter listCommentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_comment );

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewComment);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listCommentAdapter = new ListCommentAdapter( this, modelCommentArrayList );
        recyclerView.setAdapter( listCommentAdapter );

        populateRecyclerView();
    }

    private void populateRecyclerView() {

        Comment modelComment = new Comment(1, 9, R.drawable.techaerava1,
                "Lê Thị Thanh", "Ảnh đẹp quá...", "10 giờ");
        modelCommentArrayList.add( modelComment );

        modelComment = new Comment(2, 9, R.drawable.techaerava1, R.drawable.smile,
                "Lê Thị Thanh", "Ảnh đẹp quá...", "30 phút");
        modelCommentArrayList.add( modelComment );

        listCommentAdapter.notifyDataSetChanged();
    }
}