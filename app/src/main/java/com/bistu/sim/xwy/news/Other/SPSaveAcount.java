package com.bistu.sim.xwy.news.Other;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveAcount {
    //保存用户信息
    public static boolean saveUserInfo(Context context, String number, String password){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",number);
        editor.putString("password",password);
        editor.commit();
        return true;
    }
    //读取用户信息
    public static Map<String,String> getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String number = sp.getString("username","");
        String password = sp.getString("password","");

        Map<String,String> userMap = new HashMap<String, String>();
        userMap.put("username",number);
        userMap.put("password",password);
        return userMap;
    }
}
