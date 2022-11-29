package com.example.myapplication.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class UpdateGameService extends IntentService {
    String TAG = "UpdateGameService";

    public UpdateGameService() {
        super("UpdateGameService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG, "loaded: ");
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("UpdateGameService"));
        this.stopSelf();
    }
}
