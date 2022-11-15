package com.example.myapplication.Fragment.fragmentChild;

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

public class fragment_EditProfile extends Fragment implements View.OnClickListener {
    private AppCompatButton btn_SaveProfile;
    private LinearLayout btn_ChangeAvatar;
    private EditText ed_UpdateFullName;
    private EditText ed_UpdatePhoneNumbers;
    private ImageView btnBackToUser;


    public fragment_EditProfile() {

    }


    public static fragment_EditProfile newInstance(String param1, String param2) {
        fragment_EditProfile fragment = new fragment_EditProfile();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__edit_profile, container, false);
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

        btn_SaveProfile = v.findViewById(R.id.btn_SaveProfile);
        btn_ChangeAvatar = v.findViewById(R.id.btn_ChangeAvatar);
        ed_UpdateFullName = v.findViewById(R.id.ed_UpdateFullName);
        ed_UpdatePhoneNumbers = v.findViewById(R.id.ed_UpdatePhoneNumbers);
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