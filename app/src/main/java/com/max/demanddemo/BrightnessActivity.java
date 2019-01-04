package com.max.demanddemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * <p>Created by shixin on 2018/12/27.
 */
public class BrightnessActivity extends AppCompatActivity {
    public static int MAX_BRIGHTNESS_VALUE = 255;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        initView();
    }

    private void initView() {
        SeekBar brightnessSeekBar = (SeekBar) findViewById(R.id.brightness_seek_bar);
        brightnessSeekBar.setMax(MAX_BRIGHTNESS_VALUE);
        int brightness = getBrightness(this);
        int brightnessMode = getBrightnessMode(this);
        Log.i("Max", "brightness "+brightness+"; brightness mode "+brightnessMode);
        brightnessSeekBar.setProgress(brightness);
        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBrightness(BrightnessActivity.this, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 设置当前页亮度
     */
    public static void setBrightness(Activity activity, int brightness) {
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.screenBrightness = brightness * 1f / MAX_BRIGHTNESS_VALUE;
        activity.getWindow().setAttributes(layoutParams);
    }

    /**
     * 获取系统亮度
     */
    public static int getBrightness(Context context) {
        int brightness = 0;
        try {
            brightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return brightness;
    }

    /**
     * 获取系统亮度模式
     */
    public static int getBrightnessMode(Context context) {
        int brightnessMode = 0;
        try {
            brightnessMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return brightnessMode;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_brightness:
                Toast.makeText(this, "brightness "+getBrightness(this), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
