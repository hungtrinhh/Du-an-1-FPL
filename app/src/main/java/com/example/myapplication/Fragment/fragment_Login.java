package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.R;


public class fragment_Login extends Fragment {

    private LinearLayout layoutLogoWhite;
    private EditText edEmailLogin;
    private EditText edPasswordLogin;
    private Switch swRememberAccount;
    private Button btnLogin;
    private TextView btnGotoregister;


    public fragment_Login() {

    }

    private void Anhxa(View v) {


        layoutLogoWhite = (LinearLayout) v.findViewById(R.id.layout_logoWhite);
        edEmailLogin = (EditText) v.findViewById(R.id.edEmailLogin);
        edPasswordLogin = (EditText) v.findViewById(R.id.edPasswordLogin);
        swRememberAccount = (Switch) v.findViewById(R.id.swRememberAccount);
        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        btnGotoregister = (TextView) v.findViewById(R.id.btnGotoregister);

    }

    public static fragment_Login newInstance() {
        fragment_Login fragment = new fragment_Login();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Main()).commit();
            }
        });

    }
}