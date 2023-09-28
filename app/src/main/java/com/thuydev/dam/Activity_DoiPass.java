package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.thuydev.dam.DTO.DTO_thuTHu;
import com.thuydev.dam.Fragment.Frag_doiPass;
import com.thuydev.dam.Fragment.Frag_thongke;

public class Activity_DoiPass extends AppCompatActivity {
    DTO_thuTHu dto_thuTHu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass);
        Intent intent = getIntent();
        dto_thuTHu = (DTO_thuTHu) intent.getSerializableExtra("user");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_doiPass, new Frag_doiPass()).commit();
    }
    public DTO_thuTHu returnData(){
        return dto_thuTHu;
    }
}