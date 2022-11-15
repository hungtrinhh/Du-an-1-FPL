package com.example.myapplication.Fragment.fragmentUserchild;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class fragment_Editprofile extends Fragment implements View.OnClickListener {
    private AppCompatButton btnSaveProfile;
    private LinearLayout btnChoseAvartar;
    private EditText edEditprofileName;
    private EditText edEditprofilePhoneNumber;
    private ImageView btnBackToUser;


    public fragment_Editprofile() {

    }


    public static fragment_Editprofile newInstance(String param1, String param2) {
        fragment_Editprofile fragment = new fragment_Editprofile();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__editprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        Onclick();
    }

    private void Onclick() {
        btnBackToUser.setOnClickListener(this::onClick);
    }

    private void Anhxa(View v) {

        btnBackToUser = v.findViewById(R.id.btnBackToUser);

        btnSaveProfile = v.findViewById(R.id.btn_saveProfile);
        btnChoseAvartar = v.findViewById(R.id.btn_choseAvartar);
        edEditprofileName = v.findViewById(R.id.ed_editprofileName);
        edEditprofilePhoneNumber = v.findViewById(R.id.ed_editprofilePhoneNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToUser:
                getActivity().getSupportFragmentManager().popBackStack();
                break;


        }
    }
}