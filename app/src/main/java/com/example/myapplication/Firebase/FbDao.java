package com.example.myapplication.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FbDao {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();


    public static void Test(String string) {
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(string);

    }

}
