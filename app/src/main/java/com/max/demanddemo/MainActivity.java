package com.max.demanddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * <p>Created by shixin on 2018/11/6.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        // case后跟常量，编译器去重
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_horizontal_scroll:
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_set_brightness:
                intent = new Intent(this, BrightnessActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
