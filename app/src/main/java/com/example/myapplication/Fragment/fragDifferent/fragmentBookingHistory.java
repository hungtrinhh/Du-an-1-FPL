package com.example.myapplication.Fragment.fragDifferent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.github.vipulasri.timelineview.TimelineView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentBookingHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentBookingHistory extends Fragment {
    private ImageView btn_backDetailGame;
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
    }
    private void anhXa(View view)
    {
        btn_backDetailGame = view.findViewById(R.id.btn_backDetailGame);
    }
    private void onClick(){
        btn_backDetailGame.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
}