package com.tondz.funquiz.Model;

public class BaoCao {
    private String id, idbaocao, idcauhoi, lydo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdbaocao() {
        return idbaocao;
    }

    public void setIdbaocao(String idbaocao) {
        this.idbaocao = idbaocao;
    }

    public String getIdcauhoi() {
        return idcauhoi;
    }

    public void setIdcauhoi(String idcauhoi) {
        this.idcauhoi = idcauhoi;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public BaoCao(String id, String idbaocao, String idcauhoi, String lydo) {
        this.id = id;
        this.idbaocao = idbaocao;
        this.idcauhoi = idcauhoi;
        this.lydo = lydo;
    }

    public BaoCao() {

    }

    @Override
    public String toString() {
        return String.format(" ID báo cáo: %s\n Người gửi: %s\n Câu hỏi: %s \n Lý do: %s",id,idbaocao,idcauhoi,lydo);
    }
}
