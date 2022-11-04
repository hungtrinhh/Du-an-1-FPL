package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_Trangchu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_Trangchu extends Fragment {
    private SliderView imageSlider;
    private LinearLayout layout_troChoi, layout_thanhToan, layout_soDu;

    public fragment_Trangchu() {

    }


    public static fragment_Trangchu newInstance() {
        fragment_Trangchu fragment = new fragment_Trangchu();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_trangchu, container, false);
    }

    private void Anhxa(View view) {
        imageSlider = (SliderView) view.findViewById(R.id.image_slider);
        layout_troChoi = (LinearLayout) view.findViewById(R.id.layout_troChoi);
        layout_thanhToan = (LinearLayout) view.findViewById(R.id.layout_thanhToan);
        layout_soDu = (LinearLayout) view.findViewById(R.id.layout_soDu);
        int[] img = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3};
        SliderAdapter adapter = new SliderAdapter(img);
        imageSlider.setSliderAdapter(adapter);


//        set animation cac phan tu trong layout
        imageSlider.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
        layout_soDu.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
        layout_thanhToan.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));
        layout_troChoi.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.conten_appear));


//        LinearLayout contentContainer = (LinearLayout)view.findViewById(R.id.content_containerTrangchu);
//        contentContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.conten_appear));


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);


    }
}