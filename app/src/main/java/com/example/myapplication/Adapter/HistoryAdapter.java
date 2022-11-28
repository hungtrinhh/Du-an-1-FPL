package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHoler>{
    @NonNull
    @Override
    public HistoryViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new HistoryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HistoryViewHoler extends RecyclerView.ViewHolder {
        public HistoryViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
