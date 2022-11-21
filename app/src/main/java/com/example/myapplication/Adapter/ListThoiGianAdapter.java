package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.PlayTime;
import com.example.myapplication.R;

import java.util.List;

public class ListThoiGianAdapter extends RecyclerView.Adapter<ListThoiGianAdapter.ThoiGianViewHoler> {
    private Context context;
    private List<PlayTime> playTimes;
    public ListThoiGianAdapter() {
    }
    public ListThoiGianAdapter(Context context) {
        this.context = context;
    }

    public void setListThoiGian(List<PlayTime> list) {
        this.playTimes = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ThoiGianViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thoigianchoi,parent,false);
        return new ThoiGianViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThoiGianViewHoler holder, int position) {
        PlayTime playTime = playTimes.get(position);
        if(playTime == null){
            return;
        }
        holder.img_thoiGian.setImageResource(playTime.getImg());
    }

    @Override
    public int getItemCount() {
        if(playTimes!= null){
            return playTimes.size();
        }
        return 0;
    }

    public class ThoiGianViewHoler extends RecyclerView.ViewHolder {
        private ImageView img_thoiGian;
        public ThoiGianViewHoler(@NonNull View itemView) {
            super(itemView);
            img_thoiGian = itemView.findViewById(R.id.img_thoiGian);
        }
    }
}
