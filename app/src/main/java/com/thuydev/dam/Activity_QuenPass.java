package com.thuydev.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.thuydev.dam.model.thuTHu;
import com.thuydev.dam.fragment.Frag_QuenPass;

public class Activity_QuenPass extends AppCompatActivity {
thuTHu _thuTHu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_pass);
        Frag_QuenPass frag_quenPass = new Frag_QuenPass();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_quenPass,frag_quenPass).commit();
    }

    public void getData(thuTHu _thuTHu){
        this._thuTHu = _thuTHu;
    }
    public thuTHu returnData(){
        return _thuTHu;
    }
}