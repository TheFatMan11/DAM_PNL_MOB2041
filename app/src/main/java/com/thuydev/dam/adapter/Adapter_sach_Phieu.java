package com.thuydev.dam.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thuydev.dam.R;
import com.thuydev.dam.model.Sach;
import com.thuydev.dam.model.ThanhVien;

import java.util.List;

public class Adapter_sach_Phieu extends BaseAdapter {
Context context;
List<Sach> list;

    public Adapter_sach_Phieu(Context context, List<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_theloai, parent, false);
        TextView id,ten;
        id = view.findViewById(R.id.tv_idLoai);
        ten = view.findViewById(R.id.tv_tenLoaiSP);
        id.setText(list.get(position).getID_Sach()+"");
        ten.setText(list.get(position).getTenSach());

        return view;
    }
}
