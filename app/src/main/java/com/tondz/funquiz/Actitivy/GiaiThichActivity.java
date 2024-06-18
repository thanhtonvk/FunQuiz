package com.tondz.funquiz.Actitivy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tondz.funquiz.Actitivy.TrangQuanTri.BaoCaoActivity;
import com.tondz.funquiz.Database.ChoiDBContext;
import com.tondz.funquiz.Database.Common;
import com.tondz.funquiz.MainActivity;
import com.tondz.funquiz.R;

import java.util.Random;

public class GiaiThichActivity extends AppCompatActivity {

    ImageView img_troll;
    Random random;
    TextView tv_nhanxet,tv_giaithich,tv_solanxoan;
    Button btn_choitiep,btn_dungchoi,btn_trove,btn_baocao;
    MediaPlayer loseGame;
    ChoiDBContext choiDBContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giai_thich);
        init();
        onClick();
        choiDBContext = new ChoiDBContext();
        random =new Random();
        loseGame = MediaPlayer.create(this,R.raw.lose_game);
        img_troll.setImageResource(Common.dsHinhAnh().get(random.nextInt(12)));
        tv_solanxoan.setText("Số lần xoắn còn: "+Common.SO_LAN_CHOI);
        tv_giaithich.setText(Common.CAU_HOI.getGiaithich());
        if(Common.SO_LAN_CHOI==0){
            choiDBContext.addChoi(Common.CHOI);
            tv_nhanxet.setText(Common.loiKhia[random.nextInt(6)]);
            btn_choitiep.setText("Cho em chơi lại phát nữa!!");
            if(Common.CHECK_SOUND==1){
                loseGame.start();
            }
        }else{
            tv_nhanxet.setText(Common.nhanXet[random.nextInt(5)]);
        }
    }
    private void init(){
        tv_nhanxet = findViewById(R.id.tv_nhanxet);
        tv_giaithich = findViewById(R.id.tv_giaithich);
        img_troll = findViewById(R.id.img_troll);
        btn_choitiep = findViewById(R.id.btn_choitiep);
        btn_dungchoi= findViewById(R.id.btn_dungchoi);
        btn_trove = findViewById(R.id.btn_trove);
        btn_baocao = findViewById(R.id.btn_baocao);
        tv_solanxoan = findViewById(R.id.tv_solanxoan);
    }
    private void onClick(){
        btn_choitiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.SO_LAN_CHOI==0){
                    Common.SO_LAN_CHOI = 3;
                    startActivity(new Intent(getApplicationContext(),ManChoiActivity.class));
                    btn_choitiep.setText("Chiến tiếp, xoắn gì");
                    loseGame.stop();
                    finish();
                }else{
                    loseGame.stop();
                    startActivity(new Intent(getApplicationContext(),ManChoiActivity.class));
                    finish();
                }

            }
        });
        btn_dungchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loseGame.stop();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
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
                if(Common.NGUOI_DUNG!=null){
                    startActivity(new Intent(getApplicationContext(), BaoCaoActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Bạn phải đăng nhập đã", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}