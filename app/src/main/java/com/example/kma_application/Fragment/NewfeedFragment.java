package com.example.kma_application.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kma_application.Adapter.ListNewfeedAdapter;
import com.example.kma_application.Models.ModelFeed;
import com.example.kma_application.R;

import java.util.ArrayList;


public class NewfeedFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    ListNewfeedAdapter adapterFeed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_newfeed, container, false);
        View view = inflater.inflate(R.layout.fragment_newfeed, container, false);
        return view;

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapterFeed = new ListNewfeedAdapter(this, modelFeedArrayList);
//        recyclerView.setAdapter(adapterFeed);

//        populateRecyclerView();
    }

    private void populateRecyclerView() {
        ModelFeed modelFeed = new ModelFeed(1, 9, 2, R.drawable.ic_propic1, R.drawable.img_post1,
                "Sajin Maharjan", "2 hrs", "The cars we drive say a lot about us.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(2, 26, 6, R.drawable.ic_propic2, 0,
                "Karun Shrestha", "9 hrs", "Don't be afraid of your fears. They're not there to scare you. They're there to \n" +
                "let you know that something is worth it.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(3, 17, 5, R.drawable.ic_propic3, R.drawable.img_post2,
                "Lakshya Ram", "13 hrs", "That reflection!!!");
        modelFeedArrayList.add(modelFeed);

        adapterFeed.notifyDataSetChanged();
    }
}