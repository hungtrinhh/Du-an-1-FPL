package com.example.myapplication.Fragment.fragDifferent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
public class FragmentLichSuGiaoDich extends Fragment {
    private Toolbar toolbarDanhMuc;
    private ImageView btnBackNotify;
    private RecyclerView recyclerviewHistory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lich_su_giao_dich, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
    }

    private void anhXa(View view) {
        toolbarDanhMuc = (Toolbar) view.findViewById(R.id.toolbar_DanhMuc);
        btnBackNotify = (ImageView) view.findViewById(R.id.btn_backNotify);
        recyclerviewHistory = (RecyclerView) view.findViewById(R.id.recyclerview_history);
    }
}