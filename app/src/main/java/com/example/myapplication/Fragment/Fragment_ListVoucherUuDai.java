package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.Adapter.GameVerticalAdapter;
import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListVoucherUuDai#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListVoucherUuDai extends Fragment {
    private RecyclerView recyclerView_voucher_ListGame;
    private ImageView btn_BackToUuDai;
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private List<Voucher> listVoucher;
    private Toolbar toolbar;
    public Fragment_ListVoucherUuDai() {
        // Required empty public constructor
    }

    public static Fragment_ListVoucherUuDai newInstance() {
        Fragment_ListVoucherUuDai fragment = new Fragment_ListVoucherUuDai();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__list_voucher_uu_dai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        ShowListVoucher();
    }
    private void AnhXa(View view){
        recyclerView_voucher_ListGame = view.findViewById(R.id.recyclerview_voucher_ListGame);
        btn_BackToUuDai = view.findViewById(R.id.btn_BackToUuDai_fragVoucher);
    }
    private void ShowListVoucher(){
        listVoucher = new ArrayList<>();
        listVoucher.add(new Voucher(1,1,"khong"));
//        listGame = FbDao.getListGame();
        voucherVerticalAdapter = new VoucherVerticalAdapter(getActivity());
        voucherVerticalAdapter.setListDanhSachVoucher(listVoucher);
        recyclerView_voucher_ListGame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_voucher_ListGame.setAdapter(voucherVerticalAdapter);
    }
}