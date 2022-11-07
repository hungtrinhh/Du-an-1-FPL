package com.example.myapplication.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;

import java.util.List;


public class fragment_splastScreen extends Fragment {

    private ImageView imageView;
    private TextView textView;
    private List<User> userList;
    private boolean dk1 = false;

    public fragment_splastScreen() {

    }


    public static fragment_splastScreen newInstance() {
        fragment_splastScreen fragment = new fragment_splastScreen();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("getUserFromInternet"));
        getActivity().startService(new Intent("getUserFromInternet"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dk1) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Login(userList));
                }
            }
        }, 2000);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getBundleExtra("b");
            userList = (List<User>) b.getSerializable("list");
            dk1 = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dk1) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Login(userList));

                    }
                }
            }, 2000);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    private void Anhxa(View v) {
        imageView = v.findViewById(R.id.buiqwiuiqubi);
        textView = v.findViewById(R.id.asnwqnjasnjqoe);

        imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        textView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));

    }
}