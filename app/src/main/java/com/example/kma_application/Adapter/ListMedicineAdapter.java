package com.example.kma_application.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kma_application.Models.ChildMedicine;
import com.example.kma_application.Models.Prescription;

import java.util.List;

public abstract class ListMedicineAdapter extends BaseAdapter {
    private List<ChildMedicine> listData;
    private LayoutInflater layoutInflater;
    private Context context;
}
