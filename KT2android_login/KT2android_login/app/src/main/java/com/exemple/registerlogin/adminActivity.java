package com.exemple.registerlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.exemple.registerlogin.Adapter.userAdapter;
import com.exemple.registerlogin.Models.userModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adminActivity extends AppCompatActivity {
    private String URL = "http://192.168.233.148/login/hienthi.php";
    TextView txtRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txtRegister = (TextView) findViewById(R.id.textRegister);

        RecyclerView view;
        view = (RecyclerView) findViewById(R.id.view);

        ArrayList<userModel> list = new ArrayList<userModel>();
        list.add(new userModel("Admin", "Thử nghiệm",R.drawable.person));


        userAdapter adapter = new userAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        view.setAdapter(adapter);
        view.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(adminActivity.this)
                .setTitle("Exit")
                .setIcon(R.drawable.warning)
                .setMessage("Are you sure you ewan to exit")
                .setPositiveButton("yes", (dialogInterface, i) -> {
                    finish();
                }).setNegativeButton("help", (dialogInterface, i) -> {
            Toast.makeText(this, "Open help Activity", Toast.LENGTH_SHORT).show();
        }).setNegativeButton("no", (dialogInterface, i) -> {
            dialogInterface.cancel();
        }).show();
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.taikhoan:
                startActivity(new Intent(adminActivity.this, Register.class));

                txtRegister.setText("Create new account");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}