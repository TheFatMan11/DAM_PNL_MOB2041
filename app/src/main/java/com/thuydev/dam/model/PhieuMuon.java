package com.thuydev.dam.model;

public class PhieuMuon {
    private int soPhieu;
    private String ID_ThuTHu;
    private int ID_ThanhVien;
    private int ID_Sach;
    private String NgayMuon;
    private String NgayTra;
    private int trangThai;
    // dữ liệu sách
    private String tacGia;
    private String tenSach;
    private String ngayXuatBan;
    private int gia;
    private int soLuongMuon;
    // dữ liệu loại sách
    private int maLoai;
    private String tenLoai;
    // dữ liệu thành viên
    private String hoTen;

    public int getMaLoai() {
        return maLoai;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }





    public int getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(int soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getID_ThuTHu() {
        return ID_ThuTHu;
    }

    public void setID_ThuTHu(String ID_ThuTHu) {
        this.ID_ThuTHu = ID_ThuTHu;
    }

    public int getID_ThanhVien() {
        return ID_ThanhVien;
    }

    public void setID_ThanhVien(int ID_ThanhVien) {
        this.ID_ThanhVien = ID_ThanhVien;
    }

    public int getID_Sach() {
        return ID_Sach;
    }

    public void setID_Sach(int ID_Sach) {
        this.ID_Sach = ID_Sach;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String ngayTra) {
        NgayTra = ngayTra;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
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

    public PhieuMuon() {
    }
}
