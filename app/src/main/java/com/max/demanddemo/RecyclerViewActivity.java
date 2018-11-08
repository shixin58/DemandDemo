package com.max.demanddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * <p>Created by shixin on 2018/11/6.
 */
public class RecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }

    private void initView() {
        // 总共4步：1、拿到RecyclerView实例，2、设置LayoutManager，3、设置Adapter，4、刷新数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DemoAdapter demoAdapter = new DemoAdapter(this);
        recyclerView.setAdapter(demoAdapter);
        demoAdapter.setList(DemoRepository.getAllTopicVideos());
    }
}
