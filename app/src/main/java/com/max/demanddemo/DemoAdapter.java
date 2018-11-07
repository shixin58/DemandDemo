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
public class DemoAdapter extends RecyclerViewAdapter {
    private List<TopicVideoModel> mList = new ArrayList<>();

    public DemoAdapter(Context context) {
        super(context);
    }

    public void setList(List<TopicVideoModel> list) {
        int oldSize = mList.size();
        mList.clear();
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

        final CellAdapter cellAdapter = new CellAdapter(getContext());
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) celebrityViewHolder.recyclerView.getLayoutParams();
        layoutParams.height = CellAdapter.height;
        celebrityViewHolder.recyclerView.setLayoutParams(layoutParams);
        celebrityViewHolder.recyclerView.setAdapter(cellAdapter);
        cellAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), ""+cellAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        cellAdapter.setList(topicVideoModel.list);
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
        }
    }
}
