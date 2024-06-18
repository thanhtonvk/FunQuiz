package com.tondz.funquiz.Database;

import androidx.annotation.NonNull;

import com.tondz.funquiz.Model.Choi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChoiDBContext {
    public FirebaseDatabase mDatabase;
    public DatabaseReference mRef;
    public ArrayList<Choi> choiArrayList;

    public ChoiDBContext() {
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("choi");
        choiArrayList = new ArrayList<>();
    }
    public void addChoi(Choi choi) {
        mRef.child(choi.getId()).setValue(choi);
    }
}
