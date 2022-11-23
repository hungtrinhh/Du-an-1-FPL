package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListVoucherUuDaiTenTroChoi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListVoucherUuDaiTenTroChoi extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView_voucher_ListGame;
    private ImageView btn_BackToUuDai_fragVoucher, btn_Search_fragVoucher;
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private List<Voucher> listVoucher;
    private androidx.appcompat.widget.SearchView searchView_listVoucherUuDai;

    public Fragment_ListVoucherUuDaiTenTroChoi() {
        // Required empty public constructor
    }

    public Fragment_ListVoucherUuDaiTenTroChoi(List<Voucher> listVoucher) {
        this.listVoucher = listVoucher;
    }

    public static Fragment_ListVoucherUuDaiTenTroChoi newInstance() {
        Fragment_ListVoucherUuDaiTenTroChoi fragment = new Fragment_ListVoucherUuDaiTenTroChoi();
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

        searchView_listVoucherUuDai.setVisibility(View.GONE);
        // bắt sự kiện khi click
        btn_BackToUuDai_fragVoucher.setOnClickListener(this::onClick);
        btn_Search_fragVoucher.setOnClickListener(this::onClick);
    }

    private void AnhXa(View view) {
        recyclerView_voucher_ListGame = view.findViewById(R.id.recyclerview_voucher_ListGame);
        btn_BackToUuDai_fragVoucher = view.findViewById(R.id.btn_BackToUuDai_fragVoucher);
        searchView_listVoucherUuDai = view.findViewById(R.id.searchView_listVoucherUuDai);
        btn_Search_fragVoucher = view.findViewById(R.id.btn_search_fragVoucher);
    }

    private void ShowListVoucher() {
        voucherVerticalAdapter = new VoucherVerticalAdapter(getActivity());
        voucherVerticalAdapter.setListDanhSachVoucher(listVoucher);
        recyclerView_voucher_ListGame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_voucher_ListGame.setAdapter(voucherVerticalAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_BackToUuDai_fragVoucher:
                getActivity().getSupportFragmentManager().popBackStack();
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.content_frame, new fragment_Uudai()).commit();
                break;
            case R.id.btn_search_fragVoucher:
                if (searchView_listVoucherUuDai.getVisibility() == View.GONE) {
                    searchView_listVoucherUuDai.setVisibility(View.VISIBLE);
                    searchView_listVoucherUuDai.onActionViewExpanded();
                } else {
                    searchView_listVoucherUuDai.setVisibility(View.GONE);
                }
                break;
        }
    }
}