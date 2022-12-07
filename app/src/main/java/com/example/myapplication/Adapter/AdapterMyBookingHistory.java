package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.HoaDonHenGio;
import com.example.myapplication.Model.Hoadonchoigame;
import com.example.myapplication.R;

import java.util.List;

public class AdapterMyBookingHistory extends RecyclerView.Adapter<AdapterMyBookingHistory.Viewholder> {


    List<HoaDonHenGio> donHenGioList;

    public AdapterMyBookingHistory(List<HoaDonHenGio> donHenGioList) {
        this.donHenGioList = donHenGioList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_booking_history, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        HoaDonHenGio donHenGio = donHenGioList.get(position);
        Game game = FbDao.getGameFromID(Integer.parseInt(donHenGio.getGameid()));
        holder.imgAvatarofGame.setImageResource(game.getImgGame());

        holder.tvDateStartCancel.setText("Từ: " + donHenGio.getTimeStart());
        holder.tvDateEndCancle.setText("Đến: " + donHenGio.getTimeEnd());
        holder.tvNameOfGameCancel.setText("Trò chơi: " + game.getTenGame());

        holder.btnDeleteMyHis.setOnClickListener(v -> {
            Toast.makeText(holder.btnDeleteMyHis.getContext(), game.getTenGame(), 2000).show();
        });
    }

    @Override
    public int getItemCount() {
        if (donHenGioList != null) {
            return donHenGioList.size();
        }

        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private LinearLayout btnDeleteMyHis;
        private ImageView imgAvatarofGame;
        private TextView tvNameOfGameCancel;
        private TextView tvDateStartCancel;
        private TextView tvDateEndCancle;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            btnDeleteMyHis = itemView.findViewById(R.id.btnDelete_myHis);
            imgAvatarofGame = itemView.findViewById(R.id.img_AvatarofGame);
            tvNameOfGameCancel = itemView.findViewById(R.id.tv_nameOfGameCancel);
            tvDateStartCancel = itemView.findViewById(R.id.tv_dateStartCancel);
            tvDateEndCancle = itemView.findViewById(R.id.tv_dateEndCancle);


        }
    }
}
