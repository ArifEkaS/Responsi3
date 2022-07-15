package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Notifikasi extends AppCompatActivity {
    EditText user, pass;
    Button logout, kirim;
    String channelnotif = "channelku";
    String channelid = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        final Sharepref sharePrefManager = new Sharepref(this);
        user = findViewById(R.id.etuser1);
        pass = findViewById(R.id.etpass1);
        logout = findViewById(R.id.btnlogout);
        kirim = findViewById(R.id.btnkirim);

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(Notifikasi.this, MainActivity.class);
            sharePrefManager.saveIsLogin(false);
            finishAffinity();
            startActivity(intent);
        });

        kirim.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Notifikasi.class);
            tampilNotifikasi(user.getText().toString()
                    , pass.getText().toString(), intent);
        });

    }

    private void tampilNotifikasi(String s, String s1, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(Notifikasi.this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifikasi.this, channelid);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setContentText(s1)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(channelnotif, "contoh channel", importance);
            builder.setChannelId(channelnotif);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        assert notificationManager != null;
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());

    }
}