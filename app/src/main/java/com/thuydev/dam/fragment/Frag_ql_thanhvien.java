package com.thuydev.dam.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.adapter.Adapter_thanhVien;
import com.thuydev.dam.dao.ThanhVienDAO;
import com.thuydev.dam.model.ThanhVien;
import com.thuydev.dam.R;

import java.util.List;

public class Frag_ql_thanhvien extends Fragment {
    RecyclerView rc_list;
    ImageButton addThanhvien;
    List<ThanhVien>list;
    Adapter_thanhVien adapter_thanhVien;
    ThanhVienDAO _thanhVienDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlykhachhang,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_listKhachHang);
        addThanhvien = view.findViewById(R.id.ibtn_add_khachHang);
        _thanhVienDAO = new ThanhVienDAO(getContext());
        list = _thanhVienDAO.getAll();
        adapter_thanhVien = new Adapter_thanhVien(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_list.setAdapter(adapter_thanhVien);
        rc_list.setLayoutManager(manager);

        addThanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themthanhvien,null,false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText ten,diaChi,email,cccd;
        Button them ;
        ten=view.findViewById(R.id.edt_hoten);
        diaChi = view.findViewById(R.id.edt_diaChi);
        email = view.findViewById(R.id.edt_email);
        cccd = view.findViewById(R.id.edt_cccd);
        them = view.findViewById(R.id.btn_add_thanhvien);
        Button Huy = view.findViewById(R.id.btn_Huy_thanhvien);
        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ten.getText().toString().isEmpty()&&!diaChi.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()){
                    ThanhVien _thanhVien = new ThanhVien();
                    _thanhVien.setHoTen(ten.getText().toString().trim());
                    _thanhVien.setDiaChi(diaChi.getText().toString().trim());
                    _thanhVien.setEmail(email.getText().toString().trim()+"@gmail.com");
                    _thanhVien.setCccd(cccd.getText().toString().trim());
                    if(_thanhVien.getCccd().length()==12){
                        if (_thanhVienDAO.addThanhNien(_thanhVien)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(_thanhVienDAO.getAll());
                            adapter_thanhVien.notifyDataSetChanged();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Căn cước phải 12 chữ số", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
