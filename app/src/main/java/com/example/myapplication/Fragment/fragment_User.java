package com.example.myapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;


public class fragment_User extends Fragment implements View.OnClickListener {

    public fragment_User() {

    }

    // ko biết
    public static fragment_User newInstance() {
        fragment_User fragment = new fragment_User();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //khai báo view
    private TextView tvUserName;
    private TextView tvUserPhonenumber;
    private TextView tvNotyfi;
    private TextView tvHistory;
    private TextView tvHelp;
    private TextView tvStatiu;
    private TextView tvPolicyandPrivacy;
    private TextView tvLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        Onclick();
    }

    private void Onclick() {
        tvLogout.setOnClickListener(this::onClick);
    }

    private void Anhxa(View v) {
        tvUserName = v.findViewById(R.id.tvUser_name);
        tvUserPhonenumber = v.findViewById(R.id.tvUser_phonenumber);
        tvNotyfi = v.findViewById(R.id.tv_Notyfi);
        tvHistory = v.findViewById(R.id.tv_History);
        tvHelp = v.findViewById(R.id.tv_Help);
        tvStatiu = v.findViewById(R.id.tv_Statiu);
        tvPolicyandPrivacy = v.findViewById(R.id.tv_PolicyandPrivacy);
        tvLogout = v.findViewById(R.id.tv_Logout);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Logout:
                SharedPreferences s = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = s.edit();
                editor.clear();
                editor.commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Login()).commit();
                break;
            case R.id.tvUser_name:




                break;
        }
    }
}