package com.thuydev.dam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.DATABASE.DB_PNL;
import com.thuydev.dam.DTO.DTO_Sach;
import com.thuydev.dam.DTO.DTO_ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class DAO_Sach {
    SQLiteDatabase db;
    DB_PNL db_pnl;

    public DAO_Sach(Context context){
        db_pnl = new DB_PNL(context);
        db = db_pnl.getWritableDatabase();
    }

    public long addSach(DTO_Sach dto_sach){
        ContentValues values = new ContentValues();
        values.put("id_tenLoai",dto_sach.getID_Loai());
        values.put("tacGia",dto_sach.getTacGia());
        values.put("tenSach",dto_sach.getTenSach());
        values.put("ngayXuatBan",dto_sach.getNgayXuatBan());
        values.put("gia",dto_sach.getGia());
        return db.insert("tb_sach",null,values);
    }

    public int xoaSach(DTO_Sach dto_sach){
        String [] index = new String[]{
                String.valueOf(dto_sach.getID_Sach())
        };

        return db.delete("tb_sach","id_sach=?",index);
    }

    public int updateSach(DTO_Sach dto_sach){
        ContentValues values = new ContentValues();
        values.put("id_tenLoai",dto_sach.getID_Loai());
        values.put("tacGia",dto_sach.getTacGia());
        values.put("tenSach",dto_sach.getTenSach());
        values.put("ngayXuatBan",dto_sach.getNgayXuatBan());
        values.put("gia",dto_sach.getGia());

        String [] index = new String[]{
                String.valueOf(dto_sach.getID_Sach())
        };

        return db.update("tb_sach",values,"id_sach=?",index);
    }

    public List<DTO_Sach> getAll(){
        List<DTO_Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_sach",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
            DTO_Sach a = new DTO_Sach(
                    c.getInt(0),c.getInt(1),
                    c.getString(2),c.getString(3),c.getString(4),c.getInt(5));
            list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}