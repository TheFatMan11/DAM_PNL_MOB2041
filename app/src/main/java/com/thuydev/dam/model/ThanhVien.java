package com.thuydev.dam.model;

public class ThanhVien {
    private int ID_ThanhVien;
    private String hoTen;
    private String diaChi;
    private String Email;
    private String cccd;


    public ThanhVien(int ID_ThanhVien, String hoTen, String diaChi, String email, String cccd) {
        this.ID_ThanhVien = ID_ThanhVien;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        Email = email;
        this.cccd = cccd;
    }

    public ThanhVien() {
    }

    public ThanhVien(int ID_ThanhVien, String hoTen, String diaChi, String email) {
        this.ID_ThanhVien = ID_ThanhVien;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        Email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public int getID_ThanhVien() {
        return ID_ThanhVien;
    }

    public void setID_ThanhVien(int ID_ThanhVien) {
        this.ID_ThanhVien = ID_ThanhVien;
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
}
