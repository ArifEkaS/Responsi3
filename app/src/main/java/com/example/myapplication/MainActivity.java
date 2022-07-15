package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Sharepref sharePrefManager;
    private EditText etusername, etpassword;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePrefManager = new Sharepref(this);
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnlogin);
        
        login();
    }

    private void login() {
        btnlogin.setOnClickListener(v -> {
            final String username = etusername.getText().toString();
            final String password = etpassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Username dan Password Salah!!!", Toast.LENGTH_SHORT).show();
            } else {
                String spusername = sharePrefManager.getUsername();
                String sppassword = sharePrefManager.getPassword();

                Log.d("username", "user" + username);
                Log.d("password", "pass" + password);

                if (username.equals(spusername) && password.equals(sppassword)) {
                    Intent i = new Intent(MainActivity.this, Profile.class);
                    sharePrefManager.saveIsLogin(true);
                    finishAffinity();
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Username dan Password Salah!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}