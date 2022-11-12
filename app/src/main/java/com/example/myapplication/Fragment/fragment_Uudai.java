package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.GameHorizontalAdapter;
import com.example.myapplication.Adapter.GameVerticalAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Adapter.VoucherHorizontalAdapter;
import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class fragment_Uudai extends Fragment {
    private VoucherHorizontalAdapter voucherHorizontalAdapter;
    private List<Voucher> voucherList;
    private SliderView imageSlider;
    private List<Voucher> voucherSearchList;
    private RecyclerView recyclerviewVoucher;
    private RecyclerView recyclerViewGame;
    private androidx.appcompat.widget.SearchView searchView_uuDai;
    private TextView tv_showAllVoucher;
    private TextView tv_showAllGame;
    private List<Game> listGame;
    private static final String TAG = "ReadVoucher";
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private GameHorizontalAdapter gameHorizontalAdapter;
    private GameVerticalAdapter gameVerticalAdapter;
    private String[] testList = {"Việt Nam", "Englang", "Vn", "EN"};

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
        FillRecycleViewVoucher();
        FillRecycleViewGame();
        showAllVoucher();
        showAllGame();
        Log.d(TAG, "onViewCreated: " + voucherList.size());

        animation(imageSlider);

        // khai báo mảng ảnh và gán giá trị src ảnh
        int[] img = new int[]{R.drawable.banner11, R.drawable.banner20};
        SliderAdapter adapter = new SliderAdapter(img);
        // set lên slideAdapter
        imageSlider.setSliderAdapter(adapter);
    }

    public void AnhXa(View view) {
        imageSlider = (SliderView) view.findViewById(R.id.image_slider);
        recyclerviewVoucher = (RecyclerView) view.findViewById(R.id.recyclerview_voucher);
        searchView_uuDai = (androidx.appcompat.widget.SearchView) view.findViewById(R.id.searchView_uuDai);
        tv_showAllVoucher = (TextView) view.findViewById(R.id.tv_show_all_voucher);
        recyclerViewGame = view.findViewById(R.id.recyclerview_danh_muc);
        tv_showAllGame = view.findViewById(R.id.tv_show_all_game);
    }
    public void showAllVoucher() {
        tv_showAllVoucher.setOnClickListener(view -> {
//            if (tv_showAllVoucher.getText().toString().equals("Xem Tất Cả")) {
//                tv_showAllVoucher.setText("Ẩn Bớt");
//                voucherVerticalAdapter = new VoucherVerticalAdapter(getActivity());
//                voucherVerticalAdapter.setListDanhSachVoucher(voucherList);
//                recyclerviewVoucher.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//                recyclerviewVoucher.setAdapter(voucherVerticalAdapter);
//            } else {
//                tv_showAllVoucher.setText("Xem Tất Cả");
//                FillRecycleViewVoucher();
//            }
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, Fragment_ListVoucherUuDai.newInstance()).commit();
        });
    }

    public void FillRecycleViewVoucher() {
        voucherList = FbDao.getListVoucher();
        voucherHorizontalAdapter = new VoucherHorizontalAdapter(getActivity());
        voucherHorizontalAdapter.setListDanhSachVoucher(voucherList);
        recyclerviewVoucher.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerviewVoucher.setAdapter(voucherHorizontalAdapter);
    }
    /////////////

    public void showAllGame() {
        tv_showAllGame.setOnClickListener(view -> {
//            if (tv_showAllGame.getText().toString().equals("Xem Tất Cả")) {
//                tv_showAllGame.setText("Ẩn Bớt");
//                gameVerticalAdapter = new GameVerticalAdapter(getActivity());
//                gameVerticalAdapter.setListDanhSachGame(listGame);
//                recyclerViewGame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//                recyclerViewGame.setAdapter(gameVerticalAdapter);
//            } else {
//                tv_showAllGame.setText("Xem Tất Cả");
//                FillRecycleViewGame();
//            }
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, Fragment_ListGameUuDai.newInstance()).commit();
        });
    }

    public void FillRecycleViewGame() {
        listGame = FbDao.getListGame();
        gameHorizontalAdapter = new GameHorizontalAdapter(getActivity());
        gameHorizontalAdapter.setListDanhSachGame(listGame);
        recyclerViewGame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewGame.setAdapter(gameHorizontalAdapter);
    }

    public void searchUuDai(String query) {
        voucherSearchList = new ArrayList<>();
        if ("".equalsIgnoreCase(query)) {
            voucherHorizontalAdapter.setListDanhSachVoucher(voucherList);
            recyclerviewVoucher.setAdapter(voucherHorizontalAdapter);
        } else {
            for (Voucher voucher : voucherList) {
                if (voucher.getMaVoucher().toLowerCase().contains(query)) {
                    voucherSearchList.add(voucher);
                }
            }
            voucherHorizontalAdapter.setListDanhSachVoucher(voucherSearchList);
            recyclerviewVoucher.setAdapter(voucherHorizontalAdapter);
        }
    }

    // khai báo hàm animation
    private void animation(SliderView imageSlider) {
        imageSlider.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
    }
}