package com.tondz.funquiz.Actitivy.TrangQuanTri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tondz.funquiz.Database.CauHoiDBContext;
import com.tondz.funquiz.Model.CauHoi;
import com.tondz.funquiz.R;

import java.util.Random;

public class NhapCauHoiActivity extends AppCompatActivity {

    EditText edt_cauhoi, edt_dapan1, edt_dapan2, edt_dapan3, edt_dapan4, edt_giaithich;
    Spinner sp_dapan;
    String DAP_AN = "";
    Button btn_capnhat;
    CauHoiDBContext cauHoiDBContext;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_cau_hoi);
        init();
        cauHoiDBContext = new CauHoiDBContext();
        String[] arr = {"Đáp án 1", "Đáp án 2", "Đáp án 3", "Đáp án 4"};
        random = new Random();
        sp_dapan.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr));
        sp_dapan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DAP_AN = arr[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                DAP_AN = arr[0];
            }
        });
        if (QuanLyCauHoiActivity.cauHoi != null) {
            CauHoi cauHoi = QuanLyCauHoiActivity.cauHoi;
            edt_cauhoi.setText(cauHoi.getCauhoi());
            edt_dapan1.setText(cauHoi.getA());
            edt_dapan2.setText(cauHoi.getB());
            edt_dapan3.setText(cauHoi.getC());
            edt_dapan4.setText(cauHoi.getD());
            edt_giaithich.setText(cauHoi.getGiaithich());
            btn_capnhat.setText("Cập nhật");
            btn_capnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CauHoi tmp;
                    switch (DAP_AN) {
                        case "Đáp án 1":
                            tmp = new CauHoi(cauHoi.getId(), edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan1.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.updateCauHoi(cauHoi.getId(),tmp);
                            break;
                        case "Đáp án 2":
                            tmp = new CauHoi(cauHoi.getId(), edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan2.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.updateCauHoi(cauHoi.getId(),tmp);
                            break;
                        case "Đáp án 3":
                            tmp = new CauHoi(cauHoi.getId(), edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan3.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.updateCauHoi(cauHoi.getId(),tmp);
                            break;
                        case "Đáp án 4":
                            tmp = new CauHoi(cauHoi.getId(), edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan4.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.updateCauHoi(cauHoi.getId(),tmp);
                            break;
                    }
                    Toast.makeText(getApplicationContext(), "Cập nhật câu hỏi thành công", Toast.LENGTH_SHORT).show();
                    QuanLyCauHoiActivity.cauHoi = null;
                    btn_capnhat.setText("Thêm");
                    finish();
                }
            });
        } else {
            btn_capnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CauHoi cauHoi;
                    switch (DAP_AN) {
                        case "Đáp án 1":
                            cauHoi = new CauHoi(random.nextInt() + "", edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan1.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.addCauHoi(cauHoi);
                            break;
                        case "Đáp án 2":
                            cauHoi = new CauHoi(random.nextInt() + "", edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan2.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.addCauHoi(cauHoi);
                            break;
                        case "Đáp án 3":
                            cauHoi = new CauHoi(random.nextInt() + "", edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan3.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.addCauHoi(cauHoi);
                            break;
                        case "Đáp án 4":
                            cauHoi = new CauHoi(random.nextInt() + "", edt_cauhoi.getText().toString(), edt_dapan1.getText().toString(), edt_dapan2.getText().toString(), edt_dapan3.getText().toString(), edt_dapan4.getText().toString(), edt_dapan4.getText().toString(), edt_giaithich.getText().toString());
                            cauHoiDBContext.addCauHoi(cauHoi);
                            break;
                    }
                    Toast.makeText(getApplicationContext(), "Thêm câu hỏi thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

    }

    private void init() {
        edt_cauhoi = findViewById(R.id.edt_nhapcauhoi);
        edt_dapan1 = findViewById(R.id.edt_dapan1);
        edt_dapan2 = findViewById(R.id.edt_dapan2);
        edt_dapan3 = findViewById(R.id.edt_dapan3);
        edt_dapan4 = findViewById(R.id.edt_dapan4);
        edt_giaithich = findViewById(R.id.edt_giaithich);
        sp_dapan = findViewById(R.id.spiner_dapandung);
        btn_capnhat = findViewById(R.id.btn_capnhatcauhoi);
    }
}