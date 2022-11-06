package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.R;


public class fragment_Login extends Fragment {

    private LinearLayout layoutLogoWhite;
    private EditText edEmailLogin;
    private EditText edPasswordLogin;
    private Switch swRememberAccount;
    private AppCompatButton btnLogin;
    private TextView btnGotoregister, tvFogotPassword;
    String username = "", password = "";

    public fragment_Login() {

    }

    public fragment_Login(String userName, String passWord) {
        this.username = userName;
        this.password = passWord;
    }

    public static fragment_Login newInstance() {
        fragment_Login fragment = new fragment_Login();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
//        animation
        layoutLogoWhite.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.floatin));
        edEmailLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        edPasswordLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        swRememberAccount.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        btnLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        btnGotoregister.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        tvFogotPassword.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));

//        onClick
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Register_success()).commit();
            }
        });
        btnGotoregister.setOnClickListener(view1 -> {
//            addToBackStack = nút Back trên màn hình điện thoại
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Regesiter()).addToBackStack("").commit();

        });
        tvFogotPassword.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Fogot_Password()).addToBackStack("").commit();

        });
    }

    private void Anhxa(View view) {
        layoutLogoWhite = view.findViewById(R.id.layout_logoWhite);
        edEmailLogin = view.findViewById(R.id.edEmailLogin);
        edPasswordLogin = view.findViewById(R.id.edPasswordLogin);
        swRememberAccount = view.findViewById(R.id.swRememberAccount);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnGotoregister = view.findViewById(R.id.btnGotoregister);
        tvFogotPassword = view.findViewById(R.id.tvFogotPassword);
        edEmailLogin.setText(username);
        edPasswordLogin.setText(password);
    }
}