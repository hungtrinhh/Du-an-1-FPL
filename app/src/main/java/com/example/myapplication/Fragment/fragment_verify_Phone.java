package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_verify_Phone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_verify_Phone extends Fragment {



    public fragment_verify_Phone() {
        // Required empty public constructor
    }


    public static fragment_verify_Phone newInstance(String param1, String param2) {
        fragment_verify_Phone fragment = new fragment_verify_Phone();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_verify_phone_numbers, container, false);
    }
}