package com.example.myapplication.Dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class DialogLostconnection extends Dialog {

    public DialogLostconnection(@NonNull Context context) {

        super(context);
        setContentView(R.layout.dialog_lostconecttion);

    }
}
