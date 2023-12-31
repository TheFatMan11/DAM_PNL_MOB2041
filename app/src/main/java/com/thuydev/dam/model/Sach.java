package com.thuydev.dam.model;

public class Sach {
    private int ID_Sach;
    private int ID_Loai;
    private String tenLoai;
    private String tacGia;
    private String tenSach;
    private String ngayXuatBan;
    private int gia;

    public Sach() {
    }

    public Sach(int ID_Sach, int ID_Loai, String tenLoai, String tacGia, String tenSach, String ngayXuatBan, int gia) {
        this.ID_Sach = ID_Sach;
        this.ID_Loai = ID_Loai;
        this.tenLoai = tenLoai;
        this.tacGia = tacGia;
        this.tenSach = tenSach;
        this.ngayXuatBan = ngayXuatBan;
        this.gia = gia;
    }

    public Sach(int ID_Sach, int ID_Loai, String tacGia, String tenSach, String ngayXuatBan, int gia) {
        this.ID_Sach = ID_Sach;
        this.ID_Loai = ID_Loai;
        this.tacGia = tacGia;
        this.tenSach = tenSach;
        this.ngayXuatBan = ngayXuatBan;
        this.gia = gia;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getID_Sach() {
        return ID_Sach;
    }

    public void setID_Sach(int ID_Sach) {
        this.ID_Sach = ID_Sach;
    }

    public int getID_Loai() {
        return ID_Loai;
    }

    public void setID_Loai(int ID_Loai) {
        this.ID_Loai = ID_Loai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
