package com.thuydev.dam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.DATABASE.DB_PNL;
import com.thuydev.dam.DTO.DTO_LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class DAO_LoaiSach {
    SQLiteDatabase db;
    DB_PNL db_pnl;

    public DAO_LoaiSach(Context context){
        db_pnl = new DB_PNL(context);
        db = db_pnl.getWritableDatabase();
    }

    public long addLoaiSach(DTO_LoaiSach dto_loaiSach){
        ContentValues values = new ContentValues();
        values.put("Ten",dto_loaiSach.getTenLoai());

        return db.insert("tb_loaiSach",null,values);
    }

    public int xoaLoaiSach(DTO_LoaiSach dto_loaiSach){
        String [] index = new String[]{
                String.valueOf(dto_loaiSach.getID_LoaiSach())
        };

        return db.delete("tb_loaiSach","id_loaiSach=?",index);
    }

    public int capNhapLoaaiSach(DTO_LoaiSach dto_loaiSach){
        ContentValues values = new ContentValues();
        values.put("Ten",dto_loaiSach.getTenLoai());

        String [] index = new String[]{
                String.valueOf(dto_loaiSach.getID_LoaiSach())
        };

        return db.update("tb_loaiSach",values,"id_loaiSach=?",index);
    }

    public List<DTO_LoaiSach> getAll(){
        List<DTO_LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_loaiSach",null);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                DTO_LoaiSach a = new DTO_LoaiSach(c.getInt(0),c.getString(1));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
