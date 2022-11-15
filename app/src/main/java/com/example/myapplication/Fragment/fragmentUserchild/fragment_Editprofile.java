package com.example.myapplication.Fragment.fragmentUserchild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class fragment_Editprofile extends Fragment {




    public fragment_Editprofile() {

    }


    public static fragment_Editprofile newInstance(String param1, String param2) {
        fragment_Editprofile fragment = new fragment_Editprofile();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__editprofile, container, false);
    }
}