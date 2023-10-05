package com.thuydev.dam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.dam.R;
import com.thuydev.dam.adapter.Adapter_top10;
import com.thuydev.dam.dao.PhieuMuonDAO;
import com.thuydev.dam.model.PhieuMuon;

import java.util.List;

public class Frag_top10 extends Fragment {
    RecyclerView rc_list;
    SearchView searchView;
    PhieuMuonDAO phieuMuonDAO;
    List<PhieuMuon> list;
    Adapter_top10 adapter_top10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_topsach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_list = view.findViewById(R.id.rcv_listTop10Sach);
        searchView = view.findViewById(R.id.sv_search_topSach);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getTop10();
        adapter_top10 = new Adapter_top10(list,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_list.setLayoutManager(manager);
        rc_list.setAdapter(adapter_top10);



    }
}
