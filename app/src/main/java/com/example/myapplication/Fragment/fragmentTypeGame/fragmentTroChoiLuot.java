package com.example.myapplication.Fragment.fragmentTypeGame;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Fragment.fragmentMainChild.fragment_Trangchu;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.example.myapplication.Interface.OnclickItemVoucher;

import java.util.ArrayList;
import java.util.List;


public class fragmentTroChoiLuot extends Fragment implements View.OnClickListener {
    private TextView tv_nameGame_Luot, tv_cost_Luot, tv_detailGame_Luot, tv_count, tv_voucherChoose, tv_totalCost;
    private ImageButton imgButtonadd, imgButtonremove;
    private ImageView close_dialog;
    private LinearLayout choose_voucher;
    private ImageView backToDSGame;
    private int count = 0;
    private RecyclerView recyclerView_voucher_gio;
    private Dialog dialog;
    private List<Voucher> listVoucher;
    private final List<Voucher> voucherListGameChoose = new ArrayList<>();
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private Voucher voucherChoose;
    private Game game;
    private float total = 0;
    private float sale;
    private Button btn_play;

    public static fragmentTroChoiLuot newInstance() {
        fragmentTroChoiLuot fragment = new fragmentTroChoiLuot();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_Trangchu.gochild = true;
        return inflater.inflate(R.layout.fragment_tro_choi_luot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        setThongTin();
        checkBtndis();
        imgButtonadd.setOnClickListener(this::onClick);
        imgButtonremove.setOnClickListener(this::onClick);
        backToDSGame.setOnClickListener(this::onClick);
        choose_voucher.setOnClickListener(this::onClick);
        btn_play.setOnClickListener(this::onClick);
    }

    private void AnhXa(View view) {
        tv_nameGame_Luot = view.findViewById(R.id.tv_nameGame_Luot);
        tv_cost_Luot = view.findViewById(R.id.tv_cost_Luot);
        tv_detailGame_Luot = view.findViewById(R.id.tv_detailGame_Luot);
        imgButtonadd = view.findViewById(R.id.btn_add);
        imgButtonremove = view.findViewById(R.id.btn_remove);
        tv_count = view.findViewById(R.id.tv_count);
        backToDSGame = view.findViewById(R.id.btn_backToDSGame);
        choose_voucher = view.findViewById(R.id.choose_voucher);
        tv_voucherChoose = view.findViewById(R.id.tv_voucherChoose);
        tv_totalCost = view.findViewById(R.id.tv_totalCost);
        btn_play = view.findViewById(R.id.btn_play);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                count++;
                TinhTongTien();

                checkBtndis();
                tv_count.setText(count + "");
                break;
            case R.id.btn_remove:

                if (count > 0) {
                    count--;

                }
                tv_count.setText(count + "");
                TinhTongTien();
                checkBtndis();
                break;
            case R.id.btn_backToDSGame:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.choose_voucher:
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_choosevouher_gio);
                recyclerView_voucher_gio = dialog.findViewById(R.id.recyclerview_voucher_gio);
                close_dialog = dialog.findViewById(R.id.close_dialog);
                close_dialog.setOnClickListener(v1 -> {
                    dialog.dismiss();
                    checkBtndis();
                });
                ShowListVoucher();
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;
            case R.id.btn_play:
                FbDao dao = new FbDao();
                dao.PlaygameGio(count, game.getId() + "", total);
                getActivity().getSupportFragmentManager().popBackStack();
                FbDao.Thanhtoantien(total);

                break;
        }
    }

    private void checkBtndis() {
        imgButtonremove.setEnabled(count > 0);

        if(total > FbDao.UserLogin.getSodu() ||count==0){
            btn_play.setEnabled(false);
        }else {
            btn_play.setEnabled(true);
        }


    }

    private void setThongTin() {
        Bundle bundle = getArguments();
        game = (Game) bundle.get("obj_game");
        tv_count.setText(count + "");
        tv_nameGame_Luot.setText(game.getTenGame());
        tv_cost_Luot.setText(game.getGia() + "/1 lượt 1 phút");
        tv_detailGame_Luot.setText(game.getMoTa());
    }

    private void ShowListVoucher() {
        listVoucher = FbDao.getListVoucher();
        for (Voucher voucher : listVoucher) {
            if (voucher.getLoaiGame() == game.getId() || voucher.getLoaiGame() == 0) {
                voucherListGameChoose.add(voucher);
            }
        }
        voucherVerticalAdapter = new VoucherVerticalAdapter(new OnclickItemVoucher() {
            @Override
            public void onclickItemVoucher(Voucher voucher) {
                onClickItemChooseVoucher(voucher);
            }
        });
        voucherVerticalAdapter.setListDanhSachVoucher(voucherListGameChoose);
        recyclerView_voucher_gio.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_voucher_gio.setAdapter(voucherVerticalAdapter);


    }

    private void onClickItemChooseVoucher(Voucher voucher) {
        voucherChoose = voucher;
        tv_voucherChoose.setText(voucher.getMaVoucher());
        TinhTongTien();
        dialog.dismiss();
        checkBtndis();

    }

    private void TinhTongTien() {
        if (voucherChoose == null) {
            total = game.getGia() * count;
        } else {
            sale = voucherChoose.getGiamGia();
            total = game.getGia() * count * (1 - (sale / 100));
        }
        tv_totalCost.setText(total + "đ");
    }
}