package com.yuandong.savedirectory;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuandong on 2017/10/20.
 */

public class MyApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this.getApplicationContext();
    }
}
