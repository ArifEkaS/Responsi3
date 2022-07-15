package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharepref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Sharepref(Context context) {
        sharedPreferences = context.getSharedPreferences("responsi", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getUsername() {
        return sharedPreferences.getString("sp_username","Arifekaaa");
    }
    public String getPassword() {
        return sharedPreferences.getString("sp_password", "0541");
    }

    public void saveIsLogin (Boolean value) {
        editor.putBoolean("sp_islogin", value);
        editor.commit();
    }

    public boolean getIsLogin(){
        return sharedPreferences.getBoolean("sp_Islogin", false);
    }

}
