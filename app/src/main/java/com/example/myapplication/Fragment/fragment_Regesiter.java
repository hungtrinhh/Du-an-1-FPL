package com.example.myapplication.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class fragment_Regesiter extends Fragment {
    private ImageView btnBackToLogin;
    private TextView tvConditions;
    private TextInputLayout edregisterUsername;
    private TextInputLayout edregisterPhone;
    private TextInputLayout edregisterPassword;
    private TextInputLayout edregisterConfirmPassword;
    private CheckBox chkcheckLaw;
    private AppCompatButton btnRegister;


    //    bỏ switch button hoặc không
//    thêm animation fade in với chạy từ phải sang trái cho các phần tử

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_regesiter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Anhxa(view);


        tvConditions.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getLayoutInflater();
            View viewDialog = inflater.inflate(R.layout.dialog_conditions, null);
            //        builder view
            builder.setView(viewDialog);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();
        });

        btnBackToLogin.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Login()).commit();

        });





        super.onViewCreated(view, savedInstanceState);
    }

    private void Anhxa(View v) {
        btnBackToLogin = (ImageView) v.findViewById(R.id.btnBackToLogin);
        edregisterUsername = (TextInputLayout) v.findViewById(R.id.edregisterUsername);
        edregisterPhone = (TextInputLayout) v.findViewById(R.id.edregisterPhone);
        edregisterPassword = (TextInputLayout) v.findViewById(R.id.edregisterPassword);
        edregisterConfirmPassword = (TextInputLayout) v.findViewById(R.id.edregisterConfirmPassword);
        chkcheckLaw = (CheckBox) v.findViewById(R.id.chkcheckLaw);
        tvConditions = (TextView) v.findViewById(R.id.tvConditions);
        btnRegister = (AppCompatButton) v.findViewById(R.id.btnRegister);
    }
}
