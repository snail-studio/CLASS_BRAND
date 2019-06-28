package com.upu.classbrand.manager;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this.getApplicationContext();
        //CrashHandler.getInstance().init(getmContext());
        //x.Ext.init(this);

        //LogcatHelper.getInstance(this).start();
    }
    public static Context getmContext(){
        return mContext;
    }
}
