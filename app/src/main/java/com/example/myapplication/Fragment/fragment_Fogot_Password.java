package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class fragment_Fogot_Password extends Fragment {
    //    khai báo
    private ImageView btn_BackToLogin;
    private AppCompatButton btn_ResetPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fogot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        ánh xạ
        btn_BackToLogin = view.findViewById(R.id.btn_BackToLogin);
        btn_ResetPassword = view.findViewById(R.id.btn_ResetPassword);
//        bắt sự kiện
        btn_BackToLogin.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Login()).commit();
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
