package com.example.myapplication.Fragment.fragmentUserChild;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class fragment_EditProfile extends Fragment implements View.OnClickListener {
    private AppCompatButton btn_SaveProfile;
    private LinearLayout btn_ChangeAvatar;
    private EditText ed_UpdateFullName;
    private EditText ed_UpdatePhoneNumbers;
    private ImageView btnBackToUser;
    private ImageView imageView_editProfile;
    private final static int REQUEST_CODE = 123; // tạo hằng xác định chỉ số
    private final String TAG = "fragment_EditProfile";

    public fragment_EditProfile() {

    }


    public static fragment_EditProfile newInstance(String param1, String param2) {
        fragment_EditProfile fragment = new fragment_EditProfile();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        Onclick();
        btn_SaveProfile.setEnabled(false);

        setFocuschangeEdittext();
        setDataForView();
    }


    private void setFocuschangeEdittext() {
        ed_UpdateFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!s.toString().equals(FbDao.UserLogin.getName()) || !ed_UpdatePhoneNumbers.getText().toString().equals(FbDao.UserLogin.getPhonenumber())) {
                    if (!((s.length() == 0) || (ed_UpdatePhoneNumbers.getText().toString().length() == 0)) || imgdif) {
                        btn_SaveProfile.setEnabled(true);
                    } else {
                        btn_SaveProfile.setEnabled(false);

                    }


                } else {
                    btn_SaveProfile.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ed_UpdatePhoneNumbers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!s.toString().equals(FbDao.UserLogin.getPhonenumber()) || !ed_UpdateFullName.getText().toString().equals(FbDao.UserLogin.getName())) {
                    if (!((s.length() == 0) || (ed_UpdateFullName.getText().toString().length() == 0)) || imgdif) {
                        btn_SaveProfile.setEnabled(true);
                    } else {
                        btn_SaveProfile.setEnabled(false);

                    }
                } else {
                    btn_SaveProfile.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void Onclick() {
        btn_ChangeAvatar.setOnClickListener(this::onClick);
        btnBackToUser.setOnClickListener(this::onClick);
        btn_SaveProfile.setOnClickListener(this::onClick);
    }

    private void setDataForView() {
        imageView_editProfile.setImageBitmap(FbDao.UserLogin.getAvatar());
        String numberPhone = FbDao.UserLogin.getPhonenumber();
        ed_UpdatePhoneNumbers.setText(numberPhone);
        ed_UpdateFullName.setText(FbDao.UserLogin.getName());
    }

    private void Anhxa(View v) {
        imageView_editProfile = v.findViewById(R.id.img_editProfile);
        btnBackToUser = v.findViewById(R.id.btnBackToUser);

        btn_SaveProfile = v.findViewById(R.id.btn_SaveProfile);
        btn_ChangeAvatar = v.findViewById(R.id.btn_ChangeAvatar);
        ed_UpdateFullName = v.findViewById(R.id.ed_UpdateFullName);
        ed_UpdatePhoneNumbers = v.findViewById(R.id.ed_UpdatePhoneNumbers);
    }

    //hàm lấy ảnh từ thiết bị
    private void LayAnh() {
        //cấp quyền từ người dùng
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
            //cho phép sử dụng
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);//truy cập vào bộ nhớ của máy
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    Bitmap imgChose = null;
    boolean imgdif = false;

    //lấy ảnh về
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                imgChose = BitmapFactory.decodeStream(inputStream); // lấy ảnh từ bộ nhớ
                imageView_editProfile.setImageBitmap(imgChose);
                if (!imgChose.sameAs(FbDao.UserLogin.getAvatar())) {
                    imgdif = true;
                } else {
                    imgdif = false;


                }
                btn_SaveProfile.setEnabled(imgdif);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToUser:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_ChangeAvatar:
                LayAnh();
                break;
            case R.id.btn_SaveProfile:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!FbDao.UpdatedUser) {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                }).start();
                FbDao dao = new FbDao();
                Log.d("Firebase Dao", FbDao.UserLogin.getId() + "hehehe");
                dao.UpLoadavatar(imageView_editProfile);
                User u = new User();
                u.setName(ed_UpdateFullName.getText().toString());
                u.setPassword(ed_UpdatePhoneNumbers.getText().toString());
                u.setId(FbDao.UserLogin.getId());
                dao.UpdateUser(u);


                break;

        }
    }
}