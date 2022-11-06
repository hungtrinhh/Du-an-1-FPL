package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
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
    //  khai báo
    private LinearLayout layoutLogoWhite;
    private EditText ed_Username;
    private EditText ed_Password;
    private Switch sw_RememberAccount;
    private AppCompatButton btn_Login;
    private TextView tv_GoToRegister, tv_FogotPassword;
    //    khai báo biến username & password giá trị rỗng
    private String username = "", password = "";

    //khai báo view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //gọi hàm ánh xạ(truyền view để tìm id trong view đó)
        Anhxa(view);
        //gọi hàm animation (truyền vào các tham số)
        animation(layoutLogoWhite, ed_Username, ed_Password, sw_RememberAccount, btn_Login, tv_GoToRegister, tv_FogotPassword);

        //bắt sự kiện khi click
        btn_Login.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Register_success()).commit();
        });
        tv_GoToRegister.setOnClickListener(view1 -> {
//            addToBackStack = nút Back trên màn hình điện thoại
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Regesiter()).addToBackStack("").commit();

        });
        tv_FogotPassword.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Fogot_Password()).addToBackStack("").commit();

        });
    }

    // khai báo hàm animation
    private void animation(LinearLayout layoutLogoWhite, EditText edEmailLogin, EditText edPasswordLogin, Switch swRememberAccount, AppCompatButton btnLogin, TextView btnGoToRegister, TextView tvFogotPassword) {
        layoutLogoWhite.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.floatin));
        edEmailLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        edPasswordLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        swRememberAccount.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        btnLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        btnGoToRegister.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        tvFogotPassword.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
    }


    //   khai báo constructor
    public fragment_Login() {
    }

    //  Phương thức khởi tạo có tham số username & password
    public fragment_Login(String usr, String pwd) {
        this.username = usr;
        this.password = pwd;
    }

    public static fragment_Login newInstance() {
        fragment_Login fragment = new fragment_Login();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    //    khai báo hàm Anhxa
    private void Anhxa(View view) {
        layoutLogoWhite = view.findViewById(R.id.layout_logoWhite);
        ed_Username = view.findViewById(R.id.ed_Username);
        ed_Password = view.findViewById(R.id.ed_Password);
        sw_RememberAccount = view.findViewById(R.id.sw_RememberAccount);
        btn_Login = view.findViewById(R.id.btn_Login);
        tv_GoToRegister = view.findViewById(R.id.tv_GoToRegister);
        tv_FogotPassword = view.findViewById(R.id.tv_FogotPassword);
        ed_Username.setText(username);
        ed_Password.setText(password);
    }
}