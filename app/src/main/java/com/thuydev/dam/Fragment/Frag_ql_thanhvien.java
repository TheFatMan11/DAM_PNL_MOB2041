package com.thuydev.dam.Fragment;

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

import com.thuydev.dam.Adapter.Adapter_thanhVien;
import com.thuydev.dam.DAO.DAO_ThanhVien;
import com.thuydev.dam.DTO.DTO_ThanhVien;
import com.thuydev.dam.R;

import java.util.List;

public class Frag_ql_thanhvien extends Fragment {
    RecyclerView rc_list;
    ImageButton addThanhvien;
    List<DTO_ThanhVien>list;
    Adapter_thanhVien adapter_thanhVien;
    DAO_ThanhVien dao_thanhVien;
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
        dao_thanhVien = new DAO_ThanhVien(getContext());
        list = dao_thanhVien.getAll();
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

        EditText ten,diaChi,email;
        Button them ;
        ten=view.findViewById(R.id.edt_hoten);
        diaChi = view.findViewById(R.id.edt_diaChi);
        email = view.findViewById(R.id.edt_email);
        them = view.findViewById(R.id.btn_add_thanhvien);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ten.getText().toString().isEmpty()&&!diaChi.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()){
                    DTO_ThanhVien dto_thanhVien = new DTO_ThanhVien();
                    dto_thanhVien.setHoTen(ten.getText().toString().trim());
                    dto_thanhVien.setDiaChi(diaChi.getText().toString().trim());
                    dto_thanhVien.setEmail(email.getText().toString().trim()+"@gmail.com");
                    if (dao_thanhVien.addThanhNien(dto_thanhVien)>0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dao_thanhVien.getAll());
                        adapter_thanhVien.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }else {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
