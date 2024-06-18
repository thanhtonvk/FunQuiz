package com.tondz.funquiz.Actitivy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tondz.funquiz.Actitivy.TrangQuanTri.TrangQuanTriActivity;
import com.tondz.funquiz.Database.TaiKhoanDBContext;
import com.tondz.funquiz.R;

public class DangNhapActivity extends AppCompatActivity {

    EditText edt_email, edt_matkhau;
    Button btn_dangnhap, btn_dangky;
    TaiKhoanDBContext taiKhoanDBContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        init();
        taiKhoanDBContext = new TaiKhoanDBContext();
        onClick();
    }

    private void init() {
        edt_email = findViewById(R.id.edt_emaildangnhap);
        edt_matkhau = findViewById(R.id.edt_matkhaudangnhap);
        btn_dangky = findViewById(R.id.btn_dangky);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
    }

    private void onClick() {
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_email.getText().toString().equals("admin") && edt_matkhau.getText().toString().equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), TrangQuanTriActivity.class));
                } else {
                    taiKhoanDBContext.dangNhap(edt_email.getText().toString(), edt_matkhau.getText().toString(), getApplicationContext(), DangNhapActivity.this);
                }
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
            }
        });
    }
}