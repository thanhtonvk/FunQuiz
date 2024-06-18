package com.tondz.funquiz.Model;

public class NguoiDung {
    private String id, email, hoten, matkhau;
    private boolean phanquyen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }


    public NguoiDung(String id, String email, String hoten, String matkhau,boolean phanquyen) {
        this.id = id;
        this.email = email;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.phanquyen = phanquyen;
    }

    public NguoiDung() {

    }

    @Override
    public String toString() {
        return String.format("ID: %s\nEmail: %s\nTên: %s\nMật khẩu: %s\nPhân quyền admin: %s",id,email,hoten,matkhau,phanquyen);
    }
}
