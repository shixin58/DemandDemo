package com.max.demanddemo;

import android.app.Application;
import android.content.Context;

import com.max.processor.PageInfoGenerated;

/**
 * <p>Created by shixin on 2018/11/8.
 */
public class DemandApplication extends Application {
    private static DemandApplication mInstance;

    public static DemandApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PageInfoGenerated.savePages();
    }
}
