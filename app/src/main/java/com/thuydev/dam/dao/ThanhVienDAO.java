package com.thuydev.dam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.database.Dbhelper;
import com.thuydev.dam.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public ThanhVienDAO(Context context){
        dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long addThanhNien(ThanhVien _thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoten", _thanhVien.getHoTen());
        values.put("diachi", _thanhVien.getDiaChi());
        values.put("email", _thanhVien.getEmail());
        values.put("cccd",_thanhVien.getCccd());
        return db.insert("tb_thanhVien",null,values);
    }

    public int xoaThanhNien(ThanhVien _thanhVien){
        String [] index = new String[]{
                String.valueOf(_thanhVien.getID_ThanhVien())
        };

        return db.delete("tb_thanhVien","id_thanhvien=?",index);
    }

    public int UpdateThanhNien(ThanhVien _thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoten", _thanhVien.getHoTen());
        values.put("diachi", _thanhVien.getDiaChi());
        values.put("email", _thanhVien.getEmail());
        values.put("cccd",_thanhVien.getCccd());
        String [] index = new String[]{
                String.valueOf(_thanhVien.getID_ThanhVien())
        };

        return db.update("tb_thanhVien",values,"id_thanhvien=?",index);
    }

    public List<ThanhVien> getAll(){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_thanhVien",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                ThanhVien a = new ThanhVien(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
