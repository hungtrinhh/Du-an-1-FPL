package com.example.myapplication.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

//import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class fragment_Regesiter extends Fragment implements View.OnClickListener {
    private ImageView btnBackToLogin;
    private TextView tvConditions, tvError;
    private TextInputLayout edregistername;
    private TextInputLayout edregisterPhonenumber;
    private TextInputLayout edregisterPassword;
    private TextInputLayout edregisterComfirmPassword;

    private AppCompatButton btnRegister;


//    thêm animation fade in với chạy từ phải sang trái cho các phần tử

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_regesiter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        tvConditions.setOnClickListener(this::onClick);
        btnBackToLogin.setOnClickListener(this::onClick);
        btnRegister.setOnClickListener(this::onClick);

    }

    private void Anhxa(View v) {
        btnBackToLogin = (ImageView) v.findViewById(R.id.btnBackToLogin);
//        edregisterUsername = (TextInputLayout) v.findViewById(R.id.edregisterUsername);
//        edregisterPhone = (TextInputLayout) v.findViewById(R.id.edregisterPhone);


//        edregisterConfirmPassword = (TextInputLayout) v.findViewById(R.id.edregisterConfirmPassword);
//        chkcheckLaw = (CheckBox) v.findViewById(R.id.chkcheckLaw);
        edregistername = (TextInputLayout) v.findViewById(R.id.edregistername);
        edregisterPhonenumber = (TextInputLayout) v.findViewById(R.id.edregisterPhonenumber);
        edregisterPassword = (TextInputLayout) v.findViewById(R.id.edregisterPassword);
        edregisterComfirmPassword = (TextInputLayout) v.findViewById(R.id.edregisterComfirmPassword);


        tvConditions = (TextView) v.findViewById(R.id.tvConditions);
        btnRegister = (AppCompatButton) v.findViewById(R.id.btnRegister);

        OntextChange(edregistername);
        OntextChange(edregisterPassword);
        OntextChange(edregisterComfirmPassword);
        OntextChange(edregisterPhonenumber);

    }

    private void OntextChange(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.getEditText();
        switch (editText.getId()) {
            case R.id.edregistername:


                break;

            case R.id.edregisterPhonenumber:


                break;
            case R.id.edregisterPassword:
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().length() < 8) {

                            textInputLayout.setHelperText("Không được bé hơn 8 kí tự*");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#B73E3E")));
                        } else if (s.toString().length() >= 8 && !s.toString().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) {
                            textInputLayout.setHelperText("Mật khẩu yếu*");
                            ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#DD5353"));
                            textInputLayout.setHelperTextColor(colorStateList);
                        } else if (s.toString().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
                            textInputLayout.setHelperText("Mật khẩu mạnh✔");
                            ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#38E54D"));
                            textInputLayout.setHelperTextColor(colorStateList);
                        } else if (s.toString().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) {
                            textInputLayout.setHelperText("Mật khẩu mạnh vừa😢");
                            ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#FF731D"));

                            textInputLayout.setHelperTextColor(colorStateList);
                        }
//                        if (!textInputEditText.getText().toString().equals(edregistercomfirmPassword.getText().toString()) && edregistercomfirmPassword.getText().toString().length() != 0) {
//
//                            edlyregistercomfirmPassword.setHelperText("Mật khẩu xác nhận phải trùng khớp với mật khẩu*'");
//                            edlyregistercomfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#B73E3E")));
//                        } else if (textInputEditText.getText().toString().equals(edregistercomfirmPassword.getText().toString()) && edregistercomfirmPassword.getText().toString().length() != 0) {
//                            edlyregistercomfirmPassword.setHelperText("Hợp lệ'✔");
//                            edlyregistercomfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#38E54D")));
//                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            case R.id.edregisterComfirmPassword:


                break;


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToLogin:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Login()).commit();
                break;
            case R.id.tvConditions:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View viewDialog = inflater.inflate(R.layout.dialog_conditions, null);
                //        builder view
                builder.setView(viewDialog);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
//            case R.id.btnRegister:
//                FbDao.Test("hello");
//                break;
//            test
            case R.id.btnRegister:


                break;

        }
    }


}
