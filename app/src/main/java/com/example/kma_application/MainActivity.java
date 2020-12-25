package com.example.kma_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.KeyStore;

import static com.example.kma_application.R.id.button;
import static com.example.kma_application.R.id.health;
import static com.example.kma_application.R.id.nav_notification;
import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.Admin;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Teacher;

public class MainActivity extends AppCompatActivity implements LoadInfosTask.AsyncResponse {
    Button btInfo,btChat;
    String phone;
    String role;
    Button btHealth;

    InfoModel infoModel;
    Admin admin;
    Teacher teacher;
    Parent parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent data = getIntent();
        phone = data.getStringExtra("phone");
        role = data.getStringExtra("role");

        LoadInfosTask loadInfosTask = new LoadInfosTask(phone, role, this);

        loadInfosTask.execute();

//        btInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickBtInfo();
//
//            }
//        });
        this.btHealth = (Button)this.findViewById(health);
        btHealth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent_health = new Intent(MainActivity.this, health.class);
                MainActivity.this.startActivity(intent_health);
            }
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemReselectedListener(navListener);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.framelayout_container, new homeFragment()).commit();
    }


    private  BottomNavigationView.OnNavigationItemReselectedListener navListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new homeFragment();
                    break;
                case R.id.nav_contact:
                    selectedFragment = new contactFragment();
                    break;
                case R.id.nav_notification:
                    selectedFragment = new notificationFragment();
                    break;
                case R.id.nav_user:
                    selectedFragment = new userFragment();
                    break;
                default:
                    selectedFragment = new homeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container, selectedFragment).commit();
        }
    };


    private void onClickBtChat() {
        //Admin admin = (Admin) infoModel;
        //Parent parent = (Parent) infoModel;
//        Toast.makeText(this,"Role: "+parent.get_class(),Toast.LENGTH_LONG).show();
        Intent chatActivity = new Intent(this,contact.class);
        chatActivity.putExtra("role", role);
        chatActivity.putExtra("info", infoModel);
        startActivity(chatActivity);
    }

    private void onClickBtInfo() {

        //Toast.makeText(this,""+phone,Toast.LENGTH_LONG).show();
        Intent userActivity = new Intent(this,user.class);
        userActivity.putExtra("role", role);
        userActivity.putExtra("info", infoModel);
        startActivity(userActivity);
    }


    @Override
    public void processFinish(InfoModel output) {
        infoModel = output;
    }


}