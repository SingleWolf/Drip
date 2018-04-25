package com.walker.code.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walker.drip.R;
import com.walker.drip.bean.Summary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Walker
 * @date on 2018/4/24 0024 下午 15:39
 * @email feitianwumu@163.com
 * @desc 简介适配器
 */
public class SummaryAdapter extends RecyclerView.Adapter {
    private List<Summary> mData;

    public SummaryAdapter(List<Summary> data) {
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_recycler_item_summary, parent,
                false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Summary summary = mData.get(position);
            Log.i("SummaryAdapter:onBindViewHolder","position="+position+"\n");
            ((ItemViewHolder) holder).tvTitle.setText(summary.getTitle());
            Log.i("SummaryAdapter:onBindViewHolder",summary.getTitle()+"\n");
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
