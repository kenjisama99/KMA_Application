package com.example.kma_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.InfoModel;


public class userFragment extends Fragment implements LoadInfosTask.AsyncResponse{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onLoadInfoTaskFinish(InfoModel output, String role) {

    }

//    Button btLogout, btChangePassword;
//    EditText txtName, txtPhone, txtId;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user);
//
//        btLogout = (Button)findViewById(R.id.buttonLogout);
//        btChangePassword = (Button)findViewById(R.id.buttonChangePass);
//        txtPhone = (EditText)findViewById(R.id.editTextPersonPhone);
//        txtId = (EditText)findViewById(R.id.editTextPersonID);
//        txtName = (EditText)findViewById(R.id.editTextPersonName);
//
//        loadInfos();
//
//        btLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickBtLogout();
//            }
//        });
//
//        btChangePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickChangePass();
//            }
//        });
//    }
//    String infoJson(String phone) {
//        return "{\"phone\":\"" + phone +"\"}";
//    }
//    private void loadInfos(){
//
//        Intent data = getIntent();
//        String phone = data.getStringExtra("phone");
//        LoadInfosTask loadInfosTask = new LoadInfosTask(this, txtName, txtPhone, txtId);
//        //loadInfosTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        loadInfosTask.execute(infoJson(phone));
//
//    }
//
//    private void onClickChangePass() {
//        Intent data = getIntent();
//        String phone = data.getStringExtra("phone");
//        Intent changePassActivity = new Intent(this,changePassword.class);
//        changePassActivity.putExtra("phone",phone);
//        startActivity(changePassActivity);
//    }
//
//    private void onClickBtLogout() {
//        Intent loginActivity = new Intent(this,login.class);
//        startActivity(loginActivity);
//        finish();
//    }





}