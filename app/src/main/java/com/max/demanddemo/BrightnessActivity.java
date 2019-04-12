package com.max.demanddemo;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.max.annotations.PageInfo;
import com.max.baselib.PageKeys;

/**
 * <p>Created by shixin on 2018/12/27.
 */
@PageInfo(alias = PageKeys.APP_BRIGHTNESS)
public class BrightnessActivity extends AppCompatActivity {
    public static int MAX_BRIGHTNESS_VALUE = 255;
    public static int MAX_VOLUME_VALUE = 15;
    public static int MUTE_VOLUME_VALUE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        initView();
    }

    private void initView() {
        SeekBar brightnessSeekBar = findViewById(R.id.brightness_seek_bar);
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

        SeekBar volumeSeekBar = findViewById(R.id.volume_seek_bar);
        volumeSeekBar.setMax(MAX_VOLUME_VALUE);
        int volume = getVolume(this);
        volumeSeekBar.setProgress(volume);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setVolume(BrightnessActivity.this, progress);
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

    /**
     * 获取系统音量
     */
    public static int getVolume(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager == null) {
            return MUTE_VOLUME_VALUE;
        }
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 设置系统音量
     */
    public static void setVolume(Context context, int volume) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager == null) {
            return;
        }
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_brightness:
                Toast.makeText(this, "brightness "+getBrightness(this), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_get_volume:
                Toast.makeText(this, "brightness "+getVolume(this), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
