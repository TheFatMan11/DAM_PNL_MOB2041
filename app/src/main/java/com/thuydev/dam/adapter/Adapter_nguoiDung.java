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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.dao.ThuThuDAO;
import com.thuydev.dam.model.thuTHu;
import com.thuydev.dam.R;

import java.util.List;

public class Adapter_nguoiDung extends RecyclerView.Adapter<Adapter_nguoiDung.ViewHolder> {
    Context context;
    List<thuTHu> list;
    ThuThuDAO _thuThuDAO;

    public Adapter_nguoiDung(Context context, List<thuTHu> list) {
        this.context = context;
        this.list = list;
        _thuThuDAO = new ThuThuDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nguoidung, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenDangNhap.setText(list.get(position).getTenNguoiDung());
        holder.ten.setText(list.get(position).getHoTen());
        holder.diaChi.setText(list.get(position).getDiaChi());
        holder.email.setText(list.get(position).getEmail());
        holder.chucVu.setText(list.get(position).returnChucVu());

        holder.Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(list.get(position).getChucVu()!=1){
                   xoaUser(position);
               }else{
                   Toast.makeText(context, "Không thể xóa Admin", Toast.LENGTH_SHORT).show();
               }
            }
        });

        holder.capNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhapUser(position);
            }
        });
    }

    private void capNhapUser(int p) {
       androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themnguoidung, null, false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText hoTen,diaChi, Email;
        TextView title;
        LinearLayout an;
        Button capNhap;
        hoTen = view.findViewById(R.id.edt_hoten_nd);
        diaChi = view.findViewById(R.id.edt_diaChi_nd);
        Email = view.findViewById(R.id.edt_email_nd);
        capNhap = view.findViewById(R.id.btn_add_nguoidung);
        title = view.findViewById(R.id.tv_tile_nguoiDung);
        title.setText("Sửa người dùng");
        an = view.findViewById(R.id.ll_an);
        an.setVisibility(View.GONE);
        hoTen.setText(list.get(p).getHoTen());
        diaChi.setText(list.get(p).getDiaChi());
        Email.setText(catChuoi(p));
        Button Huy = view.findViewById(R.id.btn_Huy_nguoidung);
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        capNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hoTen.getText().toString().isEmpty() && !diaChi.getText().toString().isEmpty() && !Email.getText().toString().isEmpty()) {
                        thuTHu _thuTHu = list.get(p);
                        _thuTHu.setHoTen(hoTen.getText().toString().trim());
                        _thuTHu.setDiaChi(diaChi.getText().toString().trim());
                        _thuTHu.setEmail(Email.getText().toString().trim()+"@gmail.com");
                        if (_thuThuDAO.update(_thuTHu) > 0) {
                            Toast.makeText(context, "Sửa thành công ", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(_thuThuDAO.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                } else {
                    Toast.makeText(context, "Không được để trống ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String catChuoi(int p) {
        int index = list.get(p).getEmail().indexOf("@");
        if(index!=-1){
            return list.get(p).getEmail().substring(0,index);
        }else {
            return list.get(p).getEmail();
        }
    }

    private void xoaUser(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có muốn xóa người dùng này ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(_thuThuDAO.xoaThuThu(list.get(p))>0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(_thuThuDAO.getAll());
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
        TextView ten, tenDangNhap, diaChi, email, chucVu;
        ImageView capNhap, Xoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_tenuser);
            tenDangNhap = itemView.findViewById(R.id.tv_tenlogin_user);
            diaChi = itemView.findViewById(R.id.tv_diaChi_user);
            email = itemView.findViewById(R.id.tv_Email_user);
            chucVu = itemView.findViewById(R.id.tv_chucvu_user);
            capNhap = itemView.findViewById(R.id.iv_update_user);
            Xoa = itemView.findViewById(R.id.iv_xoa_user);
        }
    }
}
