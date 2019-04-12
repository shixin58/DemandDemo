package com.max.hero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.max.annotations.PageInfo;
import com.max.annotations.Param;
import com.max.baselib.PageKeys;
import com.max.baselib.Router;

/**
 * <p>Created by shixin on 2019/4/12.
 */
@PageInfo(alias = PageKeys.HERO_TEMPLATE)
public class TemplateActivity extends AppCompatActivity {

    @Param(name = "index")
    int index;

    @Param(name = "name")
    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        Router.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this.getApplicationContext(), "inject: index = "+index+"; name = "+name, Toast.LENGTH_SHORT).show();
    }
}
