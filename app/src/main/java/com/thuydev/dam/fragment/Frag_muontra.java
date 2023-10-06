package com.thuydev.dam.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.Activity_Main;
import com.thuydev.dam.R;
import com.thuydev.dam.adapter.Adapter_PhieuMuon;
import com.thuydev.dam.adapter.Adapter_sach_Phieu;
import com.thuydev.dam.adapter.Adapter_thanhvien_Phieu;
import com.thuydev.dam.dao.PhieuMuonDAO;
import com.thuydev.dam.dao.SachDAO;
import com.thuydev.dam.dao.ThanhVienDAO;
import com.thuydev.dam.model.PhieuMuon;
import com.thuydev.dam.model.Sach;
import com.thuydev.dam.model.ThanhVien;

import java.util.Calendar;
import java.util.List;

public class Frag_muontra extends Fragment {
RecyclerView rc_list;
ImageButton them;
List<PhieuMuon> list;
Adapter_PhieuMuon adapter_phieuMuon;
PhieuMuonDAO phieuMuonDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_muontra,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_listMuonTra);
        them = view.findViewById(R.id.ibtn_themPhieu);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getAll();
        adapter_phieuMuon = new Adapter_PhieuMuon(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_list.setAdapter(adapter_phieuMuon);
        rc_list.setLayoutManager(manager);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDATA();
            }
        });



    }

    private void ThemDATA() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_phieumuon,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        Spinner thanhVien,Sach;
        EditText ngayMuon,ngayTra;
        CheckBox TrangThai;
        Button Them,Huy;

        thanhVien = view.findViewById(R.id.sp_tenNguoiMuon);
        Sach = view.findViewById(R.id.sp_tenSachMuon);
        ngayMuon = view.findViewById(R.id.edt_ngayMuon);
        ngayTra = view.findViewById(R.id.edt_ngayTra);
        TrangThai = view.findViewById(R.id.cbk_trasach);
        TrangThai.setVisibility(View.GONE);
        Them = view.findViewById(R.id.btn_phieuMuon);
        Huy = view.findViewById(R.id.btn_HuyphieuMuon);
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        SachDAO sachDAO = new SachDAO(getContext());
        List<com.thuydev.dam.model.Sach> listSach  = sachDAO.getAll();
        List<ThanhVien> listThanhVien = thanhVienDAO.getAll();
        Adapter_sach_Phieu adapter_sach_phieu = new Adapter_sach_Phieu(getContext(),listSach);
        Adapter_thanhvien_Phieu adapter_thanhvien_phieu = new Adapter_thanhvien_Phieu(getContext(),listThanhVien);
        thanhVien.setAdapter(adapter_thanhvien_phieu);
        Sach.setAdapter(adapter_sach_phieu);
        Activity_Main activity_main = (Activity_Main) getActivity();
        PhieuMuon phieuMuon = new PhieuMuon();
        phieuMuon.setID_ThuTHu(activity_main.GuiThuThu().getTenNguoiDung());

        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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

        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ngayMuon.getText().toString().isEmpty()&&!ngayTra.getText().toString().isEmpty()){
                    phieuMuon.setNgayMuon(ngayMuon.getText().toString().trim());
                    phieuMuon.setNgayTra(ngayTra.getText().toString().trim());
                    if(phieuMuonDAO.addPhieuMuon(phieuMuon)>0){
                        Toast.makeText(getContext(), "Thêm phiếu thành công", Toast.LENGTH_SHORT).show();
                        reload();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(activity_main, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload() {
        list.clear();
        list.addAll(phieuMuonDAO.getAll());
        adapter_phieuMuon.notifyDataSetChanged();
    }

    private void getNgay(EditText editText) {
        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        DatePickerDialog bangLich = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
            }
        }, nam, thang, ngay);
        bangLich.show();
    }

}
