package com.exemple.registerlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText etName, etEmail, etPassword, etReenterPassword;
    private TextView tvStatus;
    private TextView btnRegister;
    private String URL = "http://192.168.233.148/login/register.php";
    private String name, email, password, reenterPassword;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etReenterPassword = findViewById(R.id.etReenterPassword);
        tvStatus = findViewById(R.id.tvStatus);
        btnRegister = findViewById(R.id.btnRegister);
        name = email = password = reenterPassword = "";
    }

    public void save(View view) {
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        reenterPassword = etReenterPassword.getText().toString().trim();
        if(!password.equals(reenterPassword)){
            Toast.makeText(this, "Mật khẩu nhập không khớp", Toast.LENGTH_SHORT).show(); //in 1 thông báo nhỏ dưới màn hình
        }
        else if(!name.equals("") && !email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                if (response.equals("success")) {
                    tvStatus.setText("Đăng ký thành công !");
                    btnRegister.setClickable(false);
                } else if (response.equals("failure")) {
                    tvStatus.setText("Đăng ký thất bại !");                    }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
