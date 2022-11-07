package com.example.myapplication.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class getUserFromInternet extends IntentService {


    public getUserFromInternet() {
        super("getUserFromInternet");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        List<User> list = FbDao.ReadUser();
        Intent i = new Intent("getUserFromInternet");
        Bundle b = new Bundle();
        b.putSerializable("list", (Serializable) list);
        i.putExtra("b", b);

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);
    }
}
