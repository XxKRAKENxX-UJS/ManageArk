package com.example.manageark;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.manageark.Model.UserModel;
import com.example.manageark.Model.WeekdayMessMenuModel;


public class SharedPrefManager {
    private static String SHARED_PREF_NAME="ManageARK";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context; }

    public void saveUser(UserModel user){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("user_id",user.getUID());
        editor.putString("user_email",user.getEmail());
        editor.putString("user_full_name",user.getFullName());
        editor.putString("user_messID",user.getMessID());
        editor.putString("user_university",user.getUniversity());
        editor.putString("user_photoUrl",user.getPhotoUrl());
        editor.putString("user_uniqueID",user.getUniqueId());
        editor.putBoolean("user_logged",true);
        editor.apply();

    }

    public void saveTime(WeekdayMessMenuModel time){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("b_time_start",time.getB_time_start());
        editor.putString("d_time_start",time.getD_time_start());
        editor.putString("s_time_start",time.getS_time_start());
        editor.putString("l_time_start",time.getL_time_start());
        editor.putString("b_time_end",time.getB_time_end());
        editor.putString("d_time_end",time.getD_time_end());
        editor.putString("l_time_end",time.getL_time_end());
        editor.putString("s_time_end",time.getS_time_end());
        editor.apply();

    }

    public WeekdayMessMenuModel gettime(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new WeekdayMessMenuModel(
                sharedPreferences.getString("b_time_start",null),
                sharedPreferences.getString("d_time_start",null),
                sharedPreferences.getString("s_time_start",null),
                sharedPreferences.getString("l_time_start",null),
                sharedPreferences.getString("b_time_end",null),
                sharedPreferences.getString("d_time_end",null),
                sharedPreferences.getString("l_time_end",null),
                sharedPreferences.getString("s_time_end",null)

        );
    }


    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("user_logged",false);
    }

    public UserModel getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getString("user_id",null),
                sharedPreferences.getString("user_email",null),
                sharedPreferences.getString("user_full_name",null),
                sharedPreferences.getString("user_messID",null),
                sharedPreferences.getString("user_university",null),
                sharedPreferences.getString("user_photoUrl",null),
                sharedPreferences.getString("user_uniqueID",null)
        );
    }

    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
}

