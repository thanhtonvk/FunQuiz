package com.tondz.funquiz.Database;

import android.widget.ListView;

import androidx.annotation.NonNull;

import com.tondz.funquiz.Model.BaoCao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BaoCaoDBContext {
    public FirebaseDatabase mDatabase;
    public DatabaseReference mRef;
    public ArrayList<BaoCao> baoCaoArrayList;

    public BaoCaoDBContext() {
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("baocao");
        baoCaoArrayList = new ArrayList<>();
    }

    public void addBaoCao(BaoCao baoCao) {
        mRef.child(baoCao.getId()).setValue(baoCao);
    }

}
