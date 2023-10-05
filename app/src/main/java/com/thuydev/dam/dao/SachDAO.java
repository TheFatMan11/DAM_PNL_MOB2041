package com.thuydev.dam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.database.Dbhelper;
import com.thuydev.dam.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public SachDAO(Context context){
        dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long addSach(Sach _sach){
        ContentValues values = new ContentValues();
        values.put("id_tenLoai", _sach.getID_Loai());
        values.put("tacGia", _sach.getTacGia());
        values.put("tenSach", _sach.getTenSach());
        values.put("ngayXuatBan", _sach.getNgayXuatBan());
        values.put("gia", _sach.getGia());
        return db.insert("tb_sach",null,values);
    }

    public int xoaSach(Sach _sach){
        String [] index = new String[]{
                String.valueOf(_sach.getID_Sach())
        };

        return db.delete("tb_sach","id_sach=?",index);
    }

    public int updateSach(Sach _sach){
        ContentValues values = new ContentValues();
        values.put("id_tenLoai", _sach.getID_Loai());
        values.put("tacGia", _sach.getTacGia());
        values.put("tenSach", _sach.getTenSach());
        values.put("ngayXuatBan", _sach.getNgayXuatBan());
        values.put("gia", _sach.getGia());

        String [] index = new String[]{
                String.valueOf(_sach.getID_Sach())
        };

        return db.update("tb_sach",values,"id_sach=?",index);
    }

    public List<Sach> getAll(){
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_sach INNER JOIN tb_loaiSach on tb_sach.id_tenLoai = tb_loaiSach.id_loaiSach",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
            Sach a = new Sach(
                    c.getInt(0),c.getInt(1),c.getString(7),
                    c.getString(2),c.getString(3),c.getString(4),c.getInt(5));
            list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
