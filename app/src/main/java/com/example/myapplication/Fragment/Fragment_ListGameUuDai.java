package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapter.GameVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListGameUuDai#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListGameUuDai extends Fragment {
    private RecyclerView recyclerView_danhmuc_ListGame;
    private ImageView btn_BackToUuDai;
    private GameVerticalAdapter gameVerticalAdapter;
    private List<Game> listGame;
    private Toolbar toolbar;
    public Fragment_ListGameUuDai() {
        // Required empty public constructor
    }

    public static Fragment_ListGameUuDai newInstance() {
        Fragment_ListGameUuDai fragment = new Fragment_ListGameUuDai();
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
        return inflater.inflate(R.layout.fragment__list_game_uu_dai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        ShowListGame();
        //    toolbar
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        btn_BackToUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AnhXa(View view){
        toolbar = view.findViewById(R.id.toolbar_DanhMuc);
        recyclerView_danhmuc_ListGame = view.findViewById(R.id.recyclerview_danhmuc_ListGame);
        btn_BackToUuDai = view.findViewById(R.id.btn_BackToUuDai);
    }
    private void ShowListGame(){
        listGame = new ArrayList<>();
        listGame.add(new Game(1,1,"khong","khong","khong"));
//        listGame = FbDao.getListGame();
        gameVerticalAdapter = new GameVerticalAdapter(getActivity());
        gameVerticalAdapter.setListDanhSachGame(listGame);
        recyclerView_danhmuc_ListGame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_danhmuc_ListGame.setAdapter(gameVerticalAdapter);
    }
}