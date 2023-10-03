package com.thuydev.dam.model;

import java.io.Serializable;

public class thuTHu implements Serializable {

    private String tenNguoiDung;
    private String maKhau;
    private String hoTen;
    private String diaChi;
    private String Email;
    private int chucVu;

    public thuTHu() {
    }

    public thuTHu(String tenNguoiDung, String maKhau, String hoTen, String diaChi, String email, int chucVu) {

        this.tenNguoiDung = tenNguoiDung;
        this.maKhau = maKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        Email = email;
        this.chucVu = chucVu;
    }

    public thuTHu(String tenNguoiDung, String maKhau, String hoTen, String diaChi, String email) {
        this.tenNguoiDung = tenNguoiDung;
        this.maKhau = maKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        Email = email;
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
