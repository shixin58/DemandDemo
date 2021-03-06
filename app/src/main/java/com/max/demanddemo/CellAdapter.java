package com.max.demanddemo;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
    public static final int IV_WIDTH = (int) (DemandApplication.getInstance().getResources().getDisplayMetrics().widthPixels/3.5);
    public static final int CELL_HEIGHT = IV_WIDTH *3/2 + (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f,
                DemandApplication.getInstance().getResources().getDisplayMetrics()));

    public CellAdapter() {
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
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent, false);
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_video, parent, false);
        return new CellViewHolder(itemView);
    }

    @Override
    public void onBindVH(RecyclerView.ViewHolder view, int position) {
        CellViewHolder cellViewHolder = (CellViewHolder) view;
        TopicVideoModel.VideoModel videoModel = mList.get(position);
        // 绑定数据时设置tag，处理点击事件时获取model
        view.itemView.setTag(videoModel);
        cellViewHolder.tvName.setText(videoModel.name);
        cellViewHolder.ivThumbnail.setImageResource(videoModel.resId);
    }

    public static class CellViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName;
        public CellViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            // 按屏幕宽度设置图片宽，按xml里设置的ratio自动设置高
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivThumbnail.getLayoutParams();
            layoutParams.width = IV_WIDTH;
            ivThumbnail.setLayoutParams(layoutParams);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
