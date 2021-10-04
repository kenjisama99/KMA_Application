package com.example.kma_application.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kma_application.Activity.ParentAbsentActivity;
import com.example.kma_application.Activity.PostStatusActivity;
import com.example.kma_application.Activity.TeacherAbsentActivity;
import com.example.kma_application.Adapter.ListNewfeedAdapter;
import com.example.kma_application.Models.ModelFeed;
import com.example.kma_application.R;

import java.util.ArrayList;


public class NewfeedFragment extends Fragment {

    Button btnPostStatus;

    RecyclerView recyclerView;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    ListNewfeedAdapter adapterFeed;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_newfeed, container, false);
        View view = inflater.inflate(R.layout.fragment_newfeed, container, false);

        recyclerView = (RecyclerView)view.findViewById( R.id.recyclerView );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapterFeed = new ListNewfeedAdapter(getContext(), modelFeedArrayList);
        recyclerView.setAdapter(adapterFeed);

        populateRecyclerView();

        btnPostStatus = (Button) view.findViewById(R.id.postStatus);
        btnPostStatus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnPostStatus();
            }
        });

        return view;
    }

    private void onClickBtnPostStatus() {
        Intent intent = new Intent(getActivity(), PostStatusActivity.class );
        startActivity(intent);
    }

    private void populateRecyclerView() {
        ModelFeed modelFeed = new ModelFeed(1, 9, 2, R.drawable.teacherava, R.drawable.img_post1,
                "Sajin Maharjan", "10 phút", "The cars we drive say a lot about us.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(2, 26, 6, R.drawable.techaerava1, 0,
                "Karun Shrestha", "9 giờ", "Don't be afraid of your fears. They're not there to scare you. They're there to \n" +
                "let you know that something is worth it.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(3, 17, 5, R.drawable.teacherava, R.drawable.img_post2,
                "Lakshya Ram", "13 giờ", "That reflection!!!");
        modelFeedArrayList.add(modelFeed);

        adapterFeed.notifyDataSetChanged();
    }
}