package com.thuydev.dam.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    static String name = "DB_PNL";
    static int ver = 2;

    public Dbhelper(Context context){
        super(context,name,null,ver);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_thuThu ="CREATE TABLE tb_thuThu (     maThuThu TEXT    UNIQUE                      PRIMARY KEY,     maKhau   TEXT,     hoTen    TEXT,     diachi   TEXT,     email    TEXT,     chucVu   INTEGER DEFAULT (0) );";
        String tb_thanhVien = "CREATE TABLE tb_thanhVien (     id_thanhvien INTEGER PRIMARY KEY AUTOINCREMENT,     hoten        TEXT,     diachi       TEXT,     email        TEXT    UNIQUE );";
        String tb_loaiSach = "CREATE TABLE tb_loaiSach (     id_loaiSach INTEGER PRIMARY KEY AUTOINCREMENT,     Ten         TEXT    UNIQUE );";
        String tb_sach="CREATE TABLE tb_sach (     id_sach     INTEGER PRIMARY KEY AUTOINCREMENT,     id_tenLoai          REFERENCES tb_loaiSach (id_loaiSach) ON DELETE CASCADE                                                              ON UPDATE CASCADE,     tacGia      TEXT,     tenSach     TEXT,     ngayXuatBan TEXT,     gia         INTEGER );";
        String tb_phieuMuon="CREATE TABLE tb_phieuMuon (     sophieu      INTEGER PRIMARY KEY AUTOINCREMENT,     id_thanhvien INTEGER REFERENCES tb_thanhVien (id_thanhvien) ON DELETE CASCADE                                                                 ON UPDATE CASCADE,     id_thuThu    INTEGER REFERENCES tb_thuThu (id_thuThu),     id_sach      INTEGER REFERENCES tb_sach (id_sach) ON DELETE CASCADE                                                       ON UPDATE CASCADE,     ngayMuon     TEXT,     ngayTra      TEXT,     trangThai    INTEGER DEFAULT (0) );";
        String admin = "insert into tb_thuThu(id_thuThu,tenNguoiDung, maKhau,hoTen,diachi,email,chucVu) values('admin','thuy','123','Quàng Ngọc Thủy','ok','ok',1)";

        db.execSQL(tb_thuThu);
        db.execSQL(tb_thanhVien);
        db.execSQL(tb_loaiSach);
        db.execSQL(tb_sach);
        db.execSQL(tb_phieuMuon);
        db.execSQL(admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists tb_thuThu");
            db.execSQL("drop table if exists tb_thanhVien");
            db.execSQL("drop table if exists tb_loaiSach");
            db.execSQL("drop table if exists tb_sach");
            db.execSQL("drop table if exists tb_phieuMuon");
            onCreate(db);
        }
    }
}
