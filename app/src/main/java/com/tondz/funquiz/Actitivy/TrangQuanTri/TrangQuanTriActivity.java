package com.tondz.funquiz.Actitivy.TrangQuanTri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tondz.funquiz.R;

public class TrangQuanTriActivity extends AppCompatActivity {

    Button btn_taikhoan,btn_cauhoi,btn_baocao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_quan_tri);
        init();
        btn_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QuanLyTaiKhoanActivity.class));
            }
        });
        btn_cauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QuanLyCauHoiActivity.class));
            }
        });
        btn_baocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QuanLyBaoCaoActivity.class));
            }
        });
    }
    private void init(){
        btn_taikhoan = findViewById(R.id.btn_quanlytaikhoan);
        btn_cauhoi= findViewById(R.id.btn_quanlycauhoi);
        btn_baocao = findViewById(R.id.btn_quanlybaocao);
    }
}