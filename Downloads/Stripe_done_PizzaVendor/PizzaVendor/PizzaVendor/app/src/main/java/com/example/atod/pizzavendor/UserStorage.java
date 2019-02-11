package com.example.atod.pizzavendor;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.SharedPreferences;

public class UserStorage {

    public static final String SP_NAME  = "userDetails";
    SharedPreferences userLocalDataBase;

    public UserStorage(Context context) {
        userLocalDataBase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.userName);
        spEditor.putString("password", user.password);
        spEditor.putString("credit_card", user.creditCard);
        spEditor.putString("address", user.address);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String name = userLocalDataBase.getString("name", "");
        String username = userLocalDataBase.getString("username", "");
        String password = userLocalDataBase.getString("password", "");
        String address = userLocalDataBase.getString("address", "");
        String creditCard = userLocalDataBase.getString("credit_card", "");

        User storedUser = new User(name, username, password, address, creditCard);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.clear();
    }





}
