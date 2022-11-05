package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Fragment.fragment_Login;
import com.example.myapplication.Reciver.ReciverCheckingInternet;

public class MainActivity extends AppCompatActivity {
    ReciverCheckingInternet broadcastReceiver = new ReciverCheckingInternet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Login()).commit();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        registerReceiver(broadcastReceiver, filter);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        dialog hoac Toast de thoat
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}
