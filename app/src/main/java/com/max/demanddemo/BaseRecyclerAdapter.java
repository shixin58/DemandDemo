package com.max.demanddemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 将ListView中getView拆分为onCreateViewHolder和onBindViewHolder
 * @see android.widget.Adapter#getView(int, View, ViewGroup)
 * @see RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
 * @see RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)
 * <p>Created by shixin on 2018/4/19.
 */
public abstract class BaseRecyclerAdapter<V extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<V> implements View.OnClickListener {
    protected OnItemClickListener mOnItemClickListener;
    protected WeakReference<RecyclerView> mRecyclerView;
    protected List<T> mList = new ArrayList<>();

    private List<View> mHeaderViews = new LinkedList<>();
    private List<View> mFooterViews = new LinkedList<>();

    public void addHeaderView(View view) {
        mHeaderViews.add(view);
    }

    public int getHeaderViewCount() {
        return mHeaderViews.size();
    }

    public void addFooterView(View view) {
        mFooterViews.add(view);
    }

    public int getFooterViewCount() {
        return mFooterViews.size();
    }

    public View getHeaderOrFooter(int viewType) {
        if(viewType>=0) return null;
        if(mHeaderViews.size()>0 && -viewType<=mHeaderViews.size()) {
            return mHeaderViews.get(-viewType-1);
        }else {
            return mFooterViews.get(-viewType-mHeaderViews.size()-1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderViews.size()>0 && position<mHeaderViews.size()) {// header
            return -(position+1);// -1, -2, -3
        }
        if(mFooterViews.size()>0 && getItemCount()-position<=mFooterViews.size()) {// footer
            return -(position-mList.size()+1);// -4, -5, -6
        }
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItem(int position) {
        if(mHeaderViews.size()>0 && position<mHeaderViews.size()) {
            return null;
        }
        if(mFooterViews.size()>0 && getItemCount()-position<=mFooterViews.size()) {
            return null;
        }
        return mList.get(position-mHeaderViews.size());
    }

    @Override
    public int getItemCount() {
        return mList.size()+mHeaderViews.size()+mFooterViews.size();
    }

    abstract public void onBindVH(V holder, int position);

    public abstract V onCreateVH(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        if(mOnItemClickListener!=null) {
            // 让BaseRecyclerAdapter实现OnClickListener，少生成对象
            holder.itemView.setOnClickListener(this);
        }
        onBindVH(holder, position);
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mRecyclerView == null)
            mRecyclerView = new WeakReference<>((RecyclerView) parent);
        return onCreateVH(parent, viewType);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        RecyclerView recyclerView = mRecyclerView.get();
        if (recyclerView != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            mOnItemClickListener.onItemClick(v, position);
        }
    }

    public static class HeaderOrFooterViewHolder extends RecyclerView.ViewHolder {
        public HeaderOrFooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
