package com.example.kma_application.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.kma_application.R;
import com.example.kma_application.Fragment.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.InfoModel;

public class MainActivity extends AppCompatActivity implements LoadInfosTask.AsyncResponse {
    //data
    String phone;
    String role;

    //model
    InfoModel infoModel;

    //fragment
    HomeFragment homeFragment;
    NotificationFragment notificationFragment;
    UserFragment userFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent data = getIntent();
        phone = data.getStringExtra("phone");
        role = data.getStringExtra("role");

        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        userFragment = new UserFragment();

        LoadInfosTask loadInfosTask = new LoadInfosTask(phone, role, this);

        loadInfosTask.execute();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemReselectedListener(navListener);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.framelayout_container, homeFragment).commit();
    }

    private  BottomNavigationView.OnNavigationItemReselectedListener navListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = homeFragment;
                    break;
                case R.id.nav_contact:
                    onClickBtChat();
                    return;
                case R.id.nav_notification:
                    selectedFragment = notificationFragment;
                    break;
                case R.id.nav_user:
                    selectedFragment = userFragment;
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft_replay = fm.beginTransaction();
            ft_replay.replace(R.id.framelayout_container, selectedFragment).commit();
        }
    };


    private void onClickBtChat() {
        //Admin admin = (Admin) infoModel;
        //Parent parent = (Parent) infoModel;
//        Toast.makeText(this,"Role: "+parent.get_class(),Toast.LENGTH_LONG).show();
        Intent chatActivity = new Intent(this, ContactActivity.class);
        chatActivity.putExtra("role", role);
        chatActivity.putExtra("info", infoModel);
        startActivity(chatActivity);
    }

//    private void onClickBtInfo() {
//
//        //Toast.makeText(this,""+phone,Toast.LENGTH_LONG).show();
//        Intent userActivity = new Intent(this, user.class);
//        userActivity.putExtra("role", role);
//        userActivity.putExtra("info", infoModel);
//        startActivity(userActivity);
//    }


    @Override
    public void onLoadInfoTaskFinish(InfoModel output, String role) {
        infoModel = output;
    }


}