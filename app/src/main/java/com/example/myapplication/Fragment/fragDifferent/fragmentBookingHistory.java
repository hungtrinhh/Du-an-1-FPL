package com.example.myapplication.Fragment.fragDifferent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.BookingHistoryAdapter;
import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.HoaDonHenGio;
import com.example.myapplication.R;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentBookingHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentBookingHistory extends Fragment {
    private ImageView btn_backDetailGame;
    private RecyclerView recyclerView;
    private Game game;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmentBookingHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentBookingHistory newInstance(String param1, String param2) {
        fragmentBookingHistory fragment = new fragmentBookingHistory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        onClick();
        showList();
    }
    private void anhXa(View view)
    {
        btn_backDetailGame = view.findViewById(R.id.btn_backDetailGame);
        recyclerView = view.findViewById(R.id.list_game_da_dat);
        Bundle bundle = getArguments();
        game = (Game) bundle.get("obj_game");
    }

    private void showList(){
        List<HoaDonHenGio> list = getList(FbDao.getListHoaDonHenGio());
        BookingHistoryAdapter adapter = new BookingHistoryAdapter(getActivity());
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private List<HoaDonHenGio> getList(List<HoaDonHenGio> listTemp){
        List<HoaDonHenGio> list = new ArrayList<>();
        for(HoaDonHenGio item : listTemp){
            if(item.getGameid().equals(String.valueOf(game.getId())) && item.isSuccess()==false){
                list.add(item);
            }
        }

        return list;
    }

    private void onClick(){
        btn_backDetailGame.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
}