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
<<<<<<< HEAD

        System.out.println("trinh duc hung");

=======
        System.out.println("hung 123");
>>>>>>> 9b474d97b6609a176d78db5926b089c43b6f4b93
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new fragment_Main()).commit();


    }
}
