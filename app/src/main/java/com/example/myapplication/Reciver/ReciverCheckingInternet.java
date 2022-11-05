package com.example.myapplication.Reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ReciverCheckingInternet extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = false;

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
        Toast.makeText(context, status+"", Toast.LENGTH_SHORT).show();
        if (status) {

        } else {


        }


    }
}
