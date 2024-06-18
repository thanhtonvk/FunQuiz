package com.tondz.funquiz.Actitivy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tondz.funquiz.Database.TaiKhoanDBContext;
import com.tondz.funquiz.Model.NguoiDung;
import com.tondz.funquiz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DangKyActivity extends AppCompatActivity {

    EditText edt_email,edt_ten,edt_matkhau;
    Button btn_dangky;
    TaiKhoanDBContext taiKhoanDBContext;
    //Đăng ký tài khoản với firebaseAuth
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        init();
        taiKhoanDBContext = new TaiKhoanDBContext();
        onClick();
    }
    private void onClick(){
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDung nguoiDung = new NguoiDung("",edt_email.getText().toString(),edt_ten.getText().toString(),edt_matkhau.getText().toString(),false);
                taiKhoanDBContext.dangKyTaiKhoan(nguoiDung,DangKyActivity.this,DangKyActivity.this);
            }
        });
    }
    private void init(){
        edt_email = findViewById(R.id.edt_emaildangky);
        edt_ten = findViewById(R.id.edt_tennguoidung);
        edt_matkhau = findViewById(R.id.edt_matkhaudangky);
        btn_dangky = findViewById(R.id.btn_dangkytaikhoan);
    }
}