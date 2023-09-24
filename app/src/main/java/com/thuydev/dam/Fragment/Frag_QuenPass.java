package com.thuydev.dam.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thuydev.dam.R;

public class Frag_QuenPass extends Fragment {
    Button xacThuc ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quenpass,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xacThuc = view.findViewById(R.id.btn_xacThuc);

        xacThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_doiPass frag_doiPass = new Frag_doiPass();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frag_quenPass,frag_doiPass).commit();
            }
        });
    }
}
