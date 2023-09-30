package com.thuydev.dam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.DATABASE.DB_PNL;
import com.thuydev.dam.DTO.DTO_ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class DAO_ThanhVien {
    SQLiteDatabase db;
    DB_PNL db_pnl;

    public DAO_ThanhVien(Context context){
        db_pnl = new DB_PNL(context);
        db = db_pnl.getWritableDatabase();
    }

    public long addThanhNien(DTO_ThanhVien dto_thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoten",dto_thanhVien.getHoTen());
        values.put("diachi",dto_thanhVien.getDiaChi());
        values.put("email",dto_thanhVien.getEmail());
        return db.insert("tb_thanhVien",null,values);
    }

    public int xoaThanhNien(DTO_ThanhVien dto_thanhVien){
        String [] index = new String[]{
                String.valueOf(dto_thanhVien.getID_ThanhVien())
        };

        return db.delete("tb_thanhVien","id_thanhvien=?",index);
    }

    public int UpdateThanhNien(DTO_ThanhVien dto_thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoten",dto_thanhVien.getHoTen());
        values.put("diachi",dto_thanhVien.getDiaChi());
        values.put("email",dto_thanhVien.getEmail());

        String [] index = new String[]{
                String.valueOf(dto_thanhVien.getID_ThanhVien())
        };

        return db.update("tb_thanhVien",values,"id_thanhvien=?",index);
    }

    public List<DTO_ThanhVien> getAll(){
        List<DTO_ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_thanhVien",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                DTO_ThanhVien a = new DTO_ThanhVien(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
