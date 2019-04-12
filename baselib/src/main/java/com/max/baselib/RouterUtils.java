package com.max.baselib;

import android.content.Intent;

import java.lang.reflect.Method;

/**
 * <p>Created by shixin on 2019/4/12.
 */
public class RouterUtils {
    public static <T> T find(Class<T> cls, Intent intent, String key) {
        try {
            if (cls == int.class) {
                Method method_1 = Intent.class.getMethod("getIntExtra", String.class, int.class);
                // 使用cls.cast抛异常：java.lang.ClassCastException: Cannot cast java.lang.Integer to int
                return (T) method_1.invoke(intent, key, 0);
            } else {
                Method method_2 = Intent.class.getMethod("getStringExtra", String.class);
                return cls.cast(method_2.invoke(intent, key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
