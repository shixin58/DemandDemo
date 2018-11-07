package com.max.demanddemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapter<V extends RecyclerView.ViewHolder> extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HeaderViewTypeBegin = 1000000;
    public static final int FooterViewTypeBegin = 2000000;

    private int mValidHaderViewType = HeaderViewTypeBegin;
    private int mValidFootViewType = FooterViewTypeBegin;
    protected OnItemClickListener mOnItemClickListener;

    private class ExtraItemViewHolder extends RecyclerView.ViewHolder {
        public ExtraItemViewHolder(View itemView) {
            super(itemView);
            setIsRecyclable(false);
        }
    }

    private class HeaderItem {
        public final int type;
        public final RecyclerView.ViewHolder holder;

        public HeaderItem(View view) {
            this.holder = new ExtraItemViewHolder(view);
            type = getVaildViewType();
        }

        public HeaderItem(RecyclerView.ViewHolder view) {
            this.holder = view;
            type = getVaildViewType();
        }

        protected int getVaildViewType() {
            return mValidHaderViewType++;
        }
    }

    private class FooterItem extends HeaderItem {

        public FooterItem(View view) {
            super(view);
        }

        public FooterItem(RecyclerView.ViewHolder view) {
            super(view);
        }

        @Override
        protected int getVaildViewType() {
            return mValidFootViewType++;
        }
    }

    private View emptyView;

    private final Context context;
    private final List<HeaderItem> headers = new ArrayList<HeaderItem>();
    private final List<FooterItem> footers = new ArrayList<FooterItem>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        emptyView.setVisibility(getCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public abstract int getCount();

    @Override
    public final int getItemCount() {
        int count = headers.size();
        count += getCount();
        count += footers.size();

        if (emptyView != null) {
            emptyView.setVisibility(getCount() == 0 ? View.VISIBLE : View.GONE);
        }

        return count;
    }

    public HeaderItem addHeaderView(View headerView) {
        HeaderItem item = new HeaderItem(headerView);
        addHeaderView(item);
        return item;
    }

    public HeaderItem addHeaderView(RecyclerView.ViewHolder headerView) {
        HeaderItem item = new HeaderItem(headerView);
        addHeaderView(item);
        return item;
    }

    private void addHeaderView(HeaderItem headerView) {
        headers.add(headerView);
        notifyItemInserted(headers.size());
    }

    public void removeHeaderView(HeaderItem headerView) {
        int indexToRemove = headers.indexOf(headerView);
        if (indexToRemove >= 0) {
            headers.remove(indexToRemove);
            notifyItemRemoved(indexToRemove);
        }
    }

    public void removeHeaderView(View headerView) {
        int indexToRemove = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).holder.itemView == headerView) {
                indexToRemove = i;
            }
        }
        if (indexToRemove != -1) {
            headers.remove(indexToRemove);
            notifyItemRemoved(indexToRemove);
        }
    }

    public FooterItem addFooterView(RecyclerView.ViewHolder footerView) {
        FooterItem item = new FooterItem(footerView);
        addFooterView(item);
        return item;
    }

    public FooterItem addFooterView(View footerView) {
        FooterItem item = new FooterItem(footerView);
        addFooterView(item);
        return item;
    }

    private void addFooterView(FooterItem footerView) {
        footers.add(footerView);
        notifyItemInserted(getItemCount());
    }

    public void removeFooterView(FooterItem footerView) {
        int indexToRemove = footers.indexOf(footerView);
        if (indexToRemove >= 0) {
            footers.remove(indexToRemove);
            notifyItemRemoved(headers.size() + getCount() + indexToRemove);
        }
    }

    public void removeFooterView(View footerView) {
        int indexToRemove = -1;
        for (int i = 0; i < footers.size(); i++) {
            if (footers.get(i).holder.itemView == footerView) {
                indexToRemove = i;
            }
        }
        if (indexToRemove != -1) {
            footers.remove(indexToRemove);
            notifyItemRemoved(headers.size() + getCount() + indexToRemove);
        }
    }

    public int getHeaderViewCount() {
        return headers.size();
    }

    public int getFooterViewCount() {
        return footers.size();
    }

    public int getViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public final int getItemViewType(int position) {
        if (position < headers.size()) {
            return headers.get(position).type;
        }
        if (position >= headers.size() + getCount()) {
            // 解决问题【机型】【MTJ】java.lang.IndexOutOfBoundsException: Invalid index 3, size is 1
            int footPos = position - (headers.size() + getCount());
            if (footPos < footers.size()) {
                return footers.get(footPos).type;
            } else {
                return -1;
            }
        }
        return getViewType(position - headers.size());
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (HeaderItem item : headers) {
            if (viewType == item.type) {
                return item.holder;
            }
        }

        for (FooterItem item : footers) {
            if (viewType == item.type) {
                return item.holder;
            }
        }

        return onCreateView(parent, viewType);
    }

    public abstract V onCreateView(ViewGroup parent, int viewType);

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= headers.size() && (position - headers.size()) < getCount()) {
            onBindView((V) holder, position - headers.size());
        }
    }

    public abstract void onBindView(V view, int position);

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}