package com.example.myapplication.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.Nullable;

public class serviceCheckInternet extends IntentService {


    public serviceCheckInternet() {
        super("serviceCheckInternet");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private String Check(Context context) {

        String status = "";
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {

            //kiểm tra loại mạng
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled";
                Log.d("TAG", "Internet type: " + status);
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled";
                Log.d("TAG", "Internet type: " + status);
                return status;
            }
        } else {
//nếu không có mạng trả về chuỗi rỗng;
            return status;
        }
        return status;
    }

}
