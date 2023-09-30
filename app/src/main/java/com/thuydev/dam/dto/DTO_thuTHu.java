package com.thuydev.dam.dto;

import java.io.Serializable;

public class DTO_thuTHu implements Serializable {
    private int Id_ThuThu;
    private String tenNguoiDung;
    private String maKhau;
    private String hoTen;
    private String diaChi;
    private String Email;
    private int chucVu;

    public DTO_thuTHu() {
    }

    public DTO_thuTHu(int id_ThuThu, String tenNguoiDung, String maKhau, String hoTen, String diaChi, String email, int chucVu) {
        Id_ThuThu = id_ThuThu;
        this.tenNguoiDung = tenNguoiDung;
        this.maKhau = maKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        Email = email;
        this.chucVu = chucVu;
    }

    public DTO_thuTHu(int id_ThuThu, String tenNguoiDung, String maKhau, String hoTen, String diaChi, String email) {
        Id_ThuThu = id_ThuThu;
        this.tenNguoiDung = tenNguoiDung;
        this.maKhau = maKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        Email = email;
    }

    public int getId_ThuThu() {
        return Id_ThuThu;
    }

    public void setId_ThuThu(int id_ThuThu) {
        Id_ThuThu = id_ThuThu;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String returnChucVu(){
        if (chucVu==1){
            return "Admin";
        }else {
            return "Thủ thư";
        }
    };
}
