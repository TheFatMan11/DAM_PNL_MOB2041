package com.thuydev.dam.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.thuydev.dam.Adapter.Adapter_nguoiDung;
import com.thuydev.dam.DAO.DAO_ThuThu;
import com.thuydev.dam.DTO.DTO_thuTHu;
import com.thuydev.dam.R;

import java.util.List;

public class Frag_taiKhoan extends Fragment {
    RecyclerView rc_list;
    ImageButton add;
    DAO_ThuThu dao_thuThu;
    Adapter_nguoiDung adapter_nguoiDung;
    List<DTO_thuTHu> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlynguoidung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_listNguoiDung);
        add = view.findViewById(R.id.ibtn_add_nguoiDung);
        dao_thuThu = new DAO_ThuThu(getContext());
        list = dao_thuThu.getAll();
        adapter_nguoiDung = new Adapter_nguoiDung(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_list.setAdapter(adapter_nguoiDung);
        rc_list.setLayoutManager(manager);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNguoiDung();
            }
        });


    }

    private void addNguoiDung() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themnguoidung, null, false);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText hoTen, tenDangNhap, matKhau, reMaKhau, diaChi, Email;
        TextInputLayout ThongBao_matKhau, ThongBao_reMatKhau;
        Button them;

        hoTen = view.findViewById(R.id.edt_hoten_nd);
        tenDangNhap = view.findViewById(R.id.edt_tenDangnhap_nd);
        matKhau = view.findViewById(R.id.edt_pass_nd);
        reMaKhau = view.findViewById(R.id.edt_rePass_nd);
        diaChi = view.findViewById(R.id.edt_diaChi_nd);
        Email = view.findViewById(R.id.edt_email_nd);
        ThongBao_matKhau = view.findViewById(R.id.tl_pass_nd);
        ThongBao_reMatKhau = view.findViewById(R.id.tl_repass_nd);
        them = view.findViewById(R.id.btn_add_nguoidung);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hoTen.getText().toString().isEmpty() && !tenDangNhap.getText().toString().isEmpty() && !matKhau.getText().toString().isEmpty() && !reMaKhau.getText().toString().isEmpty() && !diaChi.getText().toString().isEmpty() && !Email.getText().toString().isEmpty()) {
                    if (matKhau.getText().toString().trim().equals(reMaKhau.getText().toString().trim())) {
                        DTO_thuTHu dto_thuTHu = new DTO_thuTHu();
                        dto_thuTHu.setHoTen(hoTen.getText().toString().trim());
                        dto_thuTHu.setTenNguoiDung(tenDangNhap.getText().toString().trim());
                        dto_thuTHu.setMaKhau(matKhau.getText().toString().trim());
                        dto_thuTHu.setDiaChi(diaChi.getText().toString().trim());
                        dto_thuTHu.setEmail(Email.getText().toString().trim()+"@gmail.com");
                        if (dao_thuThu.addThuThu(dto_thuTHu) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao_thuThu.getAll());
                            adapter_nguoiDung.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    } else {
                        ThongBao_reMatKhau.setError("Vui lòng nhập hai mật khẩu trùng nhau");
                    }
                } else {
                    Toast.makeText(getContext(), "Không được để trống ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
