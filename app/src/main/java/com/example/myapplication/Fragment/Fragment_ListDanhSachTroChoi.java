package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DanhSachGameAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListDanhSachTroChoi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListDanhSachTroChoi extends Fragment implements View.OnClickListener {
    private FrameLayout fragmentDanhMucListGameUuDai;
    private Toolbar toolbarDanhMuc;
    private ImageView btnBackToTrangChu;
    private ImageView btnSearchTroChoi;
    private SearchView searchViewListGame;
    private SliderView imageSlider;
    private RecyclerView recyclerviewListGame;
    private List<Game> listDanhSachGame;
    private List<Game> gameSearchList;
    private DanhSachGameAdapter danhSachGameAdapter;
    private TextView tvthongBao;
    public Fragment_ListDanhSachTroChoi() {
        // Required empty public constructor
    }

    public static Fragment_ListDanhSachTroChoi newInstance() {
        Fragment_ListDanhSachTroChoi fragment = new Fragment_ListDanhSachTroChoi();
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
        return inflater.inflate(R.layout.fragment_list_danh_sach_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        ShowListVoucher();

        // khai báo mảng ảnh và gán giá trị src ảnh
        int[] img = new int[]{R.drawable.banner11, R.drawable.banner20};
        SliderAdapter adapter = new SliderAdapter(img);
        // set lên slideAdapter
        imageSlider.setSliderAdapter(adapter);
        animation(imageSlider);
        searchViewListGame.setVisibility(View.GONE);
        // bắt sự kiện khi click
        btnBackToTrangChu.setOnClickListener(this::onClick);
        btnSearchTroChoi.setOnClickListener(this::onClick);
        searchGame();
    }

    private void AnhXa(View view) {
        fragmentDanhMucListGameUuDai = (FrameLayout) view.findViewById(R.id.fragmentDanhMuc_ListGameUuDai);
        toolbarDanhMuc = (Toolbar) view.findViewById(R.id.toolbar_DanhMuc);
        btnBackToTrangChu = (ImageView) view.findViewById(R.id.btn_backToTrangChu);
        btnSearchTroChoi = (ImageView) view.findViewById(R.id.btn_search_troChoi);
        searchViewListGame = (SearchView) view.findViewById(R.id.searchView_listGame);
        imageSlider = (SliderView) view.findViewById(R.id.image_slider);
        recyclerviewListGame = (RecyclerView) view.findViewById(R.id.recyclerview_ListGame);
        tvthongBao = view.findViewById(R.id.tv_thong_bao);
        tvthongBao.setVisibility(View.GONE);
    }

    private void ShowListVoucher() {
        listDanhSachGame = FbDao.getListGame();
        danhSachGameAdapter = new DanhSachGameAdapter();
        danhSachGameAdapter.setListGame(listDanhSachGame);
        recyclerviewListGame.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerviewListGame.setAdapter(danhSachGameAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_backToTrangChu:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new fragment_Trangchu()).commit();
                break;
            case R.id.btn_search_troChoi:
                if (searchViewListGame.getVisibility() == View.GONE) {
                    btnSearchTroChoi.setImageResource(R.drawable.ic_baseline_close_24);
                    searchViewListGame.setVisibility(View.VISIBLE);
                    searchViewListGame.onActionViewExpanded();
                } else {
                    searchViewListGame.setVisibility(View.GONE);
                    btnSearchTroChoi.setImageResource(R.drawable.ic_baseline_search_24);
                }
                break;
        }
    }

    private void animation(SliderView imageSlider) {
        imageSlider.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
    }
    public void searchGame() {
        searchViewListGame.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setListSerachGame(newText);
                return false;
            }
        });
    }

    private void setListSerachGame(String query) {
        if ("".equalsIgnoreCase(query)) {
            tvthongBao.setVisibility(View.GONE);
            danhSachGameAdapter.setListGame(listDanhSachGame);
            recyclerviewListGame.setAdapter(danhSachGameAdapter);
        } else {
            gameSearchList = new ArrayList<>();
            for (Game game : listDanhSachGame) {
                if (game.getTenGame().toLowerCase().contains(query)) {
                    gameSearchList.add(game);
                }
            }
            if(gameSearchList.size() == 0){
                tvthongBao.setVisibility(View.VISIBLE);
            }else {
                tvthongBao.setVisibility(View.GONE);
            }
            danhSachGameAdapter.setListGame(gameSearchList);
            recyclerviewListGame.setAdapter(danhSachGameAdapter);
        }
    }
}