package com.max.hero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.max.annotations.PageInfo;
import com.max.annotations.Param;
import com.max.baselib.PageKeys;
import com.max.compiler.Router;

/**
 * <p>Created by shixin on 2019/4/12.
 */
@PageInfo(alias = PageKeys.HERO_ROUTER)
public class RouterActivity extends AppCompatActivity {

    @Param(name = "target")
    String target;

    @Param(name = "crazy")
    int crazy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        Router.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this.getApplicationContext(), "inject: target = "+target+"; crazy = "+crazy, Toast.LENGTH_SHORT).show();
    }
}
