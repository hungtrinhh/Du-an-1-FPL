package com.example.myapplication.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.User;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.example.myapplication.Adapter.SliderAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.util.List;


public class fragment_Trangchu extends Fragment implements View.OnClickListener {
    //  khai báo
    private SliderView image_Slider;
    private LinearLayout layout_troChoi, layout_thanhToan, layout_soDu;
    private Toolbar toolbar;
    private ImageView avaterUserHomeFrag;
    private TextView fragHomeTvUsername;
    private TextView fragHomeTvSodu;
    private ImageView hideshowSoduHomefrag;

    private static final String TAG = "FRAGMENT_TRANG_CHU";


    private boolean show = true;

    //khai báo view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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

        List<User> list = FbDao.getList();
        // khai báo SliderAdapter và gán giá trị bằng img
        SliderAdapter adapter = new SliderAdapter(img);

        List<Voucher> listVoucher = FbDao.getListVoucher();
        // set lên slideAdapter
        image_Slider.setSliderAdapter(adapter);
        //    toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        SetDataForView();
        onClickLayout();
    }

    private void SetDataForView() {
        User u = FbDao.UserLogin;
        String pattern = "###,###,###,###,###,### Poin";
        DecimalFormat df = new DecimalFormat(pattern);
        fragHomeTvSodu.setText(df.format(u.getSodu()).toString());
        fragHomeTvUsername.setText(u.getName());

    }

    // toolbar
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_search:
                Toast.makeText(getActivity(), "Toát", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    khai báo hàm Anhxa
    private void Anhxa(View view) {
        image_Slider = view.findViewById(R.id.image_Slider);
        layout_troChoi = view.findViewById(R.id.layout_troChoi);
        layout_thanhToan = view.findViewById(R.id.layout_thanhToan);
        layout_soDu = view.findViewById(R.id.layout_soDu);
        toolbar = view.findViewById(R.id.toolbar);

        avaterUserHomeFrag = view.findViewById(R.id.avaterUserHomeFrag);
        fragHomeTvUsername = view.findViewById(R.id.fragHome_tvUsername);
        fragHomeTvSodu = view.findViewById(R.id.fragHome_tvSodu);
        hideshowSoduHomefrag = view.findViewById(R.id.hideshowSoduHomefrag);
        hideshowSoduHomefrag.setOnClickListener(this::onClick);


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
        if (!FbDao.Login) {
            FbDao.Login(FbDao.UserLogin.getId());
            FbDao.Login = true;
        }
    }

    //khai báo constructor rỗng
    public fragment_Trangchu() {
    }

    //ko biết
    public static fragment_Trangchu newInstance() {
        fragment_Trangchu fragment = new fragment_Trangchu();

        return fragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_troChoi:
                replaceFragment(new Fragment_ListDanhSachTroChoi());
                break;
            case R.id.hideshowSoduHomefrag:
                if (show) {
//                    String s = FbDao.UserLogin.getSodu() + "";
//                    String s2 = "";
//                    for (int i = 0; i < s.length(); i++) {
//                        s2 += "*";
//                    }
                    fragHomeTvSodu.setText("******** Poin");
                    hideshowSoduHomefrag.setImageResource(R.drawable.ic_baseline_remove_red_eye_24px);
                } else {
                    SetDataForView();
                    hideshowSoduHomefrag.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                show = !show;
                break;
        }
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment).addToBackStack(fragment_Trangchu.TAG).commit();
    }

    public void onClickLayout() {
        layout_troChoi.setOnClickListener(this::onClick);
    }
}