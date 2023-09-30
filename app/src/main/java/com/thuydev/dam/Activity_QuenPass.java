package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.thuydev.dam.dto.DTO_thuTHu;
import com.thuydev.dam.fragment.Frag_QuenPass;

public class Activity_QuenPass extends AppCompatActivity {
DTO_thuTHu dto_thuTHu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_pass);
        Frag_QuenPass frag_quenPass = new Frag_QuenPass();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_quenPass,frag_quenPass).commit();
    }

    public void getData(DTO_thuTHu dto_thuTHu){
        this.dto_thuTHu = dto_thuTHu;
    }
    public DTO_thuTHu returnData(){
        return dto_thuTHu;
    }
}