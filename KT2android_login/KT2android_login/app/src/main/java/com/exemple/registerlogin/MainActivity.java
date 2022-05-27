package com.exemple.registerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView forgotPassword;
    private String email, password;
    private String URL = "http://192.168.233.148/login/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = password = "";
        etEmail = findViewById(R.id.inputEmail);
        etPassword = findViewById(R.id.inutPassword);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        forgotPassword.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "Function is being updated", Toast.LENGTH_SHORT).show();
        });
        
    }

    public void login(View view) {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                Log.d("res", response);
                if (response.equals("success")) {
                    Intent intent = new Intent(MainActivity.this, adminActivity.class);
                    startActivity(intent);
                    finish();
                }else if(response.equals("user")){
                    Toast.makeText(MainActivity.this, "Bạn không phải là admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Success.class);
                    startActivity(intent);
                    finish();
                }else if (response.equals("failure")) {
                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Các trường không được để trống!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}