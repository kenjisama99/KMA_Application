package com.example.kma_application.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kma_application.Activity.PostStatusActivity;
import com.example.kma_application.Adapter.ListNewfeedAdapter;
import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.AsyncTask.LoadPostsTask;
import com.example.kma_application.Models.ModelFeed;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Person;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;

import java.util.ArrayList;


public class NewfeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoadInfosTask.AsyncResponse{
    String role, phone, _class, name;
    Person person;
    Button btnPostStatus;
    Context context = getActivity();
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    ListNewfeedAdapter adapterFeed;
    RelativeLayout teacherPostLayout;

    public void setLoadInfosTask(LoadInfosTask loadInfosTask) {
        this.loadInfosTask = loadInfosTask;
    }

    LoadInfosTask loadInfosTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newfeed, container, false);

        recyclerView = (RecyclerView)view.findViewById( R.id.recyclerView );
        teacherPostLayout = (RelativeLayout)view.findViewById( R.id.teacherPostLayout );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapterFeed = new ListNewfeedAdapter(getContext(),name,phone);
        recyclerView.setAdapter(adapterFeed);

        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        if (role.equals("parent")) {
            teacherPostLayout.setVisibility(View.GONE);
        }

        btnPostStatus = (Button) view.findViewById(R.id.postStatus);
        btnPostStatus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnPostStatus();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                new LoadPostsTask(
                    getContext(),
                    _class,
                    adapterFeed,
                    recyclerView,
                    mSwipeRefreshLayout
                ).execute();
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        // Fetching data from server
        new LoadPostsTask(
                getContext(),
                _class,
                adapterFeed,
                recyclerView,
                mSwipeRefreshLayout
        ).execute();
    }

    private void onClickBtnPostStatus() {
        Intent intent = new Intent(getActivity(), PostStatusActivity.class );
        intent.putExtra("name",name);
        intent.putExtra("_class",_class);

        startActivity(intent);
    }
    @Override
    public void onLoadInfoTaskFinish(Person output, String role) {

        this.role = role;

        if (output == null){
            Toast.makeText(this.context, "InfoModel is null", Toast.LENGTH_LONG).show();
        }else{
            this.phone = output.getPhone();
            this.name = output.getName();
//            adapterFeed.setName(name);
//            adapterFeed.setPhone(phone);
            if (role.equals("teacher")) {
                Teacher teacher = (Teacher) output;
                _class = teacher.get_class();
                //txtName.setText("Cô: "+teacher.getName()+" - Lớp: "+teacher.get_class());
            }else {
                Parent parent = (Parent) output;
                _class = parent.get_class();
                //txtName.setText("Bé: "+parent.getChildName()+" - Lớp: "+parent.get_class());
            }
        }
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