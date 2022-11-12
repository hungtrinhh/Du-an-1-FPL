package com.example.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhSachGameAdapter extends RecyclerView.Adapter<DanhSachGameAdapter.DanhSachGameViewHoler>{
    @NonNull
    @Override
    public DanhSachGameViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachGameViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DanhSachGameViewHoler extends RecyclerView.ViewHolder {
        public DanhSachGameViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
