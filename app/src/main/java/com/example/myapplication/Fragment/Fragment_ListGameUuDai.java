package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.Adapter.GameUuDaiVerticalAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListGameUuDai#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListGameUuDai extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView_danhmuc_ListGame;
    private ImageView btn_BackToUuDai,btn_Search;
    private GameUuDaiVerticalAdapter gameUuDaiVerticalAdapter;
    private List<Game> listGame;
    private androidx.appcompat.widget.SearchView searchView_listGameUuDai;
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
        return inflater.inflate(R.layout.fragment_list_game_danh_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        ShowListGame();

        searchView_listGameUuDai.setVisibility(View.GONE);
        // bắt sự kiện khi click
        btn_BackToUuDai.setOnClickListener(this::onClick);
        btn_Search.setOnClickListener(this::onClick);
    }
    private void AnhXa(View view){
        recyclerView_danhmuc_ListGame = view.findViewById(R.id.recyclerview_danhmuc_ListGame);
        btn_BackToUuDai = view.findViewById(R.id.btn_BackToUuDai_fragDanhmuc);
        searchView_listGameUuDai = view.findViewById(R.id.searchView_listGameUuDai);
        btn_Search = view.findViewById(R.id.btn_search_fragDanhmuc);
    }
    private void ShowListGame(){
        listGame = FbDao.getListGame();
        gameUuDaiVerticalAdapter = new GameUuDaiVerticalAdapter(getActivity());
        gameUuDaiVerticalAdapter.setListDanhSachGame(listGame);
        recyclerView_danhmuc_ListGame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_danhmuc_ListGame.setAdapter(gameUuDaiVerticalAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_BackToUuDai_fragDanhmuc:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment_Uudai.newInstance()).commit();
                break;
            case R.id.btn_search_fragDanhmuc:
                if (searchView_listGameUuDai.getVisibility()==View.GONE){
                    searchView_listGameUuDai.setVisibility(View.VISIBLE);
                    searchView_listGameUuDai.onActionViewExpanded();
                }else {
                    searchView_listGameUuDai.setVisibility(View.GONE);
                }
                break;
        }
    }
}