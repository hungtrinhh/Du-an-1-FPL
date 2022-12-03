package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.Hoadon;
import com.example.myapplication.Model.Hoadonchoigame;
import com.example.myapplication.Model.Hoadonnaptien;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHoler> {
    private final List<Hoadon> list;
    public HistoryAdapter(List<Hoadon> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHoler holder, int position) {

        if (list.get(position).getClass().toString().equals(Hoadonnaptien.class.toString())) {
            Hoadonnaptien hoadonnaptien = (Hoadonnaptien) list.get(position);
            if(hoadonnaptien.isTrangThai()){
                holder.imgHistory.setImageResource(R.drawable.ic_baseline_add_circle_24);
                holder.tvLoaiGd.setText("Nạp tiền");
                String pattern = "+ ###,###,### p";
                DecimalFormat df = new DecimalFormat(pattern);
                holder.tvGiaTien.setText(df.format(hoadonnaptien.getCost()));
                holder.tvNgayGd.setText(hoadonnaptien.getDate());
            }
        }else {
            Hoadonchoigame hoadonchoigame = (Hoadonchoigame) list.get(position);
            holder.imgHistory.setImageResource(R.drawable.ic_baseline_remove_circle_24);
            holder.tvLoaiGd.setText("Chơi game: " + FbDao.getNameGameFromID(Integer.parseInt(hoadonchoigame.getGameid())));
            String pattern = "- ###,###,### p";
            DecimalFormat df = new DecimalFormat(pattern);
            holder.tvGiaTien.setText(df.format(hoadonchoigame.getCost()));
            holder.tvNgayGd.setText(hoadonchoigame.getDateStart());
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class HistoryViewHoler extends RecyclerView.ViewHolder {
        private final ImageView imgHistory;
        private final TextView tvLoaiGd;
        private final TextView tvNgayGd;
        private final TextView tvGiaTien;

        public HistoryViewHoler(@NonNull View itemView) {
            super(itemView);
            imgHistory = (ImageView) itemView.findViewById(R.id.img_history);
            tvLoaiGd = (TextView) itemView.findViewById(R.id.tv_loai_gd);
            tvNgayGd = (TextView) itemView.findViewById(R.id.tv_ngay_gd);
            tvGiaTien = (TextView) itemView.findViewById(R.id.tv_gia_tien);

        }
    }
}
