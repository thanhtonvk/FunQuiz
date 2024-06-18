package com.tondz.funquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tondz.funquiz.Actitivy.BXHActivity;
import com.tondz.funquiz.Actitivy.DangNhapActivity;
import com.tondz.funquiz.Actitivy.ManChoiActivity;
import com.tondz.funquiz.Actitivy.TrangQuanTri.NhapCauHoiActivity;
import com.tondz.funquiz.Database.CauHoiDBContext;
import com.tondz.funquiz.Database.Common;
import com.tondz.funquiz.Database.TaiKhoanDBContext;
import com.tondz.funquiz.Model.CauHoi;
import com.tondz.funquiz.Model.Choi;
import com.tondz.funquiz.Model.NguoiDung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_bxh, btn_dangnhap, btn_donggop, btn_amthanh, btn_thoat;
    ImageView img_logo;
    Random random;
    TaiKhoanDBContext taiKhoanDBContext;
    CauHoiDBContext cauHoiDBContext;
    MediaPlayer clickSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClick();
        clickSound = MediaPlayer.create(this,R.raw.click_sound);
        cauHoiDBContext = new CauHoiDBContext();
        random = new Random();
        taiKhoanDBContext = new TaiKhoanDBContext();
        taiKhoanDBContext.getListNguoiDung();
        Animation shakeButton = AnimationUtils.loadAnimation(this, R.anim.shake);
        btn_start.startAnimation(shakeButton);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        img_logo.startAnimation(rotation);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if (taiKhoanDBContext.mAuth.getCurrentUser() != null) {
                        for (NguoiDung nguoiDung : taiKhoanDBContext.NguoiDungList
                        ) {
                            if (nguoiDung.getId().equals(taiKhoanDBContext.mAuth.getCurrentUser().getUid())) {
                                Common.NGUOI_DUNG = nguoiDung;
                                btn_dangnhap.setText("Hê lô " + Common.NGUOI_DUNG.getHoten());
                            }
                        }
                    }
                }
            }
        });
        thread.start();
        loadCauHoi();
        Collections.shuffle(cauHoiDBContext.cauHoiList);
    }

    private void onClick() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.CHECK_SOUND==1){
                    clickSound.start();
                }
                if(Common.NGUOI_DUNG!=null){
                    Common.CHOI = new Choi(random.nextInt()+"",Common.NGUOI_DUNG.getHoten(),0);
                }else{
                    Common.CHOI = new Choi(random.nextInt()+"","Guest"+random.nextInt(),0);
                }
                startActivity(new Intent(getApplicationContext(), ManChoiActivity.class));
            }
        });
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.CHECK_SOUND==1){
                    clickSound.start();
                }
                finish();
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.CHECK_SOUND==1){
                    clickSound.start();
                }
                startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
            }
        });
        btn_amthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.CHECK_SOUND==1){
                    btn_amthanh.setBackgroundResource(R.drawable.volume_up_24);
                    Common.CHECK_SOUND = 0;
                }else{
                    btn_amthanh.setBackgroundResource(R.drawable.volume_off_24);
                    Common.CHECK_SOUND = 1;
                }
            }
        });
        btn_bxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.CHECK_SOUND==1){
                    clickSound.start();
                }
                startActivity(new Intent(getApplicationContext(), BXHActivity.class));
            }
        });
        btn_donggop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NhapCauHoiActivity.class));
            }
        });
    }

    private void init() {
        btn_start = findViewById(R.id.btn_start);
        img_logo = findViewById(R.id.img_logo);
        btn_bxh = findViewById(R.id.btn_bxh);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_donggop = findViewById(R.id.btn_donggop);
        btn_amthanh = findViewById(R.id.btn_amthanh);
        btn_thoat = findViewById(R.id.btn_thoat);
    }

    @Override
    public void onBackPressed() {

    }
    private void loadCauHoi(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang nạp đạn, bro chờ tí nhé!!!");
        progressDialog.show();
        cauHoiDBContext.mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                ) {
                    Common.cauHoiArrayList.add(dataSnapshot.getValue(CauHoi.class));

                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Lỗi mạng rồi bạn ei", Toast.LENGTH_SHORT).show();
            }
        });
    }

}