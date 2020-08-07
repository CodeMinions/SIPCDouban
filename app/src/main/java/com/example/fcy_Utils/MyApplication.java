package com.example.fcy_Utils;

import android.app.Application;
import android.content.Context;


import com.example.fcy_Utils.tool.MyHttpTool;

import org.litepal.LitePal;


public class MyApplication extends Application {
    private MyHttpTool myHttpTool;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(getApplicationContext());
        myHttpTool = new MyHttpTool(getApplicationContext());
    }

    public  Context getContext(){
        return this;
    }

    private static final String TAG = "MyApplication";


    public MyHttpTool getMyHttpTool() {
        return myHttpTool;
    }

}
