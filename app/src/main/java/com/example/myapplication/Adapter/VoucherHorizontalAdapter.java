package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;

import java.util.List;

public class VoucherHorizontalAdapter extends RecyclerView.Adapter<VoucherHorizontalAdapter.VoucherViewHoler> {
    private List<Voucher> listDanhSachVoucher;
    private Context context;

    public VoucherHorizontalAdapter() {
    }

    public VoucherHorizontalAdapter(Context context) {
        this.context = context;
    }

    public void setListDanhSachVoucher(List<Voucher> listDanhSachVoucher) {
        this.listDanhSachVoucher = listDanhSachVoucher;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoucherViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_item_horizontal,parent,false);
        return new VoucherViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHoler holder, int position) {
        Voucher voucher = listDanhSachVoucher.get(position);
        if(voucher == null){
            return;
        }
        holder.tv_TieuDeVoucher.setText("Giảm " + voucher.getGiamGia() + "% mọi loại máy");
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
        private TextView tv_TieuDeVoucher;
        private LinearLayout linearLayout_voucher;
        public VoucherViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            tv_TieuDeVoucher = (TextView) itemView.findViewById(R.id.tv_tieuDeVoucher);
        }
    }
}
