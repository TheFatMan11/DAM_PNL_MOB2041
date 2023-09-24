package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.thuydev.dam.Fragment.Frag_muontra;

public class Activity_Main_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_main,new Frag_muontra()).commit();
    }
}