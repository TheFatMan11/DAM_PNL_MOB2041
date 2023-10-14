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

import com.thuydev.dam.dao.ThanhVienDAO;
import com.thuydev.dam.model.ThanhVien;
import com.thuydev.dam.R;

import java.util.List;

public class Adapter_thanhVien extends RecyclerView.Adapter<Adapter_thanhVien.ViewHolder> {
    Context context;
    List<ThanhVien> list;
    ThanhVienDAO _thanhVienDAO;

    public Adapter_thanhVien(Context context, List<ThanhVien> list) {
        this.context = context;
        this.list = list;
        _thanhVienDAO = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ten.setText(list.get(position).getHoTen());
        holder.diaChi.setText(list.get(position).getDiaChi());
        holder.email.setText(list.get(position).getEmail());
        holder.CCCD.setText(list.get(position).getCccd());

        holder.Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaThanhVien(position);
            }
        });

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                capNhapThanhVien(position);
                return false;
            }
        });
    }


    private void capNhapThanhVien(int p) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themthanhvien,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText ten,diaChi,email,cccd;
        TextView title;
        Button sua ;
        ten=view.findViewById(R.id.edt_hoten);
        diaChi = view.findViewById(R.id.edt_diaChi);
        email = view.findViewById(R.id.edt_email);
        cccd = view.findViewById(R.id.edt_cccd);
        sua = view.findViewById(R.id.btn_add_thanhvien);
        title = view.findViewById(R.id.title_thanhvien);
        Button Huy = view.findViewById(R.id.btn_Huy_thanhvien);
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        title.setText("Sửa thành viên");
        sua.setText("Sửa");
        ThanhVien _thanhVien =list.get(p);
        ten.setText(_thanhVien.getHoTen());
        diaChi.setText(_thanhVien.getDiaChi());
        email.setText(catChuoi(p));
        cccd.setText(_thanhVien.getCccd());
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ten.getText().toString().isEmpty()&&!diaChi.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()){
                    _thanhVien.setHoTen(ten.getText().toString().trim());
                    _thanhVien.setDiaChi(diaChi.getText().toString().trim());
                    _thanhVien.setEmail(email.getText().toString().trim()+"@gmail.com");
                    _thanhVien.setCccd(cccd.getText().toString().trim());
                    if(_thanhVien.getCccd().length()==12){
                        if (_thanhVienDAO.UpdateThanhNien(_thanhVien)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(_thanhVienDAO.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }else {
                        Toast.makeText(context, "Căn cước phải 12 chữ số", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
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
    private void xoaThanhVien(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có muốn xóa thành viên này ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (_thanhVienDAO.xoaThanhNien(list.get(p)) > 0) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(_thanhVienDAO.getAll());
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
        TextView ten, diaChi, email,CCCD;
        ImageView  Xoa;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_tenThanhvien);
            diaChi = itemView.findViewById(R.id.tv_diaChi_thanhvien);
            email = itemView.findViewById(R.id.tv_Email_thanhVien);
            CCCD = itemView.findViewById(R.id.tv_CCCD_thanhVien);
            Xoa = itemView.findViewById(R.id.iv_xoaThanhVien);
            linearLayout = itemView.findViewById(R.id.ll_thanhvien);
        }
    }
}
