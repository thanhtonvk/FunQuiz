package com.tondz.funquiz.Actitivy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tondz.funquiz.Actitivy.TrangQuanTri.BaoCaoActivity;
import com.tondz.funquiz.Database.BaoCaoDBContext;
import com.tondz.funquiz.Database.CauHoiDBContext;
import com.tondz.funquiz.Database.Common;
import com.tondz.funquiz.MainActivity;
import com.tondz.funquiz.Model.BaoCao;
import com.tondz.funquiz.Model.CauHoi;
import com.tondz.funquiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManChoiActivity extends AppCompatActivity {

    List<Animation> dsHieuUng;
    TextView tv_cauhoi, tv_solanxoan;
    ImageView img_trollface;
    Button btn_da1, btn_da2, btn_da3, btn_da4, btn_trove, btn_baocao;
    Random random;
    CauHoiDBContext cauHoiDBContext;
    MediaPlayer wrongSound;
    MediaPlayer correctSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_choi);
        dsHieuUng = new ArrayList<>();
        random = new Random();
        cauHoiDBContext = new CauHoiDBContext();

        init();
        themHieuUng();
        wrongSound = MediaPlayer.create(this,R.raw.wrong_sound);
        correctSound = MediaPlayer.create(this,R.raw.correct_sound);
        img_trollface.setImageResource(Common.dsHinhAnh().get(random.nextInt(12)));
        tv_cauhoi.setAnimation(dsHieuUng.get(random.nextInt(3)));
        img_trollface.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
        Common.CAU_HOI = Common.cauHoiArrayList.get(Common.vitri);
        tv_cauhoi.setText(Common.CAU_HOI.getCauhoi());
        btn_da1.setText(Common.CAU_HOI.getA());
        btn_da2.setText(Common.CAU_HOI.getB());
        btn_da3.setText(Common.CAU_HOI.getC());
        btn_da4.setText(Common.CAU_HOI.getD());
        onClick();
        Common.vitri += 1;
        if (Common.vitri > Common.cauHoiArrayList.size() - 1) {
            Common.vitri = 0;
        }
    }

    private void onClick() {
        btn_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        btn_baocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_da1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDapAn(btn_da1.getText().toString(), Common.CAU_HOI)) {
                    if(Common.CHECK_SOUND==1){
                        correctSound.start();
                    }
                    startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                    Common.CHOI.setDiem(Common.CHOI.getDiem()+1);
                } else {
                    if(Common.CHECK_SOUND==1){
                        wrongSound.start();
                    }
                    Toast.makeText(getApplicationContext(), Common.loiKhia[random.nextInt(6)], Toast.LENGTH_SHORT).show();
                    Common.SO_LAN_CHOI -= 1;
                    tv_solanxoan.setText("Số lần xoắn còn: " + Common.SO_LAN_CHOI);
                    if (Common.SO_LAN_CHOI == 0) {
                        startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                    }
                }
            }
        });
        btn_da2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDapAn(btn_da2.getText().toString(), Common.CAU_HOI)) {
                    if(Common.CHECK_SOUND==1){
                        correctSound.start();
                    }
                    Common.CHOI.setDiem(Common.CHOI.getDiem()+1);
                    startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));

                } else {
                    if(Common.CHECK_SOUND==1){
                        wrongSound.start();
                    }
                    Toast.makeText(getApplicationContext(), Common.loiKhia[random.nextInt(6)], Toast.LENGTH_SHORT).show();
                    Common.SO_LAN_CHOI -= 1;
                    tv_solanxoan.setText("Số lần xoắn còn: " + Common.SO_LAN_CHOI);
                    if (Common.SO_LAN_CHOI == 0) {
                        startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                    }
                }
            }
        });
        btn_da3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDapAn(btn_da3.getText().toString(), Common.CAU_HOI)) {
                    if(Common.CHECK_SOUND==1){
                        correctSound.start();
                    }
                    Common.CHOI.setDiem(Common.CHOI.getDiem()+1);
                    startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                } else {
                    if(Common.CHECK_SOUND==1){
                        wrongSound.start();
                    }
                    Toast.makeText(getApplicationContext(), Common.loiKhia[random.nextInt(6)], Toast.LENGTH_SHORT).show();
                    Common.SO_LAN_CHOI -= 1;
                    tv_solanxoan.setText("Số lần xoắn còn: " + Common.SO_LAN_CHOI);
                    if (Common.SO_LAN_CHOI == 0) {
                        startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                    }
                }
            }
        });
        btn_da4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDapAn(btn_da4.getText().toString(), Common.CAU_HOI)) {
                    if(Common.CHECK_SOUND==1){
                        correctSound.start();
                    }
                    Common.CHOI.setDiem(Common.CHOI.getDiem()+1);
                    startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                } else {
                    if(Common.CHECK_SOUND==1){
                        wrongSound.start();
                    }
                    Toast.makeText(getApplicationContext(), Common.loiKhia[random.nextInt(6)], Toast.LENGTH_SHORT).show();
                    Common.SO_LAN_CHOI -= 1;
                    tv_solanxoan.setText("Số lần xoắn còn: " + Common.SO_LAN_CHOI);
                    if (Common.SO_LAN_CHOI == 0) {
                        startActivity(new Intent(getApplicationContext(), GiaiThichActivity.class));
                    }
                }
            }
        });
        btn_baocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.NGUOI_DUNG!=null){
                    startActivity(new Intent(getApplicationContext(), BaoCaoActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Bạn phải đăng nhập đã", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void themHieuUng() {
        dsHieuUng.add(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        dsHieuUng.add(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
        dsHieuUng.add(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
    }

    private void init() {
        tv_cauhoi = findViewById(R.id.tv_cauhoi);
        img_trollface = findViewById(R.id.img_troll);
        btn_da1 = findViewById(R.id.btn_da1);
        btn_da2 = findViewById(R.id.btn_da2);
        btn_da3 = findViewById(R.id.btn_da3);
        btn_da4 = findViewById(R.id.btn_da4);
        btn_trove = findViewById(R.id.btn_trove);
        btn_baocao = findViewById(R.id.btn_baocao);
        tv_solanxoan = findViewById(R.id.tv_solanxoan);
    }

    @Override
    public void onBackPressed() {

    }

    private boolean checkDapAn(String dapanchon, CauHoi cauHoi) {
        if (dapanchon.equalsIgnoreCase(cauHoi.getDapan())) return true;
        else return false;
    }

}