package com.thuydev.dam.DTO;

public class DTO_PhieuMuon {
    private int soPhieu;
    private int ID_ThuTHu;
    private int ID_ThanhVien;
    private int ID_Sach;
    private String NgayMuon;
    private String NgayTra;
    private int trangThai;
    private int gia;

    public DTO_PhieuMuon(int soPhieu, int ID_ThuTHu, int ID_ThanhVien, int ID_Sach, String ngayMuon, String ngayTra, int trangThai) {
        this.soPhieu = soPhieu;
        this.ID_ThuTHu = ID_ThuTHu;
        this.ID_ThanhVien = ID_ThanhVien;
        this.ID_Sach = ID_Sach;
        NgayMuon = ngayMuon;
        NgayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public DTO_PhieuMuon(int soPhieu, int ID_ThuTHu, int ID_ThanhVien, int ID_Sach, String ngayMuon, String ngayTra, int trangThai, int gia) {
        this.soPhieu = soPhieu;
        this.ID_ThuTHu = ID_ThuTHu;
        this.ID_ThanhVien = ID_ThanhVien;
        this.ID_Sach = ID_Sach;
        NgayMuon = ngayMuon;
        NgayTra = ngayTra;
        this.trangThai = trangThai;
        this.gia = gia;
    }

    public int getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(int soPhieu) {
        this.soPhieu = soPhieu;
    }

    public int getID_ThuTHu() {
        return ID_ThuTHu;
    }

    public void setID_ThuTHu(int ID_ThuTHu) {
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
}
