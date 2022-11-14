package com.example.myapplication.Firebase;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;


import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.User;
import com.example.myapplication.Model.Voucher;

import com.example.myapplication.Service.UpdateGameService;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbDao {
//    public void Test(String string) {
//        Log.d(TAG, "string is: " + string);
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue(string);
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }


    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final String TAG = "Firebase Dao";
    private static List<Game> listGame;
    private static List<User> listUser;
    private static List<Voucher> listVoucher;

    public static List<Game> getListGame() {
        return listGame;
    }

    public static List<Voucher> getListVoucher() {
        return listVoucher;
    }

    public static java.util.List<User> getList() {
        return listUser;
    }

    public static Activity activity;

    public FbDao(Activity context) {
        ReadUser();
        ReadVoucher();
        ReadGame();
        this.activity = context;

    }

    private void ReadGame() {
        Log.d(TAG, "ReadVoucher: ");
        listGame = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Game");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listGame.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Game u = dt.getValue(Game.class);
                    if (u == null) {
                        continue;
                    }

                    listGame.add(u);
                    Log.d(TAG, "ReadGame: " + u.getTrangThai());

                }
                activity.startService(new Intent(activity, UpdateGameService.class));
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }

    private void ReadVoucher() {
        Log.d(TAG, "ReadVoucher: ");
        listVoucher = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Voucher");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listVoucher.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Voucher u = dt.getValue(Voucher.class);
                    if (u == null) {
                        continue;
                    }
                    listVoucher.add(u);
                    Log.d(TAG, "onDataChange: " + u.getLoaiGame());
                }
                activity.startService(new Intent(activity, UpdateGameService.class));
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }


    public void AddUser(User user) {
        DatabaseReference myRef = database.getReference("Users");
        myRef.push().setValue(user);

    }

    public void ReadUser() {
        Log.d(TAG, "ReadUser: ");
        listUser = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    User u = dt.getValue(User.class);
                    if (u == null) {
                        return;
                    }
                    u.setId(dt.getKey());
                    listUser.add(u);
                    Log.d(TAG, "onDataChange: " + u.getName());

                }
            }

            int a = 0;

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });


    }

    ///////////////////////////////////////////////////////////////////////////

}
