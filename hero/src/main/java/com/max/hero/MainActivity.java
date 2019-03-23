package com.max.hero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.max.annotations.PageInfo;
import com.max.baselib.PageKeys;

/**
 * <p>Created by shixin on 2019/3/23.
 */
@PageInfo(alias = PageKeys.HERO_ENTRY)
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
