package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class fragment_Main extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private int itemselectID = R.id.bottomNav_Home;
    private FragmentManager fragmentManager = null;


    private BottomNavigationView bottomNav;

    public fragment_Main() {
    }

    public static fragment_Main newInstance() {
        fragment_Main fragment = new fragment_Main();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("TAG", "onCreateView: ");
        return inflater.inflate(R.layout.fragment_main, null);


    }

    private void Anhxa(View v) {

        Log.d("TAG", "Anhxa: ");
        bottomNav = (BottomNavigationView) v.findViewById(R.id.bottomNav);

        fragmentManager = getActivity().getSupportFragmentManager();
        bottomNav.getMenu().findItem(R.id.bottomNav_Home).setChecked(true);
        ReplaceFragment(new fragment_Trangchu());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", "onViewCreated: ");


        Anhxa(view);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //nếu item chọn trùng với item đã chọn thì không thay đổi
        if (item.getItemId() == itemselectID) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.bottomNav_Home:
                ReplaceFragment(new fragment_Trangchu());
                break;
            case R.id.bottomNav_User:
                ReplaceFragment(new fragment_User());
                break;
            case R.id.bottomNav_Doter:
                ReplaceFragment(new fragment_Uudai());
                break;
        }

        itemselectID = item.getItemId();
        return true;
    }

    private void ReplaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }
}