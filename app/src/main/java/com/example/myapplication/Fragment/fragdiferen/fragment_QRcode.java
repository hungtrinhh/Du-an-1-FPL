package com.example.myapplication.Fragment.fragdiferen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.myapplication.R;
import com.google.zxing.Result;


public class fragment_QRcode extends Fragment {
    private CodeScannerView qrcodeScaner;
    private TextView tvResuidQRcode;


    public fragment_QRcode() {

    }


    public static fragment_QRcode newInstance() {
        fragment_QRcode fragment = new fragment_QRcode();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__q_rcode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Anhxa(view);
    }

    private void Anhxa(View v) {
        qrcodeScaner = v.findViewById(R.id.qrcodeScaner);
        tvResuidQRcode = v.findViewById(R.id.tvResuidQRcode);

    }

    public void setUpQrcode() {


        CodeScanner codeScanner = new CodeScanner(getActivity(), qrcodeScaner);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

        qrcodeScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });

    }


}