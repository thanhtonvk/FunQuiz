package com.tondz.funquiz.Actitivy.TrangQuanTri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tondz.funquiz.Database.CauHoiDBContext;
import com.tondz.funquiz.Model.CauHoi;
import com.tondz.funquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanLyCauHoiActivity extends AppCompatActivity {

    AutoCompleteTextView auto_TimKiem;
    Button btn_them;
    ListView lv_cauhoi;
    ArrayAdapter arrayAdapter;
    CauHoiDBContext cauHoiDBContext;
    List<CauHoi> tmp;
    public static CauHoi cauHoi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_cau_hoi);
        init();
        cauHoiDBContext = new CauHoiDBContext();
        tmp = cauHoiDBContext.cauHoiList;
        getListCauHoi();
        lv_cauhoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadDialog(i);
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NhapCauHoiActivity.class));
            }
        });
    }

    private void loadDialog(int i) {
        cauHoi = cauHoiDBContext.cauHoiList.get(i);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tuỳ chọn");
        builder.setMessage("Câu hỏi: "+cauHoi.getCauhoi()+"\n"+"Đáp án: "+cauHoi.getDapan()+"\n"+"Giải thích: "+cauHoi.getGiaithich());
        builder.setPositiveButton("Cập nhật thông tin", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(),NhapCauHoiActivity.class));
            }
        });
        builder.setNegativeButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cauHoiDBContext.deleteCauHoi(cauHoi.getId());

            }
        });
        builder.show();

    }

    private void init() {
        auto_TimKiem = findViewById(R.id.autoComplete_cauhoi);
        btn_them = findViewById(R.id.btn_themcauhoi);
        lv_cauhoi = findViewById(R.id.lv_cauhoi);
    }
    public void getListCauHoi(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang tải, vui lòng chờ");
        progressDialog.show();
        cauHoiDBContext.mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cauHoiDBContext.cauHoiList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                ) {
                    cauHoiDBContext.cauHoiList.add(dataSnapshot.getValue(CauHoi.class));
                }
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,cauHoiDBContext.cauHoiList);
                lv_cauhoi.setAdapter(arrayAdapter);
                auto_TimKiem.setAdapter(arrayAdapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Không thể tải dữ liệu", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}