package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Fragment.fragment_SplashScreen;
import com.example.myapplication.Model.User;
import com.example.myapplication.Reciver.ReciverCheckingInternet;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static AlertDialog alertDialog;
    ReciverCheckingInternet broadcastReceiver = new ReciverCheckingInternet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        set startus bar transparent color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
*/
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_SplashScreen()).commit();
        FbDao dao = new FbDao();
        List<User> list = FbDao.getList();
        int a = 0;

    }

    @Override
    protected void onResume() {
        super.onResume();
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
