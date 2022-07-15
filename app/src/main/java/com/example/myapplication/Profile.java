package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView username, password;
    Button input, hubungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Sharepref sharePrefManager = new Sharepref(this);
        username = findViewById(R.id.tvusername);
        password = findViewById(R.id.tvpassword);
        input = findViewById(R.id.btninput);
        hubungi = findViewById(R.id.btnhub);

        username.setText(sharePrefManager.getUsername());
        password.setText(sharePrefManager.getPassword());

        input.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, Notifikasi.class);
            startActivity(intent);
        });

        hubungi.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_DIAL);
            startActivity(i);
        });
    }
}