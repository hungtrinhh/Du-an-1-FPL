package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.fragment.fragment_Main;
import com.example.myapplication.fragment.fragment_Trangchu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("dcm");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new fragment_Main()).commit();


    }
}
