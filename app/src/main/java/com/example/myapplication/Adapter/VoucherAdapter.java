package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;

import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHoler> {
    private List<Voucher> listDanhSachVoucher;
    private Context context;

    public VoucherAdapter() {
    }

    public VoucherAdapter(Context context) {
        this.context = context;
    }

    public void setListDanhSachVoucher(List<Voucher> listDanhSachVoucher) {
        this.listDanhSachVoucher = listDanhSachVoucher;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoucherViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_item,parent,false);
        return new VoucherViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHoler holder, int position) {
        Voucher voucher = listDanhSachVoucher.get(position);
        if(voucher == null){
            return;
        }
        holder.tv_MaVoucher.setText(voucher.getMaVoucher());
        holder.tv_TieuDeVoucher.setText("Giảm " + voucher.getGiamGia() + "% mọi loại máy");
        holder.tv_dung.setOnClickListener(view -> {
            Toast.makeText(context, voucher.getMaVoucher(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        if(listDanhSachVoucher!= null){
            return listDanhSachVoucher.size();
        }
        return 0;
    }

    public class VoucherViewHoler extends RecyclerView.ViewHolder {private ImageView imageView2;
        private TextView tv_MaVoucher;
        private TextView tv_TieuDeVoucher,tv_dung;
        public VoucherViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            tv_MaVoucher = (TextView) itemView.findViewById(R.id.tv_maVoucher);
            tv_TieuDeVoucher = (TextView) itemView.findViewById(R.id.tv_tieuDeVoucher);
            tv_dung = (TextView) itemView.findViewById(R.id.tv_dung);
        }
    }
}
