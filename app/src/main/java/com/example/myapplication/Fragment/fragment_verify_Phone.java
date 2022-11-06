package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class fragment_verify_Phone extends Fragment implements View.OnClickListener {
    private ImageView btnBackToRegister;
    private EditText edCode1;
    private EditText edCode2;
    private EditText edCode3;
    private EditText edCode4;
    private EditText edCode5;
    private EditText edCode6;
    private TextView btnSendvetifiAgain;
    private AppCompatButton btnCheckphone;
    private String mPhonenumber;
    private final String TAG = "fragment_verify_Phone";
    private FirebaseAuth mAuth;
    private String Username = "";
    private String Password = "";
    private String verificationId = "";
    private PhoneAuthCredential phoneAuthCredential;

    public fragment_verify_Phone(String mPhonenumber, String Username, String Password, String verificationId) {
        this.mPhonenumber = mPhonenumber;
        mAuth = FirebaseAuth.getInstance();
        this.Username = Username;
        this.Password = Password;
        this.verificationId = verificationId;

    }


    public static fragment_verify_Phone newInstance() {
        fragment_verify_Phone fragment = new fragment_verify_Phone(null, null, null, null);

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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        SetupEditext();


    }

    private String GetCodefromEdittext() {
        String code = "";
        code += edCode1.getText().toString();
        code += edCode2.getText().toString();
        code += edCode3.getText().toString();
        code += edCode4.getText().toString();
        code += edCode5.getText().toString();
        code += edCode6.getText().toString();
        return code;
    }

    private void Anhxa(View v) {
        edCode1 = v.findViewById(R.id.edCode1);
        edCode2 = v.findViewById(R.id.edCode2);
        edCode3 = v.findViewById(R.id.edCode3);
        edCode4 = v.findViewById(R.id.edCode4);
        edCode5 = v.findViewById(R.id.edCode5);
        edCode6 = v.findViewById(R.id.edCode6);

        btnBackToRegister = v.findViewById(R.id.btnBackToRegister);
        btnSendvetifiAgain = v.findViewById(R.id.btnSendvetifiAgain);
        btnCheckphone = v.findViewById(R.id.btnCheckphone);

        btnCheckphone.setOnClickListener(this::onClick);
        btnSendvetifiAgain.setOnClickListener(this::onClick);
        btnBackToRegister.setOnClickListener(this::onClick);

    }

    private void SendverifyCode(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                Log.d(TAG, "onVerificationCompleted:" + credential);
                                signInWithPhoneAuthCredential(credential);
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
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Login(Username, Password)).commit();
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void SetupEditext() {
        edCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode3.requestFocus();
                } else {
                    edCode1.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode4.requestFocus();
                } else {
                    edCode2.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode5.requestFocus();
                } else {
                    edCode3.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    edCode6.requestFocus();
                } else {
                    edCode4.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCode6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {

                } else {
                    edCode5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToRegister:
                getActivity().getSupportFragmentManager().popBackStack();


                break;
            case R.id.btnSendvetifiAgain:


                break;
            case R.id.btnCheckphone:
                phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, GetCodefromEdittext());
                signInWithPhoneAuthCredential(phoneAuthCredential);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, new fragment_Login()).commit();

                break;

        }

    }
}