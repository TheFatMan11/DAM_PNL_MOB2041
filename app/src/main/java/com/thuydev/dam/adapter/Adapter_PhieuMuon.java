package com.thuydev.dam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.Activity_Main;
import com.thuydev.dam.R;
import com.thuydev.dam.dao.LoaiSachDAO;
import com.thuydev.dam.dao.PhieuMuonDAO;
import com.thuydev.dam.dao.SachDAO;
import com.thuydev.dam.dao.ThanhVienDAO;
import com.thuydev.dam.model.LoaiSach;
import com.thuydev.dam.model.PhieuMuon;
import com.thuydev.dam.model.Sach;
import com.thuydev.dam.model.ThanhVien;

import java.util.Calendar;
import java.util.List;

public class Adapter_PhieuMuon extends RecyclerView.Adapter<Adapter_PhieuMuon.ViewHolder> {
    Context context;
    List<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;


    public Adapter_PhieuMuon(Context context, List<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        phieuMuonDAO = new PhieuMuonDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String mauDo ="#FDC2D0";
        String mauXanh = "#00ED0A";
        int mDo = Color.parseColor(mauDo);
        int mXa = Color.parseColor(mauXanh);
        holder.maPhieu.setText(list.get(position).getSoPhieu() + "");
        holder.tenSach.setText(list.get(position).getTenSach());
        holder.theLoai.setText(list.get(position).getTenLoai());
        if(list.get(position).getTrangThai()==0){
            holder.linearLayout.setBackgroundColor(mDo);
            holder.choPhep.setImageResource(R.drawable.giving);
        }else {
            holder.linearLayout.setBackgroundColor(mXa);
            holder.choPhep.setImageResource(R.drawable.handshake);
        }

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiThem(position);
            }
        });

        holder.choPhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            choPhep(position);
            }
        });

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                giuItem(position);
                return false;
            }
        });
    }

    private void choPhep(int p) {
        PhieuMuon phieuMuon = list.get(p);
        if(phieuMuon.getTrangThai()==0){
            phieuMuon.setTrangThai(1);
            if(phieuMuonDAO.updatePhieuMuon(phieuMuon)>0){
                Toast.makeText(context, "Trả sách thành công", Toast.LENGTH_SHORT).show();
                reload();
            }
        }else {
            Toast.makeText(context, "Sách đã được trả", Toast.LENGTH_SHORT).show();
        }
    }

    private void hienThiThem(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon_morong,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        PhieuMuon phi = list.get(p);
        TextView maPhieu,thanhVien,tenSach,tenTacGia,theLoai,ngayMuon,ngayTra,gia,trangThai1,trangThai2;
        maPhieu = view.findViewById(R.id.tv_maphieuMore);
        thanhVien = view.findViewById(R.id.tv_tenthanhvien_more);
        tenSach = view.findViewById(R.id.tv_tenSachMuon_more);
        tenTacGia = view.findViewById(R.id.tv_tacGiaMuon_more);
        theLoai = view.findViewById(R.id.tv_loaiMuon_more);
        ngayMuon = view.findViewById(R.id.tv_NgayMuon_more);
        ngayTra = view.findViewById(R.id.tv_Ngaytra_more);
        gia = view.findViewById(R.id.tv_giaMuon_more);
        trangThai1 = view.findViewById(R.id.trangThai);
        trangThai2 = view.findViewById(R.id.tv_status_more);

        maPhieu.setText(phi.getSoPhieu()+"");
        thanhVien.setText(phi.getHoTen());
        tenSach.setText(phi.getTenSach());
        tenTacGia.setText(phi.getTacGia());
        theLoai.setText(phi.getTenLoai());
        ngayMuon.setText(phi.getNgayMuon());
        ngayTra.setText(phi.getNgayTra());
        gia.setText(phi.getGia()+"");

        String mauDo ="#FF0000";
        String mauXanh = "#00ED0A";
        int mDo = Color.parseColor(mauDo);
        int mXa = Color.parseColor(mauXanh);
        if(phi.getTrangThai()==0){
            trangThai1.setTextColor(mDo);
            trangThai2.setTextColor(mDo);
            trangThai2.setText("Chưa trả");
        }else {
            trangThai1.setTextColor(mXa);
            trangThai2.setTextColor(mXa);
            trangThai2.setText("Đã trả");
        }

    }

    private void giuItem(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_more2,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Button sua,xoa;
        sua = view.findViewById(R.id.btn_suaLoaiSach);
        xoa = view.findViewById(R.id.btn_xoaLoaiSach);
        sua.setText("Sửa phiếu");
        xoa.setText("Xóa phiếu");

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                suaPhieu(p);
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                xoaPhieu(p);
            }
        });
    }

    private void xoaPhieu(int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có muốn xóa phiếu này ?");
        PhieuMuon phieuMuon = list.get(p);
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(phieuMuonDAO.xoaPhieuMuon(phieuMuon)>0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    reload();
                    dialog.dismiss();
                }
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void suaPhieu(int p) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_phieumuon,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        Spinner thanhVien,Sach;
        EditText ngayMuon,ngayTra;
        CheckBox TrangThai;
        Button Sua;

        thanhVien = view.findViewById(R.id.sp_tenNguoiMuon);
        Sach = view.findViewById(R.id.sp_tenSachMuon);
        ngayMuon = view.findViewById(R.id.edt_ngayMuon);
        ngayTra = view.findViewById(R.id.edt_ngayTra);
        TrangThai = view.findViewById(R.id.cbk_trasach);
        TrangThai.setVisibility(View.GONE);
        Sua = view.findViewById(R.id.btn_phieuMuon);
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        SachDAO sachDAO = new SachDAO(context);
        TextView title = view.findViewById(R.id.tv_title_phieuMuon);
        Button Huy = view.findViewById(R.id.btn_HuyphieuMuon);
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        title.setText("Sửa phiếu mượn");
        Sua.setText("Sửa");
        List<com.thuydev.dam.model.Sach> listSach  = sachDAO.getAll();
        List<ThanhVien> listThanhVien = thanhVienDAO.getAll();
        Adapter_sach_Phieu adapter_sach_phieu = new Adapter_sach_Phieu(context,listSach);
        Adapter_thanhvien_Phieu adapter_thanhvien_phieu = new Adapter_thanhvien_Phieu(context,listThanhVien);
        thanhVien.setAdapter(adapter_thanhvien_phieu);
        Sach.setAdapter(adapter_sach_phieu);
        Activity_Main activity_main = (Activity_Main) context;
        PhieuMuon phieuMuon = list.get(p);
        ngayMuon.setText(phieuMuon.getNgayMuon());
        ngayTra.setText(phieuMuon.getNgayTra());
        phieuMuon.setID_ThuTHu(activity_main.GuiThuThu().getTenNguoiDung());
        for (int i = 0;i<listThanhVien.size();i++){
            if(phieuMuon.getID_ThanhVien()==listThanhVien.get(i).getID_ThanhVien()){
                thanhVien.setSelection(i);
                break;
            }
        }
        thanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon.setID_ThanhVien(listThanhVien.get(position).getID_ThanhVien());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                phieuMuon.setID_ThanhVien(listThanhVien.get(0).getID_ThanhVien());
            }
        });
        for (int i = 0;i<listSach.size();i++){
            if(phieuMuon.getID_Sach()==listSach.get(i).getID_Sach()){
                Sach.setSelection(i);
                break;
            }
        }
        Sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon.setID_Sach(listSach.get(position).getID_Sach());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                phieuMuon.setID_Sach(listSach.get(0).getID_Sach());
            }
        });

        ngayMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNgay(ngayMuon);
                phieuMuon.setNgayMuon(ngayMuon.getText().toString().trim());
            }
        });
        ngayTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNgay(ngayTra);
                phieuMuon.setNgayTra(ngayTra.getText().toString().trim());
            }
        });

        Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ngayMuon.getText().toString().isEmpty()&&!ngayTra.getText().toString().isEmpty()){
                    phieuMuon.setNgayMuon(ngayMuon.getText().toString().trim());
                    phieuMuon.setNgayTra(ngayTra.getText().toString().trim());
                    if(phieuMuonDAO.updatePhieuMuon(phieuMuon)>0){
                        Toast.makeText(context, "Sửa phiếu thành công", Toast.LENGTH_SHORT).show();
                        reload();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(activity_main, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload() {
        list.clear();
        list.addAll(phieuMuonDAO.getAll());
        notifyDataSetChanged();
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maPhieu, tenSach, theLoai;
        ImageView choPhep, info;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maPhieu = itemView.findViewById(R.id.tv_maPhieu);
            tenSach = itemView.findViewById(R.id.tv_tenSachPhieu);
            theLoai = itemView.findViewById(R.id.tv_theLoaiPhieu);
            choPhep = itemView.findViewById(R.id.iv_muon_tra);
            info = itemView.findViewById(R.id.iv_info_phieumuon);
            linearLayout = itemView.findViewById(R.id.ll_phieuMuon);
        }
    }
}
