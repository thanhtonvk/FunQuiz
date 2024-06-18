package com.tondz.funquiz.Actitivy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tondz.funquiz.Database.ChoiDBContext;
import com.tondz.funquiz.Model.Choi;
import com.tondz.funquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Comparator;

public class BXHActivity extends AppCompatActivity {

    ListView lv_bxh;
    ChoiDBContext choiDBContext;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bxhactivity);
        init();
        choiDBContext = new ChoiDBContext();
        loadDSChoi();
    }

    private void init() {
        lv_bxh = findViewById(R.id.lv_bxh);
    }

    private void loadDSChoi() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Đang tải dữ liệu");
        dialog.show();
        choiDBContext.mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    choiDBContext.choiArrayList.add(dataSnapshot.getValue(Choi.class));
                }
                Collections.sort(choiDBContext.choiArrayList, new Comparator<Choi>() {
                    @Override
                    public int compare(Choi choi, Choi t1) {
                        return t1.getDiem() > choi.getDiem() ? -1 : 1;
                    }
                });
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,choiDBContext.choiArrayList);
                lv_bxh.setAdapter(arrayAdapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}