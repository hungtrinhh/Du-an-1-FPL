package com.example.myapplication.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.Adapter.SliderAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.SliderView;


public class fragment_Trangchu extends Fragment {
    //  khai báo
    private SliderView image_Slider;
    private LinearLayout layout_troChoi, layout_thanhToan, layout_soDu;

    //khai báo view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_trangchu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //gọi hàm ánh xạ(truyền view để tìm id trong view đó)
        Anhxa(view);

        // gọi hàm animation (truyền vào các tham số)
        animation(image_Slider, layout_troChoi, layout_thanhToan, layout_soDu);

        // khai báo mảng ảnh và gán giá trị src ảnh
        int[] img = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3};

        // khai báo SliderAdapter và gán giá trị bằng img
        SliderAdapter adapter = new SliderAdapter(img);

        // set lên slideAdapter
        image_Slider.setSliderAdapter(adapter);
    }

    //    khai báo hàm Anhxa
    private void Anhxa(View view) {
        image_Slider = view.findViewById(R.id.image_Slider);
        layout_troChoi = view.findViewById(R.id.layout_troChoi);
        layout_thanhToan = view.findViewById(R.id.layout_thanhToan);
        layout_soDu = view.findViewById(R.id.layout_soDu);


    }

    // khai báo hàm animation
    private void animation(SliderView image_Slider, LinearLayout layout_troChoi, LinearLayout layout_thanhToan, LinearLayout layout_soDu) {
        image_Slider.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
        layout_soDu.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
        layout_thanhToan.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
        layout_troChoi.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //khai báo constructor rỗng
    public fragment_Trangchu() {
    }

    //ko biết
    public static fragment_Trangchu newInstance() {
        fragment_Trangchu fragment = new fragment_Trangchu();

        return fragment;
    }


}