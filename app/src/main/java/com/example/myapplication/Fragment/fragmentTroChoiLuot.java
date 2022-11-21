package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Game;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentTroChoiLuot#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentTroChoiLuot extends Fragment implements View.OnClickListener {
    private TextView tv_nameGame_Luot,tv_cost_Luot,tv_detailGame_Luot,tv_count;
    private ImageButton imgButtonadd,imgButtonremove;
    private ImageView backToDSGame;
    private int count=0;
    public static fragmentTroChoiLuot newInstance() {
        fragmentTroChoiLuot fragment = new fragmentTroChoiLuot();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tro_choi_luot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        setThongTin();

        View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        imgButtonadd.setOnClickListener(this::onClick);
        imgButtonremove.setOnClickListener(this::onClick);
        backToDSGame.setOnClickListener(this::onClick);
    }
    private void AnhXa(View view){
        tv_nameGame_Luot = view.findViewById(R.id.tv_nameGame_Luot);
        tv_cost_Luot = view.findViewById(R.id.tv_cost_Luot);
        tv_detailGame_Luot = view.findViewById(R.id.tv_detailGame_Luot);
        imgButtonadd = view.findViewById(R.id.btn_add);
        imgButtonremove = view.findViewById(R.id.btn_remove);
        tv_count = view.findViewById(R.id.tv_count);
        backToDSGame = view.findViewById(R.id.btn_backToDSGame);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                count++;
                tv_count.setText(count+"");
                break;
            case R.id.btn_remove:
                if (count>0){
                    count--;
                }
                tv_count.setText(count+"");
                break;
            case R.id.btn_backToDSGame:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
    private void setThongTin(){
        Bundle bundle = getArguments();
        Game game = (Game) bundle.get("obj_game");
        tv_count.setText(count+"");
        tv_nameGame_Luot.setText(game.getTenGame());
        tv_cost_Luot.setText(game.getGia()+"");
        tv_detailGame_Luot.setText(game.getMoTa());
    }
}