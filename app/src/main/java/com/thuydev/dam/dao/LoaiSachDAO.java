package com.thuydev.dam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.database.Dbhelper;
import com.thuydev.dam.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public LoaiSachDAO(Context context){
        dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long addLoaiSach(LoaiSach _loaiSach){
        ContentValues values = new ContentValues();
        values.put("Ten", _loaiSach.getTenLoai());

        return db.insert("tb_loaiSach",null,values);
    }

    public int xoaLoaiSach(LoaiSach _loaiSach){
        String [] index = new String[]{
                String.valueOf(_loaiSach.getID_LoaiSach())
        };

        return db.delete("tb_loaiSach","id_loaiSach=?",index);
    }

    public int capNhapLoaaiSach(LoaiSach _loaiSach){
        ContentValues values = new ContentValues();
        values.put("Ten", _loaiSach.getTenLoai());

        String [] index = new String[]{
                String.valueOf(_loaiSach.getID_LoaiSach())
        };

        return db.update("tb_loaiSach",values,"id_loaiSach=?",index);
    }

    public List<LoaiSach> getAll(){
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_loaiSach",null);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                LoaiSach a = new LoaiSach(c.getInt(0),c.getString(1));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
