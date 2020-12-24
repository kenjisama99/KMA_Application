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


<<<<<<< HEAD
public class MainActivity extends AppCompatActivity {
=======
import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.Admin;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Teacher;

public class MainActivity extends AppCompatActivity implements LoadInfosTask.AsyncResponse {
    Button btInfo,btChat;
    String phone;
    String role;

    InfoModel infoModel;
    Admin admin;
    Teacher teacher;
    Parent parent;
>>>>>>> e641362806d285afc72a8e480ea115fdc832ee39

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemReselectedListener(navListener);
    }

<<<<<<< HEAD
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
=======
        Intent data = getIntent();
        phone = data.getStringExtra("phone");
        role = data.getStringExtra("role");

        LoadInfosTask loadInfosTask = new LoadInfosTask(phone, role, this);

        loadInfosTask.execute();

        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtInfo();
>>>>>>> e641362806d285afc72a8e480ea115fdc832ee39
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container, selectedFragment).commit();

        }
    };

<<<<<<< HEAD

=======
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

>>>>>>> e641362806d285afc72a8e480ea115fdc832ee39
}