package com.example.myapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class fragment_Login extends Fragment implements View.OnClickListener {
    //  khai báo
    private LinearLayout layoutLogoWhite;
    private EditText ed_Username;
    private EditText ed_Password;
    private SwitchCompat sw_RememberAccount;
    private AppCompatButton btn_Login;
    private TextView tv_GoToRegister, tv_FogotPassword;
    //    khai báo biến username & password giá trị rỗng
    private String username = "", password = "";
    private List<User> list;
    private String TAG = fragment_Login.class.toString();


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
        getAccount();
        //bắt sự kiện khi click
        btn_Login.setOnClickListener(this::onClick);
        tv_GoToRegister.setOnClickListener(this::onClick);
        tv_FogotPassword.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Login:
                String username = ed_Username.getText().toString();
                String password = ed_Password.getText().toString();

//                if (username.equals("") || password.equals("")) {
//                    Snackbar.make(getView(), "Không được để trống tài khoản và mật khẩu", 2000).show();
//                    break;
//                }
//
//                boolean dk = false;
//                for (User u : list
//                ) {
//                    if (username.equals(u.getName()) && password.equals(u.getPassword())) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Main()).commit();
//                        dk = true;
//                    }
//                }
//                if (!dk) {
//                    Snackbar.make(getView(), "Mật khẩu hoặc tài khoản không đúng", 2000).show();
//                }
                break;
            case R.id.tv_GoToRegister:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Regesiter(list)).addToBackStack("").commit();
                break;
            case R.id.tv_FogotPassword:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_Fogot_Password()).addToBackStack("").commit();
                break;
        }
    }


    // khai báo hàm animation
    private void animation(LinearLayout layoutLogoWhite, EditText edEmailLogin, EditText edPasswordLogin, SwitchCompat swRememberAccount, AppCompatButton btnLogin, TextView btnGoToRegister, TextView tvFogotPassword) {
        layoutLogoWhite.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.floatin));
        edEmailLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        edPasswordLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        swRememberAccount.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        btnLogin.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        btnGoToRegister.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
        tvFogotPassword.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
    }

    @Override
    public void onPause() {
        super.onPause();
        saveAccount();
    }

    public void saveAccount() {

        SharedPreferences s = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        if (!sw_RememberAccount.isChecked()) {
            editor.clear();
            editor.commit();
            return;
        }

        editor.putString("name", ed_Username.getText().toString());
        editor.putString("pass", ed_Password.getText().toString());
        editor.putBoolean("save", sw_RememberAccount.isChecked());
        editor.commit();


    }

    private void getAccount() {
        SharedPreferences s = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        ed_Username.setText(s.getString("name", ""));
        ed_Password.setText(s.getString("pass", ""));
        sw_RememberAccount.setChecked(s.getBoolean("save", false));

    }


    //   khai báo constructor
    public fragment_Login() {
        this.list = FbDao.getList();
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