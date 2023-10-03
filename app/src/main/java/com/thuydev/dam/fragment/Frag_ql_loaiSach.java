package com.thuydev.dam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.adapter.Adapter_LoaiSach;
import com.thuydev.dam.dao.LoaiSachDAO;
import com.thuydev.dam.model.LoaiSach;
import com.thuydev.dam.R;

import java.util.List;

public class Frag_ql_loaiSach extends Fragment {
    RecyclerView rc_list;
    EditText editText;
    ImageButton add;
    int swichEDT = 0;
    Adapter_LoaiSach adapter_loaiSach;
    List<LoaiSach> list;
    LoaiSachDAO _loaiSachDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlyloaisach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_listLoaiSach);
        editText = view.findViewById(R.id.edt_add_loai);
        add = view.findViewById(R.id.ibtn_add_Loaisach);
        _loaiSachDAO = new LoaiSachDAO(getContext());
        list = _loaiSachDAO.getAll();
        adapter_loaiSach = new Adapter_LoaiSach(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_list.setLayoutManager(manager);
        rc_list.setAdapter(adapter_loaiSach);
        editText.setVisibility(View.GONE);
        guiData();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swichEDT==0){
                    swichEDT=1;
                    editText.setVisibility(View.VISIBLE);
                }else {
                    // thêm loại sách ở đây
                    swichEDT=0;
                    addLoai();
                    editText.setText("");
                    editText.setVisibility(View.GONE);
                }
            }
        });


    }

    private void guiData() {
        adapter_loaiSach.getEdittex(editText);
        adapter_loaiSach.getImageButton(add);
    }

    private void addLoai() {
        LoaiSach _loaiSach = new LoaiSach();
        _loaiSach.setTenLoai(editText.getText().toString().trim());
        if(_loaiSachDAO.addLoaiSach(_loaiSach)>0){
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            list.clear();
            list.addAll(_loaiSachDAO.getAll());
            adapter_loaiSach.notifyDataSetChanged();
        }
    }
}
