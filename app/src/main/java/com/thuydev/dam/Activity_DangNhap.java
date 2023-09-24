package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.thuydev.dam.Fragment.Frag_begin;
import com.thuydev.dam.Fragment.Frag_dangnhap;

public class Activity_DangNhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        new Thread(){
            Frag_begin frag_begin;
            Frag_dangnhap frag_dangnhap;
            @Override
            public void run() {
                super.run();
                frag_dangnhap = new Frag_dangnhap();
                frag_begin = new Frag_begin();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.Frag_dangNhap,frag_begin).commit();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                manager.beginTransaction().replace(R.id.Frag_dangNhap,frag_dangnhap).commit();
            }
        }.start();
    }
}