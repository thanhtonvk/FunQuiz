package com.tondz.funquiz.Actitivy.TrangQuanTri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tondz.funquiz.Database.BaoCaoDBContext;
import com.tondz.funquiz.Database.Common;
import com.tondz.funquiz.Model.BaoCao;
import com.tondz.funquiz.R;

import java.util.Random;

public class BaoCaoActivity extends AppCompatActivity {

    TextView tv_cauhoi;
    EditText edt_lydo;
    Button btn_gui;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao);
        init();
        random = new Random();
        BaoCaoDBContext baoCaoDBContext = new BaoCaoDBContext();
        tv_cauhoi.setText(Common.CAU_HOI.getCauhoi());
        btn_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaoCao baoCao = new BaoCao(random.nextInt()+"", Common.NGUOI_DUNG.getHoten(),Common.CAU_HOI.getCauhoi(), edt_lydo.getText().toString());
                baoCaoDBContext.addBaoCao(baoCao);
                Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    private void init(){
        tv_cauhoi = findViewById(R.id.tv_cauhoibaocao);
        edt_lydo = findViewById(R.id.tv_noidungbaocao);
        btn_gui = findViewById(R.id.btn_guibaocao);
    }
}