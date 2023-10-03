package com.thuydev.dam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.dao.LoaiSachDAO;
import com.thuydev.dam.model.LoaiSach;
import com.thuydev.dam.R;

import java.util.List;

public class Adapter_LoaiSach extends RecyclerView.Adapter<Adapter_LoaiSach.ViewHolder> {
    Context context;
    List<LoaiSach> list;
    LoaiSachDAO _loaiSachDAO;
    EditText editText;
    ImageButton imageButton,soSanh;
    int swichEDT = 0;
    public Adapter_LoaiSach(Context context, List<LoaiSach> list) {
        this.context = context;
        this.list = list;
        _loaiSachDAO = new LoaiSachDAO(context);
        soSanh = new ImageButton(context);
        soSanh.setBackgroundResource(R.drawable.loop);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ten.setText(list.get(position).getTenLoai());
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                xoaVacapnhap(position);
                return false;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLoaiMoi();
            }
        });
    }

    private void xoaVacapnhap(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
       LayoutInflater inflater = ((Activity)context).getLayoutInflater();
       View view = inflater.inflate(R.layout.dialog_more2,null,false);
       builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        Button sua,xoa;
        sua = view.findViewById(R.id.btn_suaLoaiSach);
        xoa = view.findViewById(R.id.btn_xoaLoaiSach);

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhapLoai(p);
                dialog.dismiss();
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaLoai(p);
                dialog.dismiss();
            }
        });

    }

    private void addLoaiMoi() {
        if(swichEDT==0){
            swichEDT=1;
            editText.setVisibility(View.VISIBLE);
        }else {
            // thêm loại sách ở đây
            swichEDT=0;
            addLoai();
            editText.setText("");
            editText.setVisibility(View.GONE);
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private void addLoai() {
        LoaiSach _loaiSach = new LoaiSach();
        _loaiSach.setTenLoai(editText.getText().toString().trim());
        if(_loaiSachDAO.addLoaiSach(_loaiSach)>0){
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            list.clear();
            list.addAll(_loaiSachDAO.getAll());
            notifyDataSetChanged();
        }
    }
    private void capNhapLoai(int p) {
        if (editText != null) {
            editText.setVisibility(View.VISIBLE);
            editText.setText(list.get(p).getTenLoai());
            editText.setHint("Hãy nhập tên loại muốn sửa");
            imageButton.setBackgroundResource(R.drawable.loop);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoaiSach _loaiSach = list.get(p);
                    _loaiSach.setTenLoai(editText.getText().toString().trim());
                        if(!editText.getText().toString().isEmpty()){
                           try {
                               swichEDT = 0;
                               if (_loaiSachDAO.capNhapLoaaiSach(_loaiSach) > 0) {
                                   Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                   notifyDataSetChanged();
                                   editText.setText("");
                                   imageButton.setBackgroundResource(R.drawable.add);
                                   editText.setVisibility(View.GONE);
                               }
                           }catch (Exception ignored){

                           }
                        }
                }
            });
        }
    }

    private void xoaLoai(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có muốn xóa loại này ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (_loaiSachDAO.xoaLoaiSach(list.get(p)) > 0) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(_loaiSachDAO.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ten;
        LinearLayout item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_tenLoai);
            item = itemView.findViewById(R.id.item_tenLoai);
        }
    }

    public void getEdittex(EditText editText) {
        this.editText = editText;
    }

    public void getImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }
}
