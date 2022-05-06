package com.example.wanandroiddemo.wxarticle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.model.WxArticleShowBean;

import java.util.List;

public class WxArticleRecyclerViewAdapter extends RecyclerView.Adapter<WxArticleRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<WxArticleShowBean> list;
    private OnItemClickListener onItemClickListener;
    private WxArticleFragment fragment;

    public WxArticleRecyclerViewAdapter(List<WxArticleShowBean> list, WxArticleFragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(view, (int)view.getTag());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wx_article_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(list.get(position), position);
        holder.itemView.setTag(position);
        if (position == getItemCount() - 1)
            fragment.loadMore();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, date;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_wx_article_title);
            date = itemView.findViewById(R.id.tv_wx_article_date);
        }

        public void setData(WxArticleShowBean itemView, int position) {
            this.position = position;
            title.setText(itemView.title);
            date.setText(itemView.date);
        }
    }
}
