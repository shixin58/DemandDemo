package com.max.hero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.max.annotations.PageInfo;
import com.max.baselib.PackageNames;
import com.max.baselib.PageKeys;
import com.max.processor.PageInfoGenerated;

/**
 * <p>Created by shixin on 2019/3/23.
 */
@PageInfo(alias = PageKeys.HERO_ENTRY)
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_router:
                intent.setClassName(PackageNames.HERO, PageInfoGenerated.MAP.get(PageKeys.HERO_ROUTER));
                startActivity(intent);
                break;
            case R.id.tv_template:
                intent.setClassName(PackageNames.HERO, PageInfoGenerated.MAP.get(PageKeys.HERO_TEMPLATE));
                intent.putExtra("index", 1);
                intent.putExtra("name", "Come on!");
                startActivity(intent);
                break;
        }
    }
}
