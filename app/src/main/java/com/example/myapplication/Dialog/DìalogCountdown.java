package com.example.myapplication.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.R;

public class DìalogCountdown extends Dialog {

    TextView tv_minutes;
    TextView tv_seconds;
    public static DìalogCountdown dìalogCountdown;
    private Context context;

    public DìalogCountdown(@NonNull Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_timeup);
        tv_minutes = findViewById(R.id.phut);
        tv_seconds = findViewById(R.id.giay);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dìalogCountdown = new DìalogCountdown(context);

    }

    private static int phut = 0;
    private static int giay = 0;

    public void setTimeout(long milisecond) {

        if (milisecond <= 0) {
            return;
        }
        dìalogCountdown.show();

        giay = (int) (milisecond / 1000);

        while (giay >= 60) {

            phut = giay / 60;
            giay = giay % 60;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!(phut == 0 && giay == 0)) {

                    giay--;
                    if (giay == 0 && phut > 0) {
                        giay = 59;
                        phut--;
                    }
                    if (!dìalogCountdown.isShowing()) {
                        break;
                    }

                    try {
                        Thread.sleep(999);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tv_minutes.setText(phut + "");
                    tv_seconds.setText(giay + "");
                }
                if(dìalogCountdown.isShowing()){
                    dìalogCountdown.dismiss();

                }
            }
        }).start();


    }


}
