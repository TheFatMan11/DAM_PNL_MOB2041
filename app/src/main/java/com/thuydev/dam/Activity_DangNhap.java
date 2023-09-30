package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Handler;

import com.thuydev.dam.fragment.Frag_begin;
import com.thuydev.dam.fragment.Frag_dangnhap;


public class Activity_DangNhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        Loading();

    }

    private void Loading() {
        Frag_begin frag_begin;
        Frag_dangnhap frag_dangnhap;
        frag_dangnhap = new Frag_dangnhap();
        frag_begin = new Frag_begin();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_dangNhap,frag_begin).commit();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.beginTransaction().replace(R.id.Frag_dangNhap,frag_dangnhap).commit();
            }
        }, 3000);
    }
}