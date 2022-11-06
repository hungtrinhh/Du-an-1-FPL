package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;


public class fragment_verify_Phone extends Fragment implements View.OnClickListener {
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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        SetupEditext();


    }

    private void Anhxa(View v) {
        edCode1 = v.findViewById(R.id.edCode1);
        edCode2 = v.findViewById(R.id.edCode2);
        edCode3 = v.findViewById(R.id.edCode3);
        edCode4 = v.findViewById(R.id.edCode4);
        edCode5 = v.findViewById(R.id.edCode5);
        edCode6 = v.findViewById(R.id.edCode6);

        btnBackToRegister = v.findViewById(R.id.btnBackToRegister);
        btnSendvetifiAgain = v.findViewById(R.id.btnSendvetifiAgain);
        btnCheckphone = v.findViewById(R.id.btnCheckphone);

        btnCheckphone.setOnClickListener(this::onClick);
        btnSendvetifiAgain.setOnClickListener(this::onClick);
        btnBackToRegister.setOnClickListener(this::onClick);

    }

    private void SetupEditext() {
        edCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode3.requestFocus();
                } else {
                    edCode1.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode4.requestFocus();
                } else {
                    edCode2.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode5.requestFocus();
                } else {
                    edCode3.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode6.requestFocus();
                } else {
                    edCode4.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {

                } else {
                    edCode5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToRegister:
                getActivity().getSupportFragmentManager().popBackStack();


                break;
            case R.id.btnSendvetifiAgain:


                break;
            case R.id.btnCheckphone:


                break;

        }

    }
}