package com.example.myapplication.Fragment.fragDifferent;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Hoadonnaptien;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class FragmentNapTien extends Fragment {
    private TextInputEditText edTienNap;
    private Button btnNap;
    private View viewFrag;
    private ImageView img_backToUser;
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
    }

    public void anhXa(View view) {
        edTienNap = view.findViewById(R.id.ed_tien_nap);
        btnNap = view.findViewById(R.id.btn_nap);
        img_backToUser = view.findViewById(R.id.btnBackToUser);
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String date = dateFormat.format(java.util.Calendar.getInstance().getTime());
                        hoadonnaptien.setDate(date);
                        FbDao.AddHoaDonNap(hoadonnaptien);
                        Toast.makeText(getActivity(), "Yêu Cầu Nạp Tiền Của Bạn Đang Được Xử Lí", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStack();

                    }
                } catch (Exception exception) {
                    Toast.makeText(getActivity(), "Nhập Số Tiền Nạp Là Số", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backToUser(){
        img_backToUser.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
}