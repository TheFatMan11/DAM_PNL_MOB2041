package com.thuydev.dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.thuydev.dam.dto.DTO_thuTHu;
import com.thuydev.dam.fragment.Frag_muontra;
import com.thuydev.dam.fragment.Frag_ql_loaiSach;
import com.thuydev.dam.fragment.Frag_ql_sach;
import com.thuydev.dam.fragment.Frag_ql_thanhvien;
import com.thuydev.dam.fragment.Frag_taiKhoan;
import com.thuydev.dam.fragment.Frag_thongke;
import com.thuydev.dam.fragment.Frag_top10;

public class Activity_Main extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    View header;
    DTO_thuTHu dto_thuTHu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_main_admin);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.Frag_main, new Frag_thongke()).commit();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        header =navigationView.getHeaderView(0);
        dto_thuTHu = (DTO_thuTHu) intent.getSerializableExtra("user");
        showDataOnHeader(dto_thuTHu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.account);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_Sach){
                    toolbar.setTitle("Quản lý sách");
                    manager.beginTransaction().replace(R.id.Frag_main,new Frag_ql_sach()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.menu_LoaiSach) {
                    toolbar.setTitle("Quản lý loại sách");
                    manager.beginTransaction().replace(R.id.Frag_main,new Frag_ql_loaiSach()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.menu_thanhVien) {
                    toolbar.setTitle("Quản lý thành viên");
                    manager.beginTransaction().replace(R.id.Frag_main,new Frag_ql_thanhvien()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.menu_muonSach) {
                    toolbar.setTitle("Quản lý phiếu mượn");
                    manager.beginTransaction().replace(R.id.Frag_main,new Frag_muontra()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if (item.getItemId()==R.id.menu_thongKe){
                    toolbar.setTitle("Quản lý Thống kê");
                    manager.beginTransaction().replace(R.id.Frag_main,new Frag_thongke()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if (item.getItemId()==R.id.menu_top10){
                    toolbar.setTitle("Quản lý 10 sách mượn");
                    manager.beginTransaction().replace(R.id.Frag_main,new Frag_top10()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.menu_QuanLyTaiKhoan) {
                   if(dto_thuTHu.getChucVu()==1){
                       toolbar.setTitle("Quản lý tài khoản");
                       manager.beginTransaction().replace(R.id.Frag_main,new Frag_taiKhoan()).commit();
                       drawerLayout.closeDrawer(GravityCompat.START);
                   }else {
                       Toast.makeText(Activity_Main.this, "Chức năng này chỉ có Admin được dùng", Toast.LENGTH_SHORT).show();
                   }
                } else if (item.getItemId()==R.id.menu_DoiMatKhau) {
                    toolbar.setTitle("Đổi mật khẩu");
                    Intent intent1 = new Intent(Activity_Main.this, Activity_DoiPass.class);
                    intent1.putExtra("user",dto_thuTHu);
                    startActivity(intent1);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if (item.getItemId()==R.id.menu_dangXuat_admin){
                    dangXuat();
                }else {
                    Toast.makeText(Activity_Main.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

    private void showDataOnHeader(DTO_thuTHu dto_thuTHu) {
        if(dto_thuTHu!=null){
            ImageView avatar = header.findViewById(R.id.imgUsename);
            TextView tenUser = header.findViewById(R.id.lblUsername);
            avatar.setImageResource(R.drawable.user);
            tenUser.setText(dto_thuTHu.getHoTen());
        }
    }

    private void dangXuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn đăng xuất ?");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

}