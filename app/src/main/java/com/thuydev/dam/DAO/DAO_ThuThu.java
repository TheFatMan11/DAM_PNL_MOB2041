package com.thuydev.dam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.DATABASE.DB_PNL;
import com.thuydev.dam.DTO.DTO_thuTHu;

import java.util.ArrayList;
import java.util.List;

public class DAO_ThuThu {
    SQLiteDatabase db;
    DB_PNL db_pnl;

    public DAO_ThuThu(Context context){
        db_pnl = new DB_PNL(context);
        db = db_pnl.getWritableDatabase();
    }

    public long addThuThu(DTO_thuTHu dto_thuTHu){
        ContentValues values = new ContentValues();
        values.put("tenNguoiDung",dto_thuTHu.getTenNguoiDung());
        values.put("maKhau",dto_thuTHu.getMaKhau());
        values.put("hoTen",dto_thuTHu.getHoTen());
        values.put("diachi",dto_thuTHu.getDiaChi());
        values.put("email",dto_thuTHu.getEmail());

        return db.insert("tb_thuThu",null,values);
    }

    public int xoaThuThu(DTO_thuTHu dto_thuTHu){
        String [] index = new String[]{
                String.valueOf(dto_thuTHu.getId_ThuThu())
        };

        return db.delete("tb_thuThu","id_thuThu=?",index);
    }

    public int update(DTO_thuTHu dto_thuTHu){
        ContentValues values = new ContentValues();
        values.put("tenNguoiDung",dto_thuTHu.getTenNguoiDung());
        values.put("maKhau",dto_thuTHu.getMaKhau());
        values.put("hoTen",dto_thuTHu.getHoTen());
        values.put("diachi",dto_thuTHu.getDiaChi());
        values.put("email",dto_thuTHu.getEmail());

        String [] index = new String[]{
                String.valueOf(dto_thuTHu.getId_ThuThu())
        };

       return db.update("tb_thuThu",values,"id_thuThu=?",index);
    }

    public DTO_thuTHu getNDDangNhap(String tenTaiKhoan,String MatKhau){
        DTO_thuTHu a = new DTO_thuTHu();
        String [] data = new String[]{
                tenTaiKhoan,MatKhau
        };
        Cursor c = db.rawQuery("select * from tb_thuThu where tenNguoiDung=? and maKhau=?",data);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            a.setId_ThuThu(c.getInt(0));
            a.setTenNguoiDung(c.getString(1));
            a.setMaKhau(c.getString(2));
            a.setHoTen(c.getString(3));
            a.setDiaChi(c.getString(4));
            a.setEmail(c.getString(5));
            a.setChucVu(c.getInt(6));
        }
        return a;
    }

    public List<DTO_thuTHu> getAll(){
        List<DTO_thuTHu> list= new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_thuThu ",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                DTO_thuTHu a = new DTO_thuTHu();
                a.setId_ThuThu(c.getInt(0));
                a.setTenNguoiDung(c.getString(1));
                a.setMaKhau(c.getString(2));
                a.setHoTen(c.getString(3));
                a.setDiaChi(c.getString(4));
                a.setEmail(c.getString(5));
                a.setChucVu(c.getInt(6));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
