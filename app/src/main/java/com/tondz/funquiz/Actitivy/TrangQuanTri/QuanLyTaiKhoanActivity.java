package com.tondz.funquiz.Actitivy.TrangQuanTri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.tondz.funquiz.Database.TaiKhoanDBContext;
import com.tondz.funquiz.Model.NguoiDung;
import com.tondz.funquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class QuanLyTaiKhoanActivity extends AppCompatActivity {

    AutoCompleteTextView autoComplete_taikhoan;
    ListView lv_taikhoan;
    ArrayAdapter arrayAdapter;
    TaiKhoanDBContext taiKhoanDBContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan);
        init();
        taiKhoanDBContext = new TaiKhoanDBContext();
        loadDSTaiKhoan();
        lv_taikhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);
            }
        });
    }
    private void dialog(int i){
        NguoiDung nguoiDung = taiKhoanDBContext.NguoiDungList.get(i);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tuỳ chọn");
        builder.setNegativeButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                taiKhoanDBContext.deleteNguoiDung(nguoiDung.getId());
            }
        });
        builder.show();
    }
    private void init(){
        autoComplete_taikhoan = findViewById(R.id.autoComplete_taikhoan);
        lv_taikhoan=  findViewById(R.id.lv_taikhoan);
    }
    private void loadDSTaiKhoan(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang tải, vui lòng chờ");
        progressDialog.show();
        taiKhoanDBContext.mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taiKhoanDBContext.NguoiDungList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                     ) {
                    taiKhoanDBContext.NguoiDungList.add(dataSnapshot.getValue(NguoiDung.class));
                }
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,taiKhoanDBContext.NguoiDungList);
                lv_taikhoan.setAdapter(arrayAdapter);
                autoComplete_taikhoan.setAdapter(arrayAdapter);
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