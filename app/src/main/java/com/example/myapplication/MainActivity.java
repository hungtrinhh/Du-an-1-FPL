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
        //1343333
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new fragment_Main()).commit();
        System.out.println("Hug sieu cap vippro");
        for (int i = 0; i < i; i++) {
            System.out.println(i+1);
            System.out.println("trinh duc gung");
        }
    }
}
