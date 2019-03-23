package com.max.hero;

import android.app.Application;

import com.max.processor.PageInfoGenerated;

/**
 * <p>Created by shixin on 2019/3/23.
 */
public class HeroApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PageInfoGenerated.savePages();
    }
}
