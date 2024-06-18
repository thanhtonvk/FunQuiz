package com.tondz.funquiz.Database;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tondz.funquiz.Model.CauHoi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Handler;

public class CauHoiDBContext {
    public FirebaseDatabase mDatabase;
    public DatabaseReference mRef;
    public List<CauHoi>cauHoiList;
    public ArrayAdapter arrayAdapter;
    public ListView listView;
    public Context context;
    public CauHoiDBContext(){
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("cauhoi");
        cauHoiList = new ArrayList<>();
    }
    public void addCauHoi(CauHoi cauHoi){
        mRef.child(cauHoi.getId()).setValue(cauHoi);
    }
    public void updateCauHoi(String id, CauHoi cauHoi){
        mRef.child(id).setValue(cauHoi);
    }
    public void deleteCauHoi(String id){
        mRef.child(id).removeValue();
    }


}
