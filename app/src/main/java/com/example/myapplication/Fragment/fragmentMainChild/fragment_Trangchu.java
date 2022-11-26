package com.example.myapplication.Fragment.fragmentMainChild;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Dialog.DialogLoading;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Fragment.fragListgameAndVoudcher.Fragment_ListDanhSachTroChoi;
import com.example.myapplication.Fragment.fragDifferent.fragment_QRcode;
import com.example.myapplication.Model.User;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.util.List;


public class fragment_Trangchu extends Fragment implements View.OnClickListener {
    private static final int REQUETCODE = 100;
    //  khai báo
    private SliderView image_Slider;
    private LinearLayout layout_troChoi, layout_thanhToan, layout_soDu;
    private Toolbar toolbar;
    private ImageView avaterUserHomeFrag;
    private TextView fragHomeTvUsername;
    private TextView fragHomeTvSodu;
    private ImageView hideshowSoduHomefrag;
    private LinearLayout goTofragQr;


    private static final String TAG = "FRAGMENT_TRANG_CHU";
    public static boolean gochild = false;
    private boolean show = true;

    //khai báo view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_trangchu, container, false);
    }

    public fragment_Trangchu() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //gọi hàm ánh xạ(truyền view để tìm id trong view đó)
        if (gochild) {
            replaceFragment(new Fragment_ListDanhSachTroChoi());
            gochild = !gochild;
        }
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
        DialogLoading.dialogLoading.dismiss();
        onClickLayout();

    }

    private void SetDataForView() {
        User u = FbDao.UserLogin;
        String pattern = "###,###,###,###,###,### Poin";
        DecimalFormat df = new DecimalFormat(pattern);
        fragHomeTvSodu.setText(df.format(u.getSodu()));
        fragHomeTvUsername.setText(u.getName());
        if (FbDao.UserLogin.getAvatar() != null) {
            avaterUserHomeFrag.setImageBitmap(FbDao.UserLogin.getAvatar());

        }
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
        goTofragQr = view.findViewById(R.id.goTofragQr);

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
                    fragHomeTvSodu.setText("******** Poin");
                    hideshowSoduHomefrag.setImageResource(R.drawable.ic_baseline_remove_red_eye_24px);
                } else {
                    User u = FbDao.UserLogin;
                    String pattern = "###,###,###,###,###,### Poin";
                    DecimalFormat df = new DecimalFormat(pattern);
                    fragHomeTvSodu.setText(df.format(u.getSodu()));

                    hideshowSoduHomefrag.setImageResource(R.drawable.ic_baseline_visibility_off_24px);
                }
                show = !show;
                break;
            case R.id.goTofragQr:
                Toast.makeText(getContext(),"ok",Toast.LENGTH_LONG).show();
                phanQuyen();


                break;

        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment).addToBackStack(fragment_Trangchu.TAG).commit();
    }

    public void onClickLayout() {
        layout_troChoi.setOnClickListener(this::onClick);
        goTofragQr.setOnClickListener(this::onClick);
    }

    private void phanQuyen() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            ) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new fragment_QRcode()).commit();


            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUETCODE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        if (requestCode == REQUETCODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: thanh cong");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new fragment_QRcode()).commit();



            } else {
                Log.d(TAG, "onRequestPermissionsResult: that bai");
            }

        }
    }

}