package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.example.myapplication.Adapter.VoucherAdapter;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


public class fragment_Uudai extends Fragment{
    private VoucherAdapter voucherAdapter;
    private List<Voucher> voucherList;
    private SliderView imageSlider;
    private List<Voucher> voucherSearchList;
    private RecyclerView recyclerviewVoucher;
    private androidx.appcompat.widget.SearchView searchView_uuDai;
    private static final String TAG = "ReadVoucher";

    //    scroll view dạng horizontal
    public fragment_Uudai() {

    }


    public static fragment_Uudai newInstance() {
        fragment_Uudai fragment = new fragment_Uudai();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uudai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        FillRecycleView();
        Log.d(TAG, "onViewCreated: " + voucherList.size());
    }

    public void AnhXa(View view) {
        imageSlider = (SliderView) view.findViewById(R.id.image_slider);
        recyclerviewVoucher = (RecyclerView) view.findViewById(R.id.recyclerview_voucher);
        searchView_uuDai = (androidx.appcompat.widget.SearchView) view.findViewById(R.id.searchView_uuDai);
    }

    public void FillRecycleView() {
        voucherAdapter = new VoucherAdapter(getActivity());
        voucherList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Voucher");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Voucher voucher = dataSnapshot.getValue(Voucher.class);
                    voucherList.add(voucher);
                }
                setListVoucher();
                searchView_uuDai.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchUuDai(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchUuDai(newText);
                        return false;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: " + error.toException());

            }
        });
    }
    public void searchUuDai(String query){
        voucherSearchList = new ArrayList<>();
        if("".equalsIgnoreCase(query)){
            voucherAdapter.setListDanhSachVoucher(voucherList);
            recyclerviewVoucher.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerviewVoucher.setAdapter(voucherAdapter);
        }else {
            for(Voucher voucher : voucherList){
                if(voucher.getMaVoucher().toLowerCase().contains(query)){
                    voucherSearchList.add(voucher);
                }
            }voucherAdapter.setListDanhSachVoucher(voucherSearchList);
            recyclerviewVoucher.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerviewVoucher.setAdapter(voucherAdapter);
        }
    }
    public void setListVoucher(){
        voucherAdapter.setListDanhSachVoucher(voucherList);
        recyclerviewVoucher.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerviewVoucher.setAdapter(voucherAdapter);
    }
}