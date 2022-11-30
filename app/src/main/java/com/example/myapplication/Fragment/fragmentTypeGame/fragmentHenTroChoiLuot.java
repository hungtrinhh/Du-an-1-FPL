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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.VoucherVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Fragment.fragDifferent.fragment_QRcode;
import com.example.myapplication.Fragment.fragment_Main;
import com.example.myapplication.Interface.OnclickItemVoucher;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.HoaDonHenGio;
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
 * Use the {@link fragmentHenTroChoiLuot#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentHenTroChoiLuot extends Fragment implements View.OnClickListener{
    private TextView tv_nameGame_Luot, tv_cost_Luot, tv_detailGame_Luot, tv_count, tv_voucherChoose, tv_totalCost;
    private ImageButton imgButtonadd, imgButtonremove;
    private ImageView backToDSGame;
    private ImageView close_dialog;
    private EditText edt_day;
    private LinearLayout choose_voucher;
    private AppCompatButton btn_henGio;
    private ImageView imgGame;
    private Game game;
    private Voucher voucherChoose;
    private List<Voucher> listVoucher;
    private final List<Voucher> voucherListGameChoose = new ArrayList<>();
    private VoucherVerticalAdapter voucherVerticalAdapter;
    private RecyclerView recyclerView_voucher_gio;
    private Dialog dialog;
    private float total = 0;
    private float sale;
    private int count = 0;
    private int mDay,mMonth,mYear;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String pattern = "###,### Poin";
    DecimalFormat df = new DecimalFormat(pattern);
    private NumberPicker numberPicker_minutes,numberPicker_seconds;

    public static fragmentHenTroChoiLuot newInstance() {
        fragmentHenTroChoiLuot fragment = new fragmentHenTroChoiLuot();
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
        return inflater.inflate(R.layout.fragment_hen_tro_choi_luot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        setThongTin();
        checkBtndis();
        TinhTongTien();
        imgButtonadd.setOnClickListener(this::onClick);
        imgButtonremove.setOnClickListener(this::onClick);
        backToDSGame.setOnClickListener(this::onClick);
        choose_voucher.setOnClickListener(this::onClick);
        btn_henGio.setOnClickListener(this::onClick);
        btn_henGio.setEnabled(false);
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
        btn_henGio = view.findViewById(R.id.btn_henGio);
        imgGame = view.findViewById(R.id.imgGame);
        btn_henGio.setEnabled(false);
    }

    private void setThongTin() {
        Bundle bundle = getArguments();
        game = (Game) bundle.get("obj_game");
        tv_count.setText(count + "");
        tv_nameGame_Luot.setText(game.getTenGame());
        tv_cost_Luot.setText(df.format(game.getGia()) + " / 1 lượt 1 phút");
        tv_detailGame_Luot.setText(game.getMoTa());
        imgGame.setImageResource(game.getImgGame());
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


    private void checkBtndis() {
        imgButtonremove.setEnabled(count > 0);

        if (total > FbDao.UserLogin.getSodu() || count == 0) {
            btn_henGio.setEnabled(false);
        } else {
            btn_henGio.setEnabled(true);
        }
    }

    private void TinhTongTien() {
        if (voucherChoose == null) {
            total = game.getGia() * count;
        } else {
            sale = voucherChoose.getGiamGia();
            total = game.getGia() * count * (1 - (sale / 100));
        }
        tv_totalCost.setText(df.format(total));
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
                if (fragment_QRcode.check) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Main()).commit();
                } else {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
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
            case R.id.btn_henGio:
                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_hengiochoi);
                ImageView imgTime = dialog.findViewById(R.id.chooseDay);
                AppCompatButton button = dialog.findViewById(R.id.btn_chotLich);
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

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String timeStart = edt_day.getText().toString()+" "+numberPicker_minutes.getValue()+":"+numberPicker_seconds.getValue();
                        int timeM = numberPicker_seconds.getValue() + 10;
                        int timeH = numberPicker_minutes.getValue();
                        if(timeM >= 60){
                            timeM = timeM - 60;
                            timeH++;
                        }
                        String timeEnd = edt_day.getText().toString()+" "+timeH+":"+timeM;
                        int a1 = Integer.parseInt(String.valueOf(numberPicker_minutes.getValue()).concat(String.valueOf(numberPicker_seconds.getValue())));
                        int a2 = Integer.parseInt(String.valueOf(timeH).concat(String.valueOf(timeM)));

                        List<HoaDonHenGio> donHenGioList = FbDao.getListHoaDonHenGio();
                        boolean xet = true;
                        for(HoaDonHenGio item : donHenGioList){

                            String arrTime1[] = item.getTimeStart().split(" ");
                            String arrTime2[] = item.getTimeEnd().split(" ");

                            String arrTimeH1[] = arrTime1[1].split(":");
                            String arrTimeH2[] = arrTime2[1].split(":");

                            int b1 = Integer.parseInt(arrTimeH1[0].concat(arrTimeH1[1]));
                            int b2 = Integer.parseInt(arrTimeH2[0].concat(arrTimeH2[1]));

                            if(item.getGameid().equals(String.valueOf(game.getId())) && item.isSuccess() == false){
                                if(a1 >= b1 && a2 <= b2){
                                    xet = false;
                                    break;
                                }
                            }
                        }

                        if(xet){
                            HoaDonHenGio hoaDonHenGio = new HoaDonHenGio();
                            hoaDonHenGio.setUserId(FbDao.UserLogin.getId());
                            hoaDonHenGio.setCost(total);
                            hoaDonHenGio.setGameid(String.valueOf(game.getId()));
                            hoaDonHenGio.setTimeStart(timeStart);
                            hoaDonHenGio.setTimeEnd(timeEnd);
                            FbDao.AddHoaDonHenGio(hoaDonHenGio);
                            FbDao.Thanhtoantien(total);
                            dialog.cancel();
                            Snackbar.make(getView(),"Đặt lịch thành công",2000).show();
                        }else{
                            dialog.cancel();
                            Snackbar.make(getView(),"Khung giờ này đã có người chọn.Vui lòng chọn giờ khác",2000).show();
                        }

                    }
                });
                dialog.show();
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