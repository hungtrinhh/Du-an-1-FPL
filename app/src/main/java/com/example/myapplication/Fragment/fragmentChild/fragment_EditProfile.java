package com.example.myapplication.Fragment.fragmentChild;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_EditProfile extends Fragment implements View.OnClickListener {
    private AppCompatButton btn_SaveProfile;
    private LinearLayout btn_ChangeAvatar;
    private EditText ed_UpdateFullName;
    private EditText ed_UpdatePhoneNumbers;
    private ImageView btnBackToUser;
    private CircleImageView imageView_editProfile ;
    private final static int REQUEST_CODE = 123; // tạo hằng xác định chỉ số


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
    }

    private void Onclick() {
        btn_ChangeAvatar.setOnClickListener(this::onClick);
        btnBackToUser.setOnClickListener(this::onClick);
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
    private void LayAnh(){
        //cấp quyền từ người dùng
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},999);
            //cho phép sử dụng
        }else{
            Intent intent = new Intent(Intent.ACTION_PICK);//truy cập vào bộ nhớ của máy
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_CODE);
        }
    }

    //lấy ảnh về
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream); // lấy ảnh từ bộ nhớ
                imageView_editProfile .setImageBitmap(bitmap);
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

        }
    }
}