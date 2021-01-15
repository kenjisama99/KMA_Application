package com.example.kma_application.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kma_application.R;

import java.util.ArrayList;
import java.util.List;

public class ListMedicineAdapter extends RecyclerView.Adapter{
    private String phone;

    private LayoutInflater inflater;
    private List<String> childMedicineList = new ArrayList<>();

    public ListMedicineAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    private class childMedicineHolder extends RecyclerView.ViewHolder {

        TextView txtChildMedicineList;

        public childMedicineHolder(@NonNull View itemView) {
            super(itemView);

            txtChildMedicineList = itemView.findViewById(R.id.txtChildMedicineList);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_children_medicine, parent, false);
        return new ListMedicineAdapter.childMedicineHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String childMedicine = childMedicineList.get(position);

        ListMedicineAdapter.childMedicineHolder childMedicineHolder = (ListMedicineAdapter.childMedicineHolder) holder;
        childMedicineHolder.txtChildMedicineList.setText(childMedicine);

    }

    @Override
    public int getItemCount() {
        return childMedicineList.size();
    }

    public void addItem (String childMedicine) {
        childMedicineList.add(childMedicine);
        notifyDataSetChanged();
    }
}
