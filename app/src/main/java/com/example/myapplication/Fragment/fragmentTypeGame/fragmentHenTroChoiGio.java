package com.example.myapplication.Fragment.fragmentTypeGame;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ListThoiGianAdapter;
import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Fragment.fragDifferent.fragment_QRcode;
import com.example.myapplication.Fragment.fragmentMainChild.fragment_Trangchu;
import com.example.myapplication.Fragment.fragment_Main;
import com.example.myapplication.Interface.OnclickItemTime;
import com.example.myapplication.Interface.OnclickItemVoucher;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.PlayTime;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentHenTroChoiGio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentHenTroChoiGio extends Fragment implements View.OnClickListener{
    private TextView tv_nameGame, tv_cost, tv_detailGame, tv_voucherChoose, tv_totalCost;
    private ImageView close_dialog, backToDSGame;
    private ImageView imgGame;
    private EditText edt_day;
    private LinearLayout choose_voucher;
    private Voucher voucherChoose;
    private RecyclerView recyclerView_voucher_gio, recyclerview_choose_time;
    private AppCompatButton btn_henGio;
    private List<Voucher> listVoucher;
    private final List<Voucher> voucherListGameChoose = new ArrayList<>();
    private final List<PlayTime> list = new ArrayList<>();
    private Game game;
    private PlayTime playTime_choose;
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private ListThoiGianAdapter listThoiGianAdapter;
    private final int[] arr = {R.drawable.time5, R.drawable.time10, R.drawable.time15, R.drawable.time20, R.drawable.time25, R.drawable.time30, R.drawable.time35, R.drawable.time40, R.drawable.time45, R.drawable.time50, R.drawable.time55, R.drawable.time60};
    private final int[] arrTime = {5,10,15,20,25,30,35,40,45,50,55,60};//mảng thời gian tính theo phút
    private float sale;
    private Dialog dialog;
    String pattern = "###,### Poin";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat df = new DecimalFormat(pattern);
    private float total = 0;
    private int mDay,mMonth,mYear;
    private NumberPicker numberPicker_minutes,numberPicker_seconds;

    //Thời gian chơi
    private int time;

    //thời gian hệ thống ( hiện tại )
    private int presentTimeHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private int presentTimeMinutes = Calendar.getInstance().get(Calendar.MINUTE);
    //thời gian hệ thống ( khi chơi )
    private int playingTimeHours = presentTimeHours;//gán trc giá trị là giờ của hệ thống
    private int playingTimeMinutes = presentTimeMinutes;

    public static fragmentHenTroChoiGio newInstance() {
        fragmentHenTroChoiGio fragment = new fragmentHenTroChoiGio();
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
        return inflater.inflate(R.layout.fragment_hen_tro_choi_gio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        AddTime();
        ShowThoiGian();
        setThongTin();
        TinhTongTien();
        choose_voucher.setOnClickListener(this::onClick);
        backToDSGame.setOnClickListener(this::onClick);
        btn_henGio.setOnClickListener(this::onClick);
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
        btn_henGio = view.findViewById(R.id.btn_henGio);
        imgGame = view.findViewById(R.id.imgGame);
        btn_henGio.setEnabled(false);
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
        tv_voucherChoose.setText(voucherChoose.getMaVoucher());
        TinhTongTien();
        dialog.dismiss();
    }

    private void AddTime() {
        for (int i = 0; i < arr.length; i++) {
            list.add(new PlayTime(i, arr[i]));
        }
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

    private void setThongTin() {
        Bundle bundle = getArguments();
        game = (Game) bundle.get("obj_game");
        tv_nameGame.setText(game.getTenGame());
        tv_cost.setText(df.format(game.getGia()) + " / 5 phút");
        tv_detailGame.setText(game.getMoTa());
        imgGame.setImageResource(game.getImgGame());
    }

    private void TinhTongTien() {
        if (playTime_choose != null) {
            if (voucherChoose == null) {
                for (int i = 0; i < arr.length; i++) {
                    if (playTime_choose.getId() == i) {
                        time = i + 1;
                        playingTimeMinutes += arrTime[i];//gán giá trị tương ứng với số phút trên dánh sách
                        if(playingTimeMinutes >= 60){
                            int time_temp = playingTimeMinutes - arrTime[i];
                            playingTimeMinutes = time_temp;
                            playingTimeHours++;
                        }
                        total = game.getGia() * (i + 1);
                    }
                }
            } else {
                for (int i = 0; i < arr.length; i++) {
                    if (playTime_choose.getId() == i) {
                        time = i + 1;
                        if(playingTimeMinutes >= 60){
                            int time_temp = playingTimeMinutes - arrTime[i];
                            playingTimeMinutes = time_temp;
                            playingTimeHours++;
                        }
                        sale = voucherChoose.getGiamGia();
                        total = game.getGia() * (i + 1) * (1 - (sale / 100));

                    }
                }
            }
        }
        tv_totalCost.setText(df.format(total));
        checkBtndis();
    }

    private void checkBtndis() {
        if (total > FbDao.UserLogin.getSodu()||total==0) {
            btn_henGio.setEnabled(false);
        } else {
            btn_henGio.setEnabled(true);
        }
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
                    getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_henGio:
                if (playTime_choose == null) {
                    Snackbar.make(getView(), "Vui lòng chọn thời gian chơi", 2000).show();
                } else {
                    Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_hengiochoi);
                    ImageView imgTime = dialog.findViewById(R.id.chooseDay);
                    edt_day = dialog.findViewById(R.id.edt_day);
                    numberPicker_minutes = dialog.findViewById(R.id.numberpick_minutes);
                    numberPicker_seconds = dialog.findViewById(R.id.numberpick_seconds);
                    numberPicker_minutes.setMaxValue(23);
                    numberPicker_minutes.setMinValue(0);
                    numberPicker_minutes.setValue(9);
                    numberPicker_seconds.setMaxValue(59);
                    numberPicker_seconds.setMinValue(0);
                    numberPicker_seconds.setValue(0);
                    numberPicker_minutes.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                        }
                    });
                    numberPicker_seconds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                        }
                    });
                    imgTime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog d = new DatePickerDialog(getActivity(),0,dateStart,mYear,mMonth,mDay);
                            d.show();
                        }
                    });
                    dialog.show();
                }
                break;
        }
    }
    DatePickerDialog.OnDateSetListener dateStart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edt_day.setText(sdf.format(c.getTime()));
        }
    };
}