package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;


public class fragment_splastScreen extends Fragment {

    private ImageView imageView;
    private TextView textView;


    public fragment_splastScreen() {

    }


    public static fragment_splastScreen newInstance(String param1, String param2) {
        fragment_splastScreen fragment = new fragment_splastScreen();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    private void Anhxa(View v) {
        imageView = v.findViewById(R.id.buiqwiuiqubi);
        textView = v.findViewById(R.id.asnwqnjasnjqoe);

        imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        textView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));

    }
}