package com.example.kma_application.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.kma_application.R;
import com.example.kma_application.Fragment.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.Person;

public class MainActivity extends AppCompatActivity implements LoadInfosTask.AsyncResponse {
    //data
    String phone;
    String role;

    //model
    Person person;

    //fragment
    HomeFragment homeFragment;
    NotificationFragment notificationFragment;
    UserFragment userFragment;

    // a static variable to get a reference of our application context
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextOfApplication = getApplicationContext();

        Intent data = getIntent();
        phone = data.getStringExtra("phone");
        role = data.getStringExtra("role");

        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        userFragment = new UserFragment();

        LoadInfosTask loadInfosTask = new LoadInfosTask(phone, role, this,homeFragment,notificationFragment,userFragment);
        homeFragment.setLoadInfosTask(loadInfosTask);
        loadInfosTask.execute();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.framelayout_container, homeFragment).commit();
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = homeFragment;
                    break;
                case R.id.nav_contact:
                    onClickBtChat();
                    return true;
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
            return true;
        }
    };

    private void onClickBtChat() {
        Intent chatActivity = new Intent(this, ContactActivity.class);
        chatActivity.putExtra("role", role);
        chatActivity.putExtra("info", person);
        startActivity(chatActivity);
    }

    @Override
    public void onLoadInfoTaskFinish(Person output, String role) {
        person = output;
    }
}