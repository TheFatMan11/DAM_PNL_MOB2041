package com.thuydev.dam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.R;
import com.thuydev.dam.dao.LoaiSachDAO;
import com.thuydev.dam.dao.SachDAO;
import com.thuydev.dam.model.LoaiSach;
import com.thuydev.dam.model.Sach;

import java.util.Calendar;
import java.util.List;

public class Adapter_Sach extends RecyclerView.Adapter<Adapter_Sach.viewHoder> {
    List<Sach> list;
    Context context;
    SachDAO sachDAO;

    public Adapter_Sach(List<Sach> list, Context context) {
        this.list = list;
        this.context = context;
        sachDAO = new SachDAO(context);
    }

    @NonNull
    @Override
    public viewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new viewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoder holder, @SuppressLint("RecyclerView") int position) {
        holder.idSach.setText(list.get(position).getID_Sach()+"");
        holder.tenSach.setText(list.get(position).getTenSach());
        holder.theLoai.setText(list.get(position).getTenLoai());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreInfo(position);
            }
        });
    }

    private void moreInfo(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_more1,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Button xemThem,suaSach,xoaSach;
        Sach sach = list.get(p);
        xemThem = view.findViewById(R.id.btn_xemChiTiet);
        suaSach = view.findViewById(R.id.btn_suaSach);
        xoaSach = view.findViewById(R.id.btn_xoaSach);

        xoaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSach(sach);
                dialog.dismiss();
            }
        });
        suaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaSach(p);
                dialog.dismiss();
            }
        });

        xemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xemThem(sach);
                dialog.dismiss();
            }
        });


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

    private void suaSach(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null, false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText tenSach, tacGia, ngayXuatBan, gia;
        Spinner spinner;
        Button sua;
        TextView title;

        tenSach = view.findViewById(R.id.edt_tenSach);
        title = view.findViewById(R.id.tv_title_sach);
        tacGia = view.findViewById(R.id.edt_tacGia);
        ngayXuatBan = view.findViewById(R.id.edt_ngayXuatban);
        gia = view.findViewById(R.id.edt_gia);
        spinner = view.findViewById(R.id.sp_loaiSach);
        sua = view.findViewById(R.id.btn_add_sach);

        Button Huy = view.findViewById(R.id.btn_Huy_sach);
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
        List<LoaiSach> loaiSachList = loaiSachDAO.getAll();
        Adapter_theLoai adapter_theLoai = new Adapter_theLoai(context, loaiSachList);
        spinner.setAdapter(adapter_theLoai);
        Sach sach = list.get(p);
        title.setText("Sửa sách");
        sua.setText("Sửa");
        tenSach.setText(sach.getTenSach());
        tacGia.setText(sach.getTacGia());
        gia.setText(""+sach.getGia());
        ngayXuatBan.setText(sach.getNgayXuatBan());
        for (int i =0 ; i<loaiSachList.size();i++){
            if(loaiSachList.get(i).getID_LoaiSach()==sach.getID_Loai()){
                spinner.setSelection(i);
                break;
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sach.setID_Loai(loaiSachList.get(position).getID_LoaiSach());
                sach.setTenLoai(loaiSachList.get(position).getTenLoai());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sach.setID_Loai(loaiSachList.get(0).getID_LoaiSach());
                sach.setTenLoai(loaiSachList.get(0).getTenLoai());
            }
        });
        ngayXuatBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNgay(ngayXuatBan);
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tenSach.getText().toString().isEmpty() && !tacGia.getText().toString().isEmpty() && !ngayXuatBan.getText().toString().isEmpty() && !gia.getText().toString().isEmpty()) {
                    sach.setTenSach(tenSach.getText().toString().trim());
                    sach.setTacGia(tacGia.getText().toString().trim());
                    sach.setGia(Integer.parseInt(gia.getText().toString().trim()));
                    sach.setNgayXuatBan(ngayXuatBan.getText().toString().trim());
                    if(sachDAO.updateSach(sach)>0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void getNgay(EditText editText) {
        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        DatePickerDialog bangLich = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
            }
        }, nam, thang, ngay);
        bangLich.show();
    }
    private void xoaSach(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có muốn xóa loại này ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(sachDAO.xoaSach(sach)>0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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

    private void loadData() {
        list.clear();
        list.addAll(sachDAO.getAll());
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHoder extends RecyclerView.ViewHolder {
        TextView idSach, tenSach, theLoai;
        ImageView more;

        public viewHoder(@NonNull View itemView) {
            super(itemView);
            idSach = itemView.findViewById(R.id.tv_masach);
            tenSach = itemView.findViewById(R.id.tv_tenSach);
            theLoai = itemView.findViewById(R.id.tv_theLoai);
            more = itemView.findViewById(R.id.iv_menuThem);
        }
    }
}
