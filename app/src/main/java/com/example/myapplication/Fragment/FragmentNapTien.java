package com.example.myapplication.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Hoadonnaptien;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class FragmentNapTien extends Fragment {
    private TextInputEditText edTienNap;
    private Button btnNap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nap_tien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        Nap();
    }

    public void anhXa(View view) {
        edTienNap = (TextInputEditText) view.findViewById(R.id.ed_tien_nap);
        btnNap = (Button) view.findViewById(R.id.btn_nap);
    }

    public void Nap() {
        btnNap.setOnClickListener(view -> {
            Hoadonnaptien hoadonnaptien = new Hoadonnaptien();

            hoadonnaptien.setUserId(FbDao.UserLogin.getId());
            if (edTienNap.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Vui Lòng Nhập Số Tiền Muốn Nạp", Toast.LENGTH_SHORT).show();
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
}