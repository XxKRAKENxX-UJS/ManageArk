package com.example.manageark;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.manageark.Model.UserModel;


public class SharedPrefManager {
    private static String SHARED_PREF_NAME="ManageARK";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

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

