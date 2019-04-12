package com.max.hero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.max.annotations.PageInfo;
import com.max.baselib.PageKeys;

/**
 * <p>Created by shixin on 2019/4/12.
 */
@PageInfo(alias = PageKeys.HERO_ROUTER)
public class RouterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
    }
}
