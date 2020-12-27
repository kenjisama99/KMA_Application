package com.example.kma_application.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.R;


public class NotificationFragment extends Fragment implements LoadInfosTask.AsyncResponse{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onLoadInfoTaskFinish(InfoModel output, String role) {

    }
}