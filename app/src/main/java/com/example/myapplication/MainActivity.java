package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.Fragment.fragment_Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Main()).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        dialog hoac Toast de thoat
    }
}
