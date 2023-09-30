package com.thuydev.dam.DTO;

public class DTO_LoaiSach {
    private int ID_LoaiSach;
    private String TenLoai;

    public DTO_LoaiSach() {
    }

    public DTO_LoaiSach(int ID_LoaiSach, String tenLoai) {
        this.ID_LoaiSach = ID_LoaiSach;
        TenLoai = tenLoai;
    }

    public int getID_LoaiSach() {
        return ID_LoaiSach;
    }

    public void setID_LoaiSach(int ID_LoaiSach) {
        this.ID_LoaiSach = ID_LoaiSach;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
