package com.tondz.funquiz.Actitivy.TrangQuanTri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.tondz.funquiz.Database.BaoCaoDBContext;
import com.tondz.funquiz.Database.TaiKhoanDBContext;
import com.tondz.funquiz.Model.BaoCao;
import com.tondz.funquiz.Model.NguoiDung;
import com.tondz.funquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class QuanLyBaoCaoActivity extends AppCompatActivity {
    AutoCompleteTextView autoComplete_baocao;
    ListView lv_baocao;
    ArrayAdapter arrayAdapter;
    BaoCaoDBContext baoCaoDBContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_bao_cao);
        baoCaoDBContext = new BaoCaoDBContext();
        init();
        loadDSBaoCao();
    }
    private void init(){
        autoComplete_baocao = findViewById(R.id.autoComplete_baocao);
        lv_baocao=  findViewById(R.id.lv_baocao);
    }
    private void loadDSBaoCao(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang tải, vui lòng chờ");
        progressDialog.show();
        baoCaoDBContext.mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                baoCaoDBContext.baoCaoArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                ) {
                    baoCaoDBContext.baoCaoArrayList.add(dataSnapshot.getValue(BaoCao.class));
                }
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,baoCaoDBContext.baoCaoArrayList);
                lv_baocao.setAdapter(arrayAdapter);
                autoComplete_baocao.setAdapter(arrayAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Không thể tải danh sách", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}