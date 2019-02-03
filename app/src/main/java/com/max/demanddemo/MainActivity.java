package com.max.demanddemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.net.URISyntaxException;

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
            case R.id.tv_invoke_bdvideo:
                String urlString = "bdvideo://invoke?#Intent;action=com.baidu.video.search;S.action=special;S.title=aaa;S.refer=rbbhwdc;S.tomain=0;end";
                String urlString2 = "bdvideo://invoke?#Intent;action=com.baidu.video.channel;end";
                String urlString3 = "#Intent;action=action_oppo_push_to_video_activity;category=bdvideo;end";
                String urlString4 = "#Intent;action=action_current_day_notification_num_insufficient;category=bdvideo;end";
                try {
                    intent = Intent.parseUri(urlString4, 0);
                    intent.setPackage("com.baidu.video");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                /*intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlString));
                intent.setPackage("com.baidu.video");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
                break;
            default:
                break;
        }
    }
}
