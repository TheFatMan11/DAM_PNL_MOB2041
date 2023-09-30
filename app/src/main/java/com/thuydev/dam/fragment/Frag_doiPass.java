package com.thuydev.dam.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thuydev.dam.Activity_DoiPass;
import com.thuydev.dam.Activity_QuenPass;
import com.thuydev.dam.dao.ThuThuDAO;
import com.thuydev.dam.dto.DTO_thuTHu;
import com.thuydev.dam.R;

public class Frag_doiPass extends Fragment {
    ThuThuDAO _thuThuDAO;
    TextInputLayout thongBao_Mk,thongBao_reMk;
    TextInputEditText matKhau,ReMaktKhau;
    Button doiPass;
    DTO_thuTHu dto_thuTHu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_doipass,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _thuThuDAO = new ThuThuDAO(getContext());
        thongBao_Mk = view.findViewById(R.id.in_pass_doi);
        thongBao_reMk = view.findViewById(R.id.in_RePass);
        matKhau = view.findViewById(R.id.ed_txtPass_doi);
        ReMaktKhau = view.findViewById(R.id.ed_txt_RePass);
        doiPass = view.findViewById(R.id.btn_doiPass);
        doiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiMatKhau();
            }
        });
    }
    private void doiMatKhau() {
        if(!matKhau.getText().toString().trim().isEmpty()&&!ReMaktKhau.getText().toString().isEmpty()){
            if(matKhau.getText().toString().trim().equals(ReMaktKhau.getText().toString())){
               try {
                   Activity_QuenPass activity_quenPass = (Activity_QuenPass) getActivity();
                   dto_thuTHu = activity_quenPass.returnData();
                   dto_thuTHu.setMaKhau(matKhau.getText().toString().trim());
                   if(_thuThuDAO.updatePass(dto_thuTHu)>0){
                       Toast.makeText(activity_quenPass, "Đổi thành oông", Toast.LENGTH_SHORT).show();
                       getActivity().finish();
                   }
               }catch (Exception e) {
                   Activity_DoiPass activity_quenPass = (Activity_DoiPass) getActivity();
                   dto_thuTHu = activity_quenPass.returnData();
                   dto_thuTHu.setMaKhau(matKhau.getText().toString().trim());
                   if (_thuThuDAO.updatePass(dto_thuTHu) > 0) {
                       Toast.makeText(activity_quenPass, "Đổi thành oông", Toast.LENGTH_SHORT).show();
                       activity_quenPass.finish();
                   }
               }
            }else {
                thongBao_reMk.setError("Hai mật khẩu phải giống nhau");
                resetThongBao();
            }
        } else if (matKhau.getText().toString().trim().isEmpty()&&!ReMaktKhau.getText().toString().isEmpty()) {
            thongBao_Mk.setError("Không được trống");
            resetThongBao();
        } else if (!matKhau.getText().toString().trim().isEmpty()&&ReMaktKhau.getText().toString().isEmpty()) {
            thongBao_reMk.setError("Không được trống");
            resetThongBao();
        }else {
            thongBao_Mk.setError("Không được trống");
            thongBao_reMk.setError("Không được trống");
            resetThongBao();
        }
    }
    private void resetThongBao() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                thongBao_Mk.setError(null);
                thongBao_reMk.setError(null);
            }
        },3000);
    }
}
