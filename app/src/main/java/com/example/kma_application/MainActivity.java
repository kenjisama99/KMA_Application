package com.example.kma_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.transition.Transition;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.KeyStore;

import static com.example.kma_application.R.id.nav_notification;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemReselectedListener(navListener);
    }

    private  BottomNavigationView.OnNavigationItemReselectedListener navListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
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


}