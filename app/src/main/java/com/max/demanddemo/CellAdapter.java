package com.max.demanddemo;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * <p>Created by shixin on 2018/11/6.
 */
public class CellAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder, TopicVideoModel.VideoModel> {
    private static int width;

    public CellAdapter(Context context) {
        width = (int) (context.getResources().getDisplayMetrics().widthPixels/3.5);
    }

    public void setList(List<TopicVideoModel.VideoModel> list) {
        mList.clear();
        if(list!=null && !list.isEmpty()) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent, false);
        return new CellViewHolder(itemView);
    }

    @Override
    public void onBindVH(RecyclerView.ViewHolder view, int position) {
        CellViewHolder cellViewHolder = (CellViewHolder) view;
        TopicVideoModel.VideoModel videoModel = mList.get(position);
        cellViewHolder.tvName.setText(videoModel.name);
        cellViewHolder.ivThumbnail.setImageResource(videoModel.resId);
    }

    public static class CellViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName;
        public CellViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivThumbnail.getLayoutParams();
            layoutParams.width = width;
            ivThumbnail.setLayoutParams(layoutParams);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
