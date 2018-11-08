package com.max.demanddemo;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created by shixin on 2018/11/6.
 */
public class DemoAdapter extends RecyclerViewAdapter
        implements BaseRecyclerAdapter.OnItemClickListener {
    private List<TopicVideoModel> mList = new ArrayList<>();

    public DemoAdapter(Context context) {
        super(context);
    }

    public void setList(List<TopicVideoModel> list) {
        int oldSize = mList.size();
        mList.clear();
        // 试用RecyclerView的局部刷新
        notifyItemRangeRemoved(0, oldSize);
        if(list!=null && !list.isEmpty()) {
            mList.addAll(list);
            notifyItemRangeInserted(0, list.size());
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new CelebrityViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_demo, parent, false));
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder view, int position) {
        CelebrityViewHolder celebrityViewHolder = (CelebrityViewHolder) view;
        TopicVideoModel topicVideoModel = mList.get(position);
        celebrityViewHolder.textView.setText(topicVideoModel.topicName);

        // notifyDataSetChanged()有时UI不刷新，所以直接重新实例化并设置Adapter
        final CellAdapter cellAdapter = new CellAdapter();
        celebrityViewHolder.recyclerView.setAdapter(cellAdapter);
        // 让DemoAdapter实现OnItemClickListener，少生成对象
        cellAdapter.setOnItemClickListener(this);
        cellAdapter.setList(topicVideoModel.list);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(view.getTag() instanceof TopicVideoModel.VideoModel) {
            TopicVideoModel.VideoModel videoModel = (TopicVideoModel.VideoModel) view.getTag();
            // Toast比Logcat更方便查看
            Toast.makeText(view.getContext(), ""+videoModel, Toast.LENGTH_SHORT).show();
        }
    }

    public static class CelebrityViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public CelebrityViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            // 22.0.0支持包RecyclerView设置wrap_content无效，需手动设置高度
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
            layoutParams.height = CellAdapter.CELL_HEIGHT;
            recyclerView.setLayoutParams(layoutParams);
        }
    }
}
