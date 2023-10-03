package com.thuydev.dam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.dam.database.Dbhelper;
import com.thuydev.dam.model.thuTHu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public ThuThuDAO(Context context){
        dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long addThuThu(thuTHu _thuTHu){
        ContentValues values = new ContentValues();
        values.put("maThuThu", _thuTHu.getTenNguoiDung());
        values.put("maKhau", _thuTHu.getMaKhau());
        values.put("hoTen", _thuTHu.getHoTen());
        values.put("diachi", _thuTHu.getDiaChi());
        values.put("email", _thuTHu.getEmail());
        return db.insert("tb_thuThu",null,values);
    }

    public int xoaThuThu(thuTHu _thuTHu){
        String [] index = new String[]{
                _thuTHu.getTenNguoiDung()
        };
        return db.delete("tb_thuThu","maThuThu=?",index);
    }

    public int update(thuTHu _thuTHu){
        ContentValues values = new ContentValues();
        values.put("maThuThu", _thuTHu.getTenNguoiDung());
        values.put("maKhau", _thuTHu.getMaKhau());
        values.put("hoTen", _thuTHu.getHoTen());
        values.put("diachi", _thuTHu.getDiaChi());
        values.put("email", _thuTHu.getEmail());

        String [] index = new String[]{
                _thuTHu.getTenNguoiDung()
        };

       return db.update("tb_thuThu",values,"maThuThu=?",index);
    }

    public int updatePass(thuTHu _thuTHu){
        ContentValues values = new ContentValues();
        values.put("maKhau", _thuTHu.getMaKhau());
        String [] index = new String[]{
               _thuTHu.getTenNguoiDung()
        };

        return db.update("tb_thuThu",values,"maThuThu=?",index);
    }
    public List<thuTHu> getNDDangNhap(String tenTaiKhoan, String MatKhau){
        List<thuTHu> list= new ArrayList<>();
        String [] data = new String[]{
                tenTaiKhoan,MatKhau
        };
        Cursor c = db.rawQuery("select * from tb_thuThu where maThuThu=? and maKhau=?",data);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            thuTHu a = new thuTHu();
            a.setTenNguoiDung(c.getString(0));
            a.setMaKhau(c.getString(1));
            a.setHoTen(c.getString(2));
            a.setDiaChi(c.getString(3));
            a.setEmail(c.getString(4));
            a.setChucVu(c.getInt(5));
            list.add(a);
        }
        return list;
    }
    public List<thuTHu> getQuenPass(String tenTaiKhoan, String email){
        List<thuTHu> list= new ArrayList<>();
        String [] data = new String[]{
                tenTaiKhoan,email
        };
        Cursor c = db.rawQuery("select * from tb_thuThu where maThuThu=? and email=?",data);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            thuTHu a = new thuTHu();
            a.setTenNguoiDung(c.getString(0));
            a.setMaKhau(c.getString(1));
            a.setHoTen(c.getString(2));
            a.setDiaChi(c.getString(3));
            a.setEmail(c.getString(4));
            a.setChucVu(c.getInt(5));
            list.add(a);
        }
        return list;
    }
    public List<thuTHu> getAll(){
        List<thuTHu> list= new ArrayList<>();
        Cursor c = db.rawQuery("select * from tb_thuThu ",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                thuTHu a = new thuTHu();
                a.setTenNguoiDung(c.getString(0));
                a.setMaKhau(c.getString(1));
                a.setHoTen(c.getString(2));
                a.setDiaChi(c.getString(3));
                a.setEmail(c.getString(4));
                a.setChucVu(c.getInt(5));
                list.add(a);
            }while (c.moveToNext());
        }
        return list;
    }
}
