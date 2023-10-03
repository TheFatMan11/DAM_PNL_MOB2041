package com.thuydev.dam.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thuydev.dam.Activity_QuenPass;
import com.thuydev.dam.dao.ThuThuDAO;
import com.thuydev.dam.model.thuTHu;
import com.thuydev.dam.R;

import java.util.List;

public class Frag_QuenPass extends Fragment {
    Button xacThuc ;
    TextInputEditText tenDangNhap,Email;
    TextInputLayout thongBaoEmail,thongBaoTen;
    thuTHu _thuTHu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quenpass,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xacThuc = view.findViewById(R.id.btn_xacThuc);
        tenDangNhap = view.findViewById(R.id.ed_txtUser_quen);
        thongBaoTen = view.findViewById(R.id.in_User_quen);
        Email = view.findViewById(R.id.ed_txtEmail_quen);
        thongBaoEmail = view.findViewById(R.id.in_email_quen);

        xacThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckData();
            }
        });
    }

    private void CheckData() {
        if(!tenDangNhap.getText().toString().trim().isEmpty()&&!Email.getText().toString().trim().isEmpty()){
            ThuThuDAO _thuThuDAO = new ThuThuDAO(getContext());
            List<thuTHu> list = _thuThuDAO.getQuenPass(tenDangNhap.getText().toString().trim(),Email.getText().toString().trim());
            if(list.size()>0){
                Frag_doiPass frag_doiPass = new Frag_doiPass();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frag_quenPass,frag_doiPass).commit();
                _thuTHu = list.get(0);
                Activity_QuenPass activity_quenPass = (Activity_QuenPass) getActivity();
                activity_quenPass.getData(_thuTHu);
            }else {
                thongBaoTen.setError("Tên hoặc email không đúng");
                thongBaoEmail.setError("Tên hoặc email không đúng");
                resetThongBao();
            }
        }else if (tenDangNhap.getText().toString().trim().isEmpty()&&!Email.getText().toString().trim().isEmpty()){
            thongBaoTen.setError("Tên không được để trống");
            resetThongBao();
        }else if (!tenDangNhap.getText().toString().trim().isEmpty()&&Email.getText().toString().trim().isEmpty()){
            thongBaoEmail.setError("Email không được để trống");
            resetThongBao();
        }else {
            thongBaoTen.setError("Tên không được để trống");
            thongBaoEmail.setError("Email không được để trống");
          resetThongBao();

        }
    }

    private void resetThongBao() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                thongBaoTen.setError(null);
                thongBaoEmail.setError(null);
            }
        },3000);
    }
}
