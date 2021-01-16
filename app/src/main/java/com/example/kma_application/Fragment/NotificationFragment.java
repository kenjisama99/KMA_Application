package com.example.kma_application.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kma_application.AsyncTask.LoadInfosTask;
import com.example.kma_application.AsyncTask.LoadNotifiTask;
import com.example.kma_application.Models.Person;
import com.example.kma_application.R;


public class NotificationFragment extends Fragment implements LoadInfosTask.AsyncResponse{
    ListView listView;
    String role;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        listView = (ListView)view.findViewById(R.id.lvNotifi);

        new LoadNotifiTask(
                getActivity(),
                listView,
                role
        ).execute();
        return view;
    }

    @Override
    public void onLoadInfoTaskFinish(Person output, String role) {
        this.role = role;
    }
}