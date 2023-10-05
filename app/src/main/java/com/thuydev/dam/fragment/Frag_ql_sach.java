package com.thuydev.dam.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.R;
import com.thuydev.dam.adapter.Adapter_Sach;
import com.thuydev.dam.adapter.Adapter_theLoai;
import com.thuydev.dam.dao.LoaiSachDAO;
import com.thuydev.dam.dao.SachDAO;
import com.thuydev.dam.model.LoaiSach;
import com.thuydev.dam.model.Sach;

import java.util.Calendar;
import java.util.List;

public class Frag_ql_sach extends Fragment {
    RecyclerView rc_listl;
    ImageButton addSach;
    SachDAO sachDAO;
    List<Sach> list;
    Adapter_Sach adapter_sach;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlysach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_listl = view.findViewById(R.id.rcv_listSach);
        addSach = view.findViewById(R.id.ibtn_add_sach);
        sachDAO = new SachDAO(getContext());
        list = sachDAO.getAll();
        adapter_sach = new Adapter_Sach(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_listl.setLayoutManager(manager);
        rc_listl.setAdapter(adapter_sach);
        addSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSSach();
            }
        });
    }

    private void addSSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null, false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText tenSach, tacGia, ngayXuatBan, gia;
        Spinner spinner;
        Button add;

        tenSach = view.findViewById(R.id.edt_tenSach);
        tacGia = view.findViewById(R.id.edt_tacGia);
        ngayXuatBan = view.findViewById(R.id.edt_ngayXuatban);
        gia = view.findViewById(R.id.edt_gia);
        spinner = view.findViewById(R.id.sp_loaiSach);
        add = view.findViewById(R.id.btn_add_sach);
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        List<LoaiSach> loaiSachList = loaiSachDAO.getAll();
        Adapter_theLoai adapter_theLoai = new Adapter_theLoai(getContext(), loaiSachList);
        spinner.setAdapter(adapter_theLoai);
        Sach sach = new Sach();
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
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tenSach.getText().toString().isEmpty() && !tacGia.getText().toString().isEmpty() && !ngayXuatBan.getText().toString().isEmpty() && !gia.getText().toString().isEmpty()) {
                    sach.setTenSach(tenSach.getText().toString().trim());
                    sach.setTacGia(tacGia.getText().toString().trim());
                    sach.setGia(Integer.parseInt(gia.getText().toString().trim()));
                    sach.setNgayXuatBan(ngayXuatBan.getText().toString().trim());
                    if(sachDAO.addSach(sach)>0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                       loadData();
                       dialog.dismiss();
                    }
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void loadData() {
        list.clear();
        list.addAll(sachDAO.getAll());
        adapter_sach.notifyDataSetChanged();
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
