package com.thuydev.dam.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thuydev.dam.Activity_Main_admin;
import com.thuydev.dam.Activity_QuenPass;
import com.thuydev.dam.DAO.DAO_ThuThu;
import com.thuydev.dam.DTO.DTO_thuTHu;
import com.thuydev.dam.R;

import java.util.List;

public class Frag_dangnhap extends Fragment {
    TextInputEditText tenDangNhap, matKhau;
    TextInputLayout thongBao_ten, thongBao_mk;
    Button dangNhap;
    TextView quenPass;
    CheckBox luu;
    DAO_ThuThu dao_thuThu;
    DTO_thuTHu dto_thuTHu;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dangnhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dangNhap = view.findViewById(R.id.btn_dangNhap);
        tenDangNhap = view.findViewById(R.id.ed_txtUser);
        matKhau = view.findViewById(R.id.ed_txtPass);
        thongBao_mk = view.findViewById(R.id.in_Pass);
        thongBao_ten = view.findViewById(R.id.in_User);
        quenPass = view.findViewById(R.id.tv_quenPass);
        luu = view.findViewById(R.id.cbk_luudangnhap);
        dao_thuThu = new DAO_ThuThu(getContext());
        sharedPreferences = getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("luu",false)){
            tenDangNhap.setText(sharedPreferences.getString("ten",""));
            matKhau.setText(sharedPreferences.getString("matkhau",""));
            luu.setChecked(sharedPreferences.getBoolean("luu",false));
        }

        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangNhapMain();
            }
        });

        quenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_QuenPass.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void luuPass() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ten",tenDangNhap.getText().toString().trim());
        editor.putString("matkhau",matKhau.getText().toString().trim());
        editor.putBoolean("luu",luu.isChecked());
        editor.apply();
    }

    private void dangNhapMain() {
        if (!tenDangNhap.getText().toString().trim().isEmpty() && !matKhau.getText().toString().trim().isEmpty()) {
            List<DTO_thuTHu> list = dao_thuThu.getNDDangNhap(tenDangNhap.getText().toString().trim(), matKhau.getText().toString().trim());
            if (list.size()>0) {
                Toast.makeText(getContext(), "Đăng nhạp thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Activity_Main_admin.class);
                dto_thuTHu = list.get(0);
                intent.putExtra("user",dto_thuTHu);
                getActivity().startActivity(intent);
                luuPass();
            } else {
                thongBao_ten.setError("Sai thông tin");
                thongBao_mk.setError("Sai thông tin");
                resetThongBao();
            }
        } else if (tenDangNhap.getText().toString().trim().isEmpty() && !matKhau.getText().toString().trim().isEmpty()) {
            thongBao_ten.setError("Không được để trống tên tài khoản");
            resetThongBao();
        } else if (!tenDangNhap.getText().toString().trim().isEmpty() && matKhau.getText().toString().trim().isEmpty()) {
            thongBao_mk.setError("Không được để trống mật khẩu");
            resetThongBao();
        } else {
            thongBao_ten.setError("Không được để trống tên tài khoản");
            thongBao_mk.setError("Không được để trống mật khẩu");
            resetThongBao();
        }
    }
    private void resetThongBao() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                thongBao_ten.setError(null);
                thongBao_mk.setError(null);
            }
        },3000);
    }

}
