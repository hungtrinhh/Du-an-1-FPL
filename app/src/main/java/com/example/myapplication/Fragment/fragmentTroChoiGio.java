package com.example.myapplication.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DanhSachGameAdapter;
import com.example.myapplication.Adapter.ListThoiGianAdapter;
import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.PlayTime;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.example.myapplication.SetOnClickItemIterface.OnclickItemGame;
import com.example.myapplication.SetOnClickItemIterface.OnclickItemTime;
import com.example.myapplication.SetOnClickItemIterface.OnclickItemVoucher;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentTroChoiGio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentTroChoiGio extends Fragment implements View.OnClickListener {
    private TextView tv_nameGame, tv_cost, tv_detailGame,tv_voucherChoose,tv_totalCost;
    private LinearLayout choose_voucher;
    private RecyclerView recyclerView_voucher_gio, recyclerview_choose_time;
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private ListThoiGianAdapter listThoiGianAdapter;
    private List<PlayTime> list = new ArrayList<>();
    private List<Voucher> listVoucher;
    private ImageView close_dialog, backToDSGame;
    private  Dialog dialog;
    private Voucher voucherChoose;
    private PlayTime playTime_choose;
    private Button btn_play;
    private float total=0;
    private Game game;
    private int arr[] = {R.drawable.time5, R.drawable.time10, R.drawable.time15, R.drawable.time20, R.drawable.time25, R.drawable.time30, R.drawable.time35, R.drawable.time40, R.drawable.time45, R.drawable.time50, R.drawable.time55, R.drawable.time60};
    private float sale;

    public static fragmentTroChoiGio newInstance() {
        fragmentTroChoiGio fragment = new fragmentTroChoiGio();
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
        return inflater.inflate(R.layout.fragment_tro_choi_gio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        AddTime();
        ShowThoiGian();
        setThongTin();
        fragment_Trangchu.gochild = true;
        choose_voucher.setOnClickListener(this::onClick);
        backToDSGame.setOnClickListener(this::onClick);
        btn_play.setOnClickListener(this::onClick);

    }

    private void AnhXa(View view) {
        backToDSGame = view.findViewById(R.id.btn_backToDSGame);
        choose_voucher = view.findViewById(R.id.choose_voucher);
        recyclerview_choose_time = view.findViewById(R.id.recyclerview_choose_time);
        tv_nameGame = view.findViewById(R.id.tv_nameGame);
        tv_cost = view.findViewById(R.id.tv_cost);
        tv_detailGame = view.findViewById(R.id.tv_detailGame);
        tv_voucherChoose = view.findViewById(R.id.tv_voucherChoose);
        tv_totalCost = view.findViewById(R.id.tv_totalCost);
        btn_play = view.findViewById(R.id.btn_play);

    }

    private void ShowListVoucher() {
        listVoucher = FbDao.getListVoucher();
        voucherVerticalAdapter = new VoucherVerticalAdapter(new OnclickItemVoucher() {
            @Override
            public void onclickItemVoucher(Voucher voucher) {
                onClickItemChooseVoucher(voucher);
            }
        });
        voucherVerticalAdapter.setListDanhSachVoucher(listVoucher);
        recyclerView_voucher_gio.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_voucher_gio.setAdapter(voucherVerticalAdapter);
    }

    private void onClickItemChooseVoucher(Voucher voucher) {
        voucherChoose = voucher;
        tv_voucherChoose.setText(voucherChoose.getMaVoucher());
        TinhTongTien();
        dialog.dismiss();
    }

    private void ShowThoiGian() {
        listThoiGianAdapter = new ListThoiGianAdapter(new OnclickItemTime() {
            @Override
            public void onclickItemTime(PlayTime playTime) {
                onClickItemChooseTime(playTime);
            }
        });
        listThoiGianAdapter.setListThoiGian(list);
        recyclerview_choose_time.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview_choose_time.setAdapter(listThoiGianAdapter);
    }

    private void onClickItemChooseTime(PlayTime playTime) {
        playTime_choose = playTime;
        TinhTongTien();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_voucher:
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_choosevouher_gio);
                recyclerView_voucher_gio = dialog.findViewById(R.id.recyclerview_voucher_gio);
                close_dialog = dialog.findViewById(R.id.close_dialog);
                close_dialog.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });
                ShowListVoucher();

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;
            case R.id.btn_backToDSGame:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.btn_play:
                if (playTime_choose==null){
                    Snackbar.make(getView(), "Vui lòng chọn thời gian chơi", 2000).show();
                }else {
                    //bla blaaaaa
                }
                break;
        }
    }

    private void AddTime() {
        for (int i = 0; i < arr.length; i++) {
            list.add(new PlayTime(i, arr[i]));
        }
    }

    private void setThongTin() {
        Bundle bundle = getArguments();
        game = (Game) bundle.get("obj_game");
        tv_nameGame.setText(game.getTenGame());
        tv_cost.setText(game.getGia() + " / 5 phút");
        tv_detailGame.setText(game.getMoTa());
    }


    private void TinhTongTien(){
        if (playTime_choose!=null){
            if (voucherChoose==null){
                for (int i=0;i<12;i++){
                    if (playTime_choose.getId()==i){
                        total = game.getGia()*(i+1);
                    }
                }
            }else {
                for (int i=0;i<12;i++){
                    if (playTime_choose.getId()==i){
                        sale = voucherChoose.getGiamGia();
                        total = game.getGia()*(i+1)*(1-(sale/100));
                    }
                }
            }
        }
        tv_totalCost.setText(total+"đ");
    }
}