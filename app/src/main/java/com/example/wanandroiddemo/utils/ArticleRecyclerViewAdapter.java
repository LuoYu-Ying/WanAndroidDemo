package com.example.wanandroiddemo.utils;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.model.ArticleItemBean;
import com.example.wanandroiddemo.wxarticle.WxArticleFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 更加通用的 Adapter。
 按键监听 请使用 adapter.setOnItemClickLister() 方法。
 需要实现四个方法：
 onItemClick() : 卡片点击事件
 addFavArticle() : 添加收藏
 deleteFavArticle() : 取消收藏
 loadMore() : 加载更多
 */

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<ArticleItemBean> list;
    private OnItemClickListener onItemClickListener;
    private Map<Integer, Boolean> map = new HashMap<>();

    public ArticleRecyclerViewAdapter(List<ArticleItemBean> list) {
        this.list = list;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(view, (int)view.getTag());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void addFavArticle(int id);
        void deleteArticle(int id);
        void loadMore();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(list.get(position), position);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, date, author;
        private ImageView fav;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_article_title);
            date = itemView.findViewById(R.id.tv_article_date);
            author = itemView.findViewById(R.id.tv_article_author);
            fav = itemView.findViewById(R.id.iv_article_fav);
        }

        public void setData(ArticleItemBean itemView, int position) {
            this.position = position;
            title.setText(itemView.getTitle());
            date.setText(itemView.getDate());
            author.setText(itemView.getAuthor());
            if (itemView.isFav()) {
                fav.setImageResource(R.drawable.ic_baseline_favorite_24);
            } else {
                fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            }
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = list.get(position).getId();
                    boolean isFav = list.get(position).isFav();
                    if (isFav) {
                        fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        onItemClickListener.deleteArticle(id);
                        list.get(position).setFav(false);
                        Toast.makeText(MyApp.context, "取消成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        onItemClickListener.addFavArticle(id);

                        fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                        list.get(position).setFav(true);
                        Toast.makeText(MyApp.context, "收藏成功！", Toast.LENGTH_SHORT).show();
//                        ToastUtils.show("收藏成功！", 1000);
                    }
                }
            });
            if (position == getItemCount() - 1) {
                onItemClickListener.loadMore();
            }
        }
    }
}
