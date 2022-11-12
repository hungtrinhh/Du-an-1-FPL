package com.example.myapplication.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Game;
import com.example.myapplication.R;

import java.util.List;

public class DanhSachGameAdapter extends RecyclerView.Adapter<DanhSachGameAdapter.DanhSachGameViewHoler> {
    private List<Game> listGame;

    public DanhSachGameAdapter(List<Game> listGame) {
        this.listGame = listGame;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhSachGameViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_sach_tro_choi, parent, false);
        return new DanhSachGameViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachGameViewHoler holder, int position) {
        Game game = listGame.get(position);
        if (game == null) {
            return;
        }
        holder.tvTenGame.setText(game.getTenGame());
        holder.tvTrangThai.setText(game.getTrangThai());
        if(game.getTrangThai().equalsIgnoreCase("Bảo Trì")){
            holder.tvTrangThai.setTextColor(Color.parseColor("#E04119"));
        }else{
            holder.tvTrangThai.setTextColor(Color.parseColor("#2FC863"));
        }
    }

    @Override
    public int getItemCount() {
        if (listGame != null){
            return listGame.size();
        }
        return 0;
    }

    public class DanhSachGameViewHoler extends RecyclerView.ViewHolder {
        private TextView tvTenGame;
        private TextView tvTrangThai;
        public DanhSachGameViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenGame = (TextView) itemView.findViewById(R.id.tv_ten_game);
            tvTrangThai = (TextView) itemView.findViewById(R.id.tv_trang_thai);


        }
    }
}
