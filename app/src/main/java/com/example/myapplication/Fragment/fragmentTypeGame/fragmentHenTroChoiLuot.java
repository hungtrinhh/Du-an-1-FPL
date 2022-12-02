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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                ImageView close = dialog.findViewById(R.id.close);
                AppCompatButton button = dialog.findViewById(R.id.btn_chotLich);
                edt_day = dialog.findViewById(R.id.edt_day);
                numberPicker_minutes = dialog.findViewById(R.id.numberpick_minutes);
                numberPicker_seconds = dialog.findViewById(R.id.numberpick_seconds);
                numberPicker_minutes.setMaxValue(23);
                numberPicker_minutes.setMinValue(0);
                numberPicker_minutes.setFormatter(new NumberPicker.Formatter() {
                    @Override
                    public String format(int i) {
                        return String.format("%02d", i);
                    }
                });
                numberPicker_minutes.setValue(9);
                numberPicker_seconds.setMaxValue(59);
                numberPicker_seconds.setMinValue(0);
                numberPicker_seconds.setFormatter(new NumberPicker.Formatter() {
                    @Override
                    public String format(int i) {
                        return String.format("%02d", i);
                    }
                });
                numberPicker_seconds.setValue(0);
                close.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });
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
                        if(timeStart.length() <= 0){
                            Snackbar snackbar = Snackbar.make(getView(),"Vui lòng chọn ngày",2000);
                            View snackbar_view = snackbar.getView();
                            TextView tv_bar = snackbar_view.findViewById(com.google.android.material.R.id.snackbar_text);
                            tv_bar.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.stop,0);
                            snackbar.show();
                            return;
                        }
                        int timeM = numberPicker_seconds.getValue() + 10;
                        int timeH = numberPicker_minutes.getValue();
                        if(timeM >= 60){
                            timeM = timeM - 60;
                            timeH++;
                        }

                        Date a_date1 = new Date();
                        SimpleDateFormat a_fmtDay1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Calendar c1 = Calendar.getInstance();
                        c1.setTimeInMillis(System.currentTimeMillis());
                        c1.set(Calendar.HOUR,numberPicker_minutes.getValue());
                        c1.set(Calendar.MINUTE,numberPicker_seconds.getValue());
                        c1.set(Calendar.DAY_OF_MONTH,mDay);
                        c1.set(Calendar.MONTH,mMonth);
                        c1.set(Calendar.YEAR,mYear);
                        a_date1.setTime(c1.getTimeInMillis());
                        String time1 = a_fmtDay1.format(a_date1);

                        Date a_date2 = new Date();
                        SimpleDateFormat a_fmtDay2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Calendar c2 = Calendar.getInstance();
                        c2.setTimeInMillis(System.currentTimeMillis());
                        c2.set(Calendar.HOUR,timeH);
                        c2.set(Calendar.MINUTE,timeM);
                        c2.set(Calendar.DAY_OF_MONTH,mDay);
                        c2.set(Calendar.MONTH,mMonth);
                        c2.set(Calendar.YEAR,mYear);
                        a_date2.setTime(c2.getTimeInMillis());
                        String time2 = a_fmtDay2.format(a_date2);

                        List<HoaDonHenGio> donHenGioList = FbDao.getListHoaDonHenGio();
                        boolean xet = true;
                        for(HoaDonHenGio item : donHenGioList){

                            SimpleDateFormat b_fmtDay = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                            try {

                                Date b_date1 = b_fmtDay.parse(item.getTimeStart());
                                Date b_date2 = b_fmtDay.parse(item.getTimeEnd());

                                if(item.getGameid().equals(String.valueOf(game.getId())) && item.isSuccess() == false){
                                    int ssDate_a1 = a_date1.compareTo(b_date1);
                                    int ssDate_a2 = a_date1.compareTo(b_date2);

                                    int ssDate_b1 = a_date2.compareTo(b_date1);
                                    int ssDate_b2 = a_date2.compareTo(b_date2);

                                    if((ssDate_a1 >= 0 && ssDate_a2 <= 0) || (ssDate_b1>=0 && ssDate_b2 <=0)){
                                        xet = false;
                                        break;
                                    }

                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        if(xet){
                            HoaDonHenGio hoaDonHenGio = new HoaDonHenGio();
                            hoaDonHenGio.setUserId(FbDao.UserLogin.getId());
                            hoaDonHenGio.setCost(total);
                            hoaDonHenGio.setGameid(String.valueOf(game.getId()));
                            hoaDonHenGio.setTimeStart(time1);
                            hoaDonHenGio.setTimeEnd(time2);
                            FbDao.AddHoaDonHenGio(hoaDonHenGio);
                            FbDao.Thanhtoantien(total);
                            dialog.cancel();
                            Snackbar.make(getView(),"Đặt lịch thành công",2000).show();
                        }else{
                            dialog.cancel();
                            Snackbar.make(getView(),"Khung giờ này đã có người chọn. Vui lòng chọn giờ khác",2000).show();
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