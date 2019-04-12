package com.max.baselib;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * <p>Created by shixin on 2019/4/12.
 */
public class Router {

    public static void inject(Activity activity) {
        Class<?> cls = activity.getClass();
        try {
            Class<?> injectCls = Class.forName(cls.getName()+"_InjectParams");
            Method injectMethod = injectCls.getMethod("inject", cls);
            injectMethod.invoke(injectCls, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
