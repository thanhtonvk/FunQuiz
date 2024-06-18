package com.tondz.funquiz.Model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Choi {
    private String id,nguoichoi;
    private int diem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdnguoichoi() {
        return nguoichoi;
    }

    public void setIdnguoichoi(String idnguoichoi) {
        this.nguoichoi = idnguoichoi;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public Choi(String id, String nguoichoi, int diem) {
        this.id = id;
        this.nguoichoi = nguoichoi;
        this.diem = diem;
    }
    public Choi(){

    }

    @Override
    public String toString() {
        return String.format("Người chơi: %s\nĐiểm: %s",nguoichoi,diem);
    }
}
