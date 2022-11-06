package com.example.myapplication.Fragment;

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
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_verify_Phone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_verify_Phone extends Fragment {
    private ImageView btnBackToRegister;
    private EditText edCode1;
    private EditText edCode2;
    private EditText edCode3;
    private EditText edCode4;
    private EditText edCode5;
    private EditText edCode6;
    private TextView btnSendvetifiAgain;
    private AppCompatButton btnCheckphone;


    public fragment_verify_Phone() {
        // Required empty public constructor
    }


    public static fragment_verify_Phone newInstance(String param1, String param2) {
        fragment_verify_Phone fragment = new fragment_verify_Phone();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_verify_phone_numbers, container, false);
    }

    private void Anhxa(View v) {
        btnBackToRegister = v.findViewById(R.id.btnBackToRegister);
        edCode1 = v.findViewById(R.id.edCode1);
        edCode2 = v.findViewById(R.id.edCode2);
        edCode3 = v.findViewById(R.id.edCode3);
        edCode4 = v.findViewById(R.id.edCode4);
        edCode5 = v.findViewById(R.id.edCode5);
        edCode6 = v.findViewById(R.id.edCode6);
        btnSendvetifiAgain = v.findViewById(R.id.btnSendvetifiAgain);
        btnCheckphone = v.findViewById(R.id.btnCheckphone);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);


    }
}