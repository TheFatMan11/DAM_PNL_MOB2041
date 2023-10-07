package com.thuydev.dam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.database.Dbhelper;
import com.thuydev.dam.model.PhieuMuon;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public PhieuMuonDAO(Context context) {
        dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long addPhieuMuon(PhieuMuon _phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("id_thanhvien", _phieuMuon.getID_ThanhVien());
        values.put("id_thuThu", _phieuMuon.getID_ThuTHu());
        values.put("id_sach", _phieuMuon.getID_Sach());
        values.put("ngayMuon", _phieuMuon.getNgayMuon());
        values.put("ngayTra", _phieuMuon.getNgayTra());

        return db.insert("tb_phieuMuon", null, values);
    }

    public int xoaPhieuMuon(PhieuMuon _phieuMuon) {
        String[] index = new String[]{
                String.valueOf(_phieuMuon.getSoPhieu())
        };

        return db.delete("tb_phieuMuon", "sophieu=?", index);
    }

    public int updatePhieuMuon(PhieuMuon _phieuMuon){
        ContentValues values = new ContentValues();
        values.put("id_thanhvien", _phieuMuon.getID_ThanhVien());
        values.put("id_thuThu", _phieuMuon.getID_ThuTHu());
        values.put("id_sach", _phieuMuon.getID_Sach());
        values.put("ngayMuon", _phieuMuon.getNgayMuon());
        values.put("ngayTra", _phieuMuon.getNgayTra());
        values.put("trangThai", _phieuMuon.getTrangThai());

        String[] index = new String[]{
                String.valueOf(_phieuMuon.getSoPhieu())
        };

        return db.update("tb_phieuMuon",values,"sophieu=?",index);
    }

    public List<PhieuMuon> getAll() {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_phieuMuon a INNER JOIN tb_sach b on a.id_sach = b.id_sach INNER JOIN tb_loaiSach c on b.id_tenLoai = c.id_loaiSach INNER JOIN tb_thanhVien d on a.id_thanhvien = d.id_thanhvien", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                PhieuMuon a = new PhieuMuon();
                a.setSoPhieu(c.getInt(0)); //
                a.setID_ThanhVien(c.getInt(1));
                a.setID_ThuTHu(c.getString(2));
                a.setID_Sach(c.getInt(3));
                a.setNgayMuon(c.getString(4));//
                a.setNgayTra(c.getString(5));//
                a.setTrangThai(c.getInt(6));//
                a.setTacGia(c.getString(9));//
                a.setTenSach(c.getString(10));//
                a.setNgayXuatBan(c.getString(11));
                a.setGia(c.getInt(12));//
                a.setMaLoai(c.getInt(13));
                a.setTenLoai(c.getString(14));//
                a.setHoTen(c.getString(16));//
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<PhieuMuon> getTop10() {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery("select *,count(a.id_sach) as da from tb_phieuMuon a INNER JOIN tb_sach b on a.id_sach = b.id_sach INNER JOIN tb_loaiSach c on b.id_tenLoai = c.id_loaiSach INNER JOIN tb_thanhVien d on a.id_thanhvien = d.id_thanhvien GROUP by a.id_sach ORDER by da DESC LIMIT 10 ", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                PhieuMuon a = new PhieuMuon();
                a.setSoPhieu(c.getInt(0));
                a.setID_Sach(c.getInt(3));
                a.setTacGia(c.getString(9));
                a.setTenSach(c.getString(10));
                a.setNgayXuatBan(c.getString(11));
                a.setGia(c.getInt(12));
                a.setMaLoai(c.getInt(13));
                a.setTenLoai(c.getString(14));
                a.setHoTen(c.getString(16));
                a.setSoLuongMuon(c.getInt(19));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<PhieuMuon> getThongKe(String tuNgay, String denNgay) {
        List<PhieuMuon> list = new ArrayList<>();
        String [] date = new String[]{
          tuNgay,denNgay
        };
        Cursor c = db.rawQuery("SELECT *, COUNT(a.id_sach) AS da FROM tb_phieuMuon a INNER JOIN tb_sach b ON a.id_sach = b.id_sach INNER JOIN tb_loaiSach c ON b.id_tenLoai = c.id_loaiSach INNER JOIN tb_thanhVien d on a.id_thanhvien = d.id_thanhvien WHERE a.ngayMuon BETWEEN ? AND ? GROUP BY a.id_sach;", date);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                PhieuMuon a = new PhieuMuon();
                a.setSoPhieu(c.getInt(0));
                a.setID_Sach(c.getInt(3));
                a.setTacGia(c.getString(9));
                a.setTenSach(c.getString(10));
                a.setNgayXuatBan(c.getString(11));
                a.setGia(c.getInt(12));
                a.setMaLoai(c.getInt(13));
                a.setTenLoai(c.getString(14));
                a.setHoTen(c.getString(16));
                a.setSoLuongMuon(c.getInt(19));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
