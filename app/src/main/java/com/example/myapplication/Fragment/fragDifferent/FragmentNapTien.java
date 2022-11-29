package com.example.myapplication.Fragment.fragDifferent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Hoadonnaptien;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentNapTien extends Fragment implements View.OnClickListener {
    private TextInputEditText edTienNap;
    private Button btnNap;
    private View viewFrag;
    private ImageView img_backToUser;
    private AppCompatButton btn20;
    private AppCompatButton btn30;
    private AppCompatButton btn50;
    private AppCompatButton btn100;
    private AppCompatButton btn200;
    private AppCompatButton btn500;
    private AppCompatButton btn1000;
    private AppCompatButton btn2000;
    private AppCompatButton btn5000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nap_tien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFrag = view;
        anhXa(view);
        SeteventNaptien();
        backToUser();
        btnOnclick();
        onChangNapTien();
    }

    private void onChangNapTien() {
        edTienNap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkMenhGia(editable.toString());
            }
        });
    }

    public void checkMenhGia(String menhGia) {
        if (menhGia.equals("20000")) {
            btn20.setBackgroundResource(R.drawable.item_background);
            btn20.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn20.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn20.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("30000")) {
            btn30.setBackgroundResource(R.drawable.item_background);
            btn30.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn30.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn30.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("50000")) {
            btn50.setBackgroundResource(R.drawable.item_background);
            btn50.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn50.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn50.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("100000")) {
            btn100.setBackgroundResource(R.drawable.item_background);
            btn100.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn100.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn100.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("200000")) {
            btn200.setBackgroundResource(R.drawable.item_background);
            btn200.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn200.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn200.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("500000")) {
            btn500.setBackgroundResource(R.drawable.item_background);
            btn500.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn500.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn500.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("1000000")) {
            btn1000.setBackgroundResource(R.drawable.item_background);
            btn1000.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("1000000");
        } else {
            btn1000.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn1000.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("2000000")) {
            btn2000.setBackgroundResource(R.drawable.item_background);
            btn2000.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("2000000");
        } else {
            btn2000.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn2000.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (menhGia.equals("5000000")) {
            btn5000.setBackgroundResource(R.drawable.item_background);
            btn5000.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            btn5000.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn5000.setTextColor(Color.parseColor("#3F51B5"));
        }
    }

    public void anhXa(View view) {
        edTienNap = view.findViewById(R.id.ed_tien_nap);
        btnNap = view.findViewById(R.id.btn_nap);
        img_backToUser = view.findViewById(R.id.btn_backNapTien);
        btn20 = (AppCompatButton) view.findViewById(R.id.btn_20);
        btn30 = (AppCompatButton) view.findViewById(R.id.btn_30);
        btn50 = (AppCompatButton) view.findViewById(R.id.btn_50);
        btn100 = (AppCompatButton) view.findViewById(R.id.btn_100);
        btn200 = (AppCompatButton) view.findViewById(R.id.btn_200);
        btn500 = (AppCompatButton) view.findViewById(R.id.btn_500);
        btn1000 = (AppCompatButton) view.findViewById(R.id.btn_1000);
        btn2000 = (AppCompatButton) view.findViewById(R.id.btn_2000);
        btn5000 = (AppCompatButton) view.findViewById(R.id.btn_5000);
    }

    public void btnOnclick() {
        btn20.setOnClickListener(this::onClick);
        btn30.setOnClickListener(this::onClick);
        btn50.setOnClickListener(this::onClick);
        btn100.setOnClickListener(this::onClick);
        btn200.setOnClickListener(this::onClick);
        btn500.setOnClickListener(this::onClick);
        btn1000.setOnClickListener(this::onClick);
        btn2000.setOnClickListener(this::onClick);
        btn5000.setOnClickListener(this::onClick);
    }

    public void SeteventNaptien() {
        btnNap.setOnClickListener(view -> {
            Hoadonnaptien hoadonnaptien = new Hoadonnaptien();

            hoadonnaptien.setUserId(FbDao.UserLogin.getId());
            if (edTienNap.getText().toString().isEmpty()) {
                Snackbar.make(viewFrag, "Bạn chưa nhập vào số tiền muốn nạp", 2000).show();
            } else {
                try {
                    float tienNap = Float.parseFloat(edTienNap.getText().toString());
                    hoadonnaptien.setCost(tienNap);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String date = dateFormat.format(new Date());
                    hoadonnaptien.setDate(date);
                    FbDao.AddHoaDonNap(hoadonnaptien);
                    Toast.makeText(getActivity(), "Yêu Cầu Nạp Tiền Của Bạn Đang Được Xử Lí", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                } catch (Exception exception) {
                    Toast.makeText(getActivity(), "Nhập Số Tiền Nạp Là Số", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backToUser() {
        img_backToUser.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_20) {
            btn20.setBackgroundResource(R.drawable.item_background);
            btn20.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("20000");
        } else {
            btn20.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn20.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_30) {
            btn30.setBackgroundResource(R.drawable.item_background);
            btn30.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("30000");
        } else {
            btn30.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn30.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_50) {
            btn50.setBackgroundResource(R.drawable.item_background);
            btn50.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("50000");
        } else {
            btn50.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn50.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_100) {
            btn100.setBackgroundResource(R.drawable.item_background);
            btn100.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("100000");
        } else {
            btn100.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn100.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_200) {
            btn200.setBackgroundResource(R.drawable.item_background);
            btn200.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("200000");
        } else {
            btn200.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn200.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_500) {
            btn500.setBackgroundResource(R.drawable.item_background);
            btn500.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("500000");
        } else {
            btn500.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn500.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_1000) {
            btn1000.setBackgroundResource(R.drawable.item_background);
            btn1000.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("1000000");
        } else {
            btn1000.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn1000.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_2000) {
            btn2000.setBackgroundResource(R.drawable.item_background);
            btn2000.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("2000000");
        } else {
            btn2000.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn2000.setTextColor(Color.parseColor("#3F51B5"));
        }
        if (view.getId() == R.id.btn_5000) {
            btn5000.setBackgroundResource(R.drawable.item_background);
            btn5000.setTextColor(Color.parseColor("#FFFFFFFF"));
            edTienNap.setText("5000000");
        } else {
            btn5000.setBackgroundResource(R.drawable.background_item_menh_gia);
            btn5000.setTextColor(Color.parseColor("#3F51B5"));
        }
    }
}