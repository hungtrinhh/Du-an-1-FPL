package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Game;
import com.example.myapplication.R;

import java.util.List;

public class GameUuDaiVerticalAdapter extends RecyclerView.Adapter<GameUuDaiVerticalAdapter.VoucherViewHoler> {
    private List<Game> listGame;
    private Context context;

    public GameUuDaiVerticalAdapter() {
    }

    public GameUuDaiVerticalAdapter(Context context) {
        this.context = context;
    }

    public void setListDanhSachGame(List<Game> listGame) {
        this.listGame = listGame;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoucherViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item_vertical,parent,false);
        return new VoucherViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHoler holder, int position) {
        Game game = listGame.get(position);
        if(game == null){
            return;
        }
        holder.tvTenGame.setText(game.getTenGame());
    }

    @Override
    public int getItemCount() {
        if(listGame!= null){
            return listGame.size();
        }
        return 0;
    }

    public class VoucherViewHoler extends RecyclerView.ViewHolder {
        private ImageView imageView2;
        private TextView tvTenGame;
        public VoucherViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            tvTenGame = (TextView) itemView.findViewById(R.id.tv_tenGame);
        }
    }
}
