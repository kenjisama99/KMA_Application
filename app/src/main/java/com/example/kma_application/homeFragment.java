package com.example.kma_application;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.InfoModel;


public class homeFragment extends Fragment implements LoadInfosTask.AsyncResponse{
    String role, phone;
    InfoModel infoModel;
    Context context = getActivity();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void processFinish(InfoModel output, String role) {
        this.infoModel = output;
        this.role = role;
        if (infoModel == null){
            Toast.makeText(this.context, "InfoModel is null", Toast.LENGTH_LONG).show();
        }else
            this.phone = infoModel.getPhone();

    }
}