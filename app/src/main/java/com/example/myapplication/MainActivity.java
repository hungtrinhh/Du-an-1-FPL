package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.Dialog.DialogLostconnection;
import com.example.myapplication.Fragment.fragment_Login;
import com.example.myapplication.Fragment.fragment_Main;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Login()).commit();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, filter);

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean status = false;
            Log.d("TAG", "onReceive: " + status);
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    status = true;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    status = true;

                } else {
                    status = false;
                }
            }
            DialogLostconnection dialog = new DialogLostconnection(getApplicationContext());

            if (status) {
                //        SnackBar hien thi ket noi wifi
                dialog.show();
            } else {
                dialog.cancel();
            }


        }
    };

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
