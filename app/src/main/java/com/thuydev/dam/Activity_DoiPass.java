package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.thuydev.dam.model.thuTHu;
import com.thuydev.dam.fragment.Frag_doiPass;

public class Activity_DoiPass extends AppCompatActivity {
    thuTHu _thuTHu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass);
        Intent intent = getIntent();
        _thuTHu = (thuTHu) intent.getSerializableExtra("user");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_doiPass, new Frag_doiPass()).commit();
    }
    public thuTHu returnData(){
        return _thuTHu;
    }
}