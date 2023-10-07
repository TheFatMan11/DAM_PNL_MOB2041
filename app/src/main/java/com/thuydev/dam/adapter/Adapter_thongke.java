package com.thuydev.dam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.R;
import com.thuydev.dam.dao.SachDAO;
import com.thuydev.dam.model.PhieuMuon;
import com.thuydev.dam.model.Sach;

import java.util.List;

public class Adapter_thongke extends RecyclerView.Adapter<Adapter_thongke.viewHoder> {
    List<PhieuMuon> list;
    Context context;
    SachDAO sachDAO;

    public Adapter_thongke(List<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
        sachDAO = new SachDAO(context);
    }

    @NonNull
    @Override
    public viewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10, parent, false);
        return new viewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoder holder, @SuppressLint("RecyclerView") int position) {
        holder.idSach.setText(list.get(position).getID_Sach()+"");
        holder.tenSach.setText(list.get(position).getTenSach());
        holder.tenloai.setText("Số lượng mượn: ");
        holder.theLoai.setText(list.get(position).getSoLuongMuon()+"");

    }

    private void xemThem(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach_morong, null, false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView id,ten,theLoai,tacGia,ngayXuat,gia;
        id = view.findViewById(R.id.tv_maSachMore);
        ten = view.findViewById(R.id.tv_tenSach_more);
        theLoai = view.findViewById(R.id.tv_theLoai_more);
        tacGia = view.findViewById(R.id.tv_tacGia_more);
        ngayXuat = view.findViewById(R.id.tv_ngayXuatBan_more);
        gia = view.findViewById(R.id.tv_gia_more);
        id.setText(sach.getID_Sach()+"");
        ten.setText(sach.getTenSach());
        theLoai.setText(sach.getTenLoai());
        tacGia.setText(sach.getTacGia());
        ngayXuat.setText(sach.getNgayXuatBan());
        gia.setText(sach.getGia()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHoder extends RecyclerView.ViewHolder {
        TextView idSach, tenSach, theLoai,tenloai;


        public viewHoder(@NonNull View itemView) {
            super(itemView);
            idSach = itemView.findViewById(R.id.tv_masach);
            tenSach = itemView.findViewById(R.id.tv_tenSach);
            theLoai = itemView.findViewById(R.id.tv_theLoai);
            tenloai = itemView.findViewById(R.id.theloai);

        }
    }
}
