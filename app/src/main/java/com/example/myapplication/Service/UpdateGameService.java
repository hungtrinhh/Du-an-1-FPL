package com.example.myapplication.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class UpdateGameService extends IntentService {


    public UpdateGameService() {
        super("UpdateVoucherService");
    }

    String TAG = "UpdateVoucherService";

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "sended: ");
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent("UpdateVoucherService"));
        stopService(intent);
    }
}
