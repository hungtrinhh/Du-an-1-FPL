package com.example.myapplication.Firebase;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FbDao {


    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "Firebase Dao";

    public static void Test(String string) {
        Log.d(TAG, "string is: " + string);
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(string);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public static void AddUser(User user) {
        DatabaseReference myRef = database.getReference("Users");
        myRef.push().setValue(user);

    }

    public static List<User> ReadUser() {
        List<User> list = new ArrayList<>();
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
                    list.add(u);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
        return list;
    }

    ///////////////////////////////////////////////////////////////////////////

}
