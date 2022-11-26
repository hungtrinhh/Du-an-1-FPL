package com.example.myapplication.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Game;
import com.example.myapplication.R;
import com.example.myapplication.Interface.OnclickItemGame;

import java.util.List;

public class DanhSachGameAdapter extends RecyclerView.Adapter<DanhSachGameAdapter.DanhSachGameViewHoler> {
    private List<Game> listGame;
    private OnclickItemGame onclickItemGame;

    private int[]  imageAvatarGame = new int[]{ R.drawable.game_ghost_house,R.drawable.game_bounce_house,R.drawable.racingcar,R.drawable.gun, R.drawable.game_nhun_nhay,R.drawable.game_bao_nha , R.drawable.game_jumping_house, R.drawable.game_cau_truot, R.drawable.game_suc_cac, R.drawable.game_xich_du};

    public DanhSachGameAdapter(OnclickItemGame onclickItemGame) {
        this.onclickItemGame = onclickItemGame;
    }

    public DanhSachGameAdapter() {
    }

    public void setListGame(List<Game> listGame) {
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
        for (int i=1;i<listGame.size()+1;i++){
            if (game.getId()==i){
                holder.img_AvatarGame.setImageResource(imageAvatarGame[i-1]);
            }
        }
        if (game.getTrangThai().equalsIgnoreCase("Bảo Trì")) {
            holder.tvTrangThai.setTextColor(Color.parseColor("#E04119"));

        } else if (game.getTrangThai().equalsIgnoreCase("Đang được chơi")) {

            holder.tvTrangThai.setTextColor(Color.parseColor("#FFE15D"));


        } else {
            holder.tvTrangThai.setTextColor(Color.parseColor("#2FC863"));
        }
        holder.linearLayoutDanhSachGame.setOnClickListener(view -> {
            onclickItemGame.onclickItemGame(game);
        });

    }

    @Override
    public int getItemCount() {
        if (listGame != null) {
            return listGame.size();
        }
        return 0;
    }


    public class DanhSachGameViewHoler extends RecyclerView.ViewHolder {
        private final TextView tvTenGame;
        private final ImageView img_AvatarGame;
        private final TextView tvTrangThai;
        private final LinearLayout linearLayoutDanhSachGame;

        public DanhSachGameViewHoler(@NonNull View itemView) {
            super(itemView);
            img_AvatarGame = itemView.findViewById(R.id.img_AvatarGame);
            tvTenGame = itemView.findViewById(R.id.tv_ten_game);
            tvTrangThai = itemView.findViewById(R.id.tv_trang_thai);
            linearLayoutDanhSachGame = itemView.findViewById(R.id.linear_danh_sach_game);
        }
    }
}
