package com.thuydev.dam.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.R;
import com.thuydev.dam.adapter.Adapter_thongke;
import com.thuydev.dam.dao.PhieuMuonDAO;
import com.thuydev.dam.model.PhieuMuon;

import java.util.Calendar;
import java.util.List;

public class Frag_thongke extends Fragment {
    RecyclerView rc_list;
    EditText tuNgay, denNgay;
    Button thongKe;
    TextView doanhThu;
    PhieuMuonDAO phieuMuonDAO;
    List<PhieuMuon> list;
    Adapter_thongke adapter_thongke;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_thongke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_thongke);
        tuNgay = view.findViewById(R.id.edt_tuNgay);
        denNgay = view.findViewById(R.id.edt_denNgay);
        thongKe = view.findViewById(R.id.btn_thongKe);
        doanhThu = view.findViewById(R.id.tv_doanhthu);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getTop10();
        adapter_thongke = new Adapter_thongke(list, getContext());
        rc_list.setAdapter(adapter_thongke);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(manager);
        loadDoanhThu(list);
        tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNgay(tuNgay);
            }
        });
        denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNgay(denNgay);
            }
        });
        thongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongKeSach();
            }
        });


    }

    private void loadDoanhThu(List<PhieuMuon> list) {
        int tong = 0;
            for (PhieuMuon p : list){
                tong +=(p.getGia()*p.getSoLuongMuon());
            }
        doanhThu.setText(""+tong);
    }

    private void thongKeSach() {
        list.clear();
        list.addAll(phieuMuonDAO.getThongKe(tuNgay.getText().toString(), denNgay.getText().toString()));
        loadDoanhThu(list);
        adapter_thongke.notifyDataSetChanged();
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
