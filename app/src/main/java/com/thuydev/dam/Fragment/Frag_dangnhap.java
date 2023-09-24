package com.thuydev.dam.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thuydev.dam.Activity_QuenPass;
import com.thuydev.dam.R;

public class Frag_dangnhap extends Fragment {
    TextInputEditText tenDangNhap ,matKhau;
    TextInputLayout thongBao_ten, thongBao_mk;
    Button dangNhap;
    TextView quenPass;
    CheckBox luu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dangnhap,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dangNhap = view.findViewById(R.id.btn_dangNhap);
        tenDangNhap = view.findViewById(R.id.ed_txtUser);
        matKhau = view.findViewById(R.id.ed_txtPass);
        thongBao_ten = view.findViewById(R.id.in_Pass);
        thongBao_mk = view.findViewById(R.id.in_User);
        quenPass =  view.findViewById(R.id.tv_quenPass);
        luu =  view.findViewById(R.id.cbk_luudangnhap);

        if (luu.isChecked()){

        }

        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


}
