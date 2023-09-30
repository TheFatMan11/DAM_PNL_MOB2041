package com.thuydev.dam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.database.Dbhelper;
import com.thuydev.dam.dto.DTO_PhieuMuon;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public PhieuMuonDAO(Context context) {
        dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long addPhieuMuon(DTO_PhieuMuon dto_phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("id_thanhvien", dto_phieuMuon.getID_ThanhVien());
        values.put("id_thuThu", dto_phieuMuon.getID_ThuTHu());
        values.put("id_sach", dto_phieuMuon.getID_Sach());
        values.put("ngayMuon", dto_phieuMuon.getNgayMuon());
        values.put("ngayTra", dto_phieuMuon.getNgayTra());
        values.put("trangThai", dto_phieuMuon.getTrangThai());

        return db.insert("tb_phieuMuon", null, values);
    }

    public int xoaPhieuMuon(DTO_PhieuMuon dto_phieuMuon) {
        String[] index = new String[]{
                String.valueOf(dto_phieuMuon.getSoPhieu())
        };

        return db.delete("tb_phieuMuon", "sophieu=?", index);
    }

    public int updatePhieuMuon(DTO_PhieuMuon dto_phieuMuon){
        ContentValues values = new ContentValues();
        values.put("id_thanhvien", dto_phieuMuon.getID_ThanhVien());
        values.put("id_thuThu", dto_phieuMuon.getID_ThuTHu());
        values.put("id_sach", dto_phieuMuon.getID_Sach());
        values.put("ngayMuon", dto_phieuMuon.getNgayMuon());
        values.put("ngayTra", dto_phieuMuon.getNgayTra());
        values.put("trangThai", dto_phieuMuon.getTrangThai());

        String[] index = new String[]{
                String.valueOf(dto_phieuMuon.getSoPhieu())
        };

        return db.update("tb_phieuMuon",values,"sophieu=?",index);
    }

    public List<DTO_PhieuMuon> getAll() {
        List<DTO_PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_phieuMuon", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                DTO_PhieuMuon a = new DTO_PhieuMuon();
                a.setSoPhieu(0);
                a.setNgayMuon(c.getString(4));
                a.setNgayTra(c.getString(5));
                a.setTrangThai(c.getInt(6));
                a.setTacGia(c.getString(9));
                a.setTenSach(c.getString(10));
                a.setNgayXuatBan(c.getString(11));
                a.setGia(c.getInt(12));
                a.setSoLuongMuon(c.getInt(14));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<DTO_PhieuMuon> getTop10() {
        List<DTO_PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery("select *,count(a.id_sach) as da from tb_phieuMuon a INNER JOIN tb_sach b on a.id_sach = b.id_sach GROUP by a.id_sach ORDER by da DESC LIMIT 10 ", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                DTO_PhieuMuon a = new DTO_PhieuMuon();
                a.setSoPhieu(0);
                a.setTacGia(c.getString(9));
                a.setTenSach(c.getString(10));
                a.setNgayXuatBan(c.getString(11));
                a.setGia(c.getInt(12));
                a.setSoLuongMuon(c.getInt(14));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<DTO_PhieuMuon> getThongKe(String tuNgay, String denNgay) {
        List<DTO_PhieuMuon> list = new ArrayList<>();
        String [] date = new String[]{
          tuNgay,denNgay
        };
        Cursor c = db.rawQuery("SELECT *, COUNT(a.id_sach) AS da FROM tb_phieuMuon a INNER JOIN tb_sach b ON a.id_sach = b.id_sach INNER JOIN tb_loaiSach c ON b.id_tenLoai = c.id_loaiSach WHERE a.ngayMuon BETWEEN ? AND ? GROUP BY a.id_sach;", date);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                DTO_PhieuMuon a = new DTO_PhieuMuon();
                a.setSoPhieu(0);
                a.setTacGia(c.getString(9));
                a.setTenSach(c.getString(10));
                a.setNgayXuatBan(c.getString(11));
                a.setGia(c.getInt(12));
                a.setSoLuongMuon(c.getInt(14));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
