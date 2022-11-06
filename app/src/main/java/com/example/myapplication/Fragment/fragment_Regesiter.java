package com.example.myapplication.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

//import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class fragment_Regesiter extends Fragment implements View.OnClickListener {
    private ImageView btnBackToLogin;
    private TextView tvConditions;
    private TextInputLayout edregistername;
    private TextInputLayout edregisterPhonenumber;
    private TextInputLayout edregisterPassword;
    private TextInputLayout edregisterComfirmPassword;
    private CheckBox chkCheckLaw;
    private LinearLayout layout_Conditions;

    private AppCompatButton btnRegister;
    private final String TAG = "fragment_Regesiter";

    private FirebaseAuth mAuth;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_regesiter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        mAuth = FirebaseAuth.getInstance();
        Animation(edregistername,edregisterPhonenumber,edregisterPassword,edregisterComfirmPassword,layout_Conditions,btnRegister);
    }
    private void Animation(TextInputLayout edregistername,TextInputLayout edregisterPhonenumber,TextInputLayout edregisterPassword,TextInputLayout edregisterComfirmPassword,LinearLayout layout_Conditions,AppCompatButton btnRegister){
        edregistername.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.lefttoright));
        edregisterPhonenumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.righttoleft));
        edregisterPassword.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.lefttoright));
        edregisterComfirmPassword.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.righttoleft));
        edregistername.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.lefttoright));
        layout_Conditions.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.floatin));
        btnRegister.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));

    }
    private void Anhxa(View view) {
        btnBackToLogin = view.findViewById(R.id.btnBackToLogin);
        layout_Conditions = view.findViewById(R.id.layout_Conditions);
        edregistername = view.findViewById(R.id.edregistername);
        edregisterPhonenumber = view.findViewById(R.id.edregisterPhonenumber);
        edregisterPassword = view.findViewById(R.id.edregisterPassword);
        edregisterComfirmPassword = view.findViewById(R.id.edregisterComfirmPassword);
        chkCheckLaw = view.findViewById(R.id.chkCheckLaw);
        tvConditions = view.findViewById(R.id.tvConditions);
        btnRegister = view.findViewById(R.id.btnRegister);

        OntextChange(edregistername);
        OntextChange(edregisterPassword);
        OntextChange(edregisterComfirmPassword);
        OntextChange(edregisterPhonenumber);

        tvConditions.setOnClickListener(this::onClick);
        btnBackToLogin.setOnClickListener(this::onClick);
        btnRegister.setOnClickListener(this::onClick);
        chkCheckLaw.setOnClickListener(this::onClick);

    }

    private void OntextChange(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.getEditText();
        switch (textInputLayout.getId()) {
            case R.id.edregistername:
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) {
                            textInputLayout.setHelperText("Bắt buộc*");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        } else {
                            textInputLayout.setHelperText("✔");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green_700)));
                        }
                        btnRegister.setEnabled(CheckBtn());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                btnRegister.setEnabled(CheckBtn());
                break;
            case R.id.edregisterPhonenumber:
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) {

                            textInputLayout.setHelperText("Bắt buộc*");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        } else {
                            textInputLayout.setHelperText("✔");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green_700)));
                        }
                        btnRegister.setEnabled(CheckBtn());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                btnRegister.setEnabled(CheckBtn());
                break;
            case R.id.edregisterPassword:
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().length() == 0) {
                            textInputLayout.setHelperText("Bắt buộc");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        } else if (s.length() < 6) {
                            textInputLayout.setHelperText("Mật khẩu không được bé hơn 6 kí tự");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        } else {
                            textInputLayout.setHelperText("✔");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green_700)));
                        }
                        if (edregisterComfirmPassword.getEditText().getText().toString().equals(s.toString())) {
                            edregisterComfirmPassword.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green_700)));
                            edregisterComfirmPassword.setHelperText("✔");
                        } else {
                            edregisterComfirmPassword.setHelperText("Mật khẩu xác nhận phải trùng với mật khẩu*");
                            edregisterComfirmPassword.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        }
                        btnRegister.setEnabled(CheckBtn());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                btnRegister.setEnabled(CheckBtn());
                break;
            case R.id.edregisterComfirmPassword:
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) {
                            textInputLayout.setHelperText("Bắt buộc*");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        } else if (s.toString().equals(edregisterPassword.getEditText().getText().toString())) {
                            textInputLayout.setHelperText("✔");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green_700)));
                        } else {
                            textInputLayout.setHelperText("Mật khẩu xác nhận phải trùng với mật khẩu*");
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        }
                        btnRegister.setEnabled(CheckBtn());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
        }
    }


    private boolean CheckBtn() {
        Log.d(TAG, "CheckBtn: ");
        if (edregisterPhonenumber.getHelperTextCurrentTextColor() == getResources().getColor(R.color.red)) {
            return false;
        }
        if (edregisterComfirmPassword.getHelperTextCurrentTextColor() == getResources().getColor(R.color.red)) {
            return false;
        }
        if (edregisterPassword.getHelperTextCurrentTextColor() == getResources().getColor(R.color.red)) {
            return false;
        }
        if (edregistername.getHelperTextCurrentTextColor() == getResources().getColor(R.color.red)) {
            return false;
        }

        return chkCheckLaw.isChecked();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToLogin:
                getActivity().getSupportFragmentManager().popBackStack();
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
            case R.id.btnRegister:

                break;
            case R.id.chkCheckLaw:
                btnRegister.setEnabled(CheckBtn());
                SendverifyCode(edregisterPhonenumber.getEditText().getText().toString());
                break;
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, String Username, String Password) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.containerMain, new fragment_Login(Username, Password)).commit();

                } else {

                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                    }
                }
            }
        });

    }

    private void SendverifyCode(String phoneNumber) {
        String Username = edregistername.getEditText().getText().toString();
        String Password = edregisterPassword.getEditText().getText().toString();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                Log.d(TAG, "onVerificationCompleted:" + credential);

                                signInWithPhoneAuthCredential(credential, Username, Password);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                Log.w(TAG, "onVerificationFailed", e);
                                Toast.makeText(getActivity(), "Gửi mã xác minh thất bại,Hãy liên hệ với quản trị viên để được giúp đỡ", Toast.LENGTH_SHORT).show();
                                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                                } else if (e instanceof FirebaseTooManyRequestsException) {

                                }


                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                super.onCodeSent(verificationId, token);
                                Log.d(TAG, "onCodeSent:" + verificationId);
                                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.containerMain, new fragment_verify_Phone(phoneNumber, Username, Password, verificationId)).commit();
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


}
