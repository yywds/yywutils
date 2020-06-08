package com.example.utils;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    /**
     * 在Application中获取全局上下文
     */
    private static Context context;//上下文

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    //以后调用此方法获取上下文就行
    public static Context getContext(){
        return context;
    }
}
