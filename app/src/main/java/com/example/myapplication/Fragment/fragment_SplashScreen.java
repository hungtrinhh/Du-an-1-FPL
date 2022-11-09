package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;


public class fragment_SplashScreen extends Fragment {

    private ImageView imageView;
    private TextView textView;


    public fragment_SplashScreen() {

    }


    public static fragment_SplashScreen newInstance() {
        fragment_SplashScreen fragment = new fragment_SplashScreen();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

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

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Login()).commit();

            }
        }, 3000);

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    private void Anhxa(View v) {
        imageView = v.findViewById(R.id.buiqwiuiqubi);
        textView = v.findViewById(R.id.asnwqnjasnjqoe);
        imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        textView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));

    }
}