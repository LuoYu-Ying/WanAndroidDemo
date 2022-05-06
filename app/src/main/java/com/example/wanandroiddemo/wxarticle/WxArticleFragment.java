package com.example.wanandroiddemo.wxarticle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.base_mvp.BaseView;
import com.example.wanandroiddemo.model.WxArticleBean;
import com.example.wanandroiddemo.model.WxArticleShowBean;
import com.example.wanandroiddemo.model.WxAuthorBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WxArticleFragment extends BaseView<WxArticlePresenter, WxArticleContract.View> {
    private static final String TAG = "WxArticleFragment";
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private ArrayList<Integer> idList = new ArrayList<>();
    private List<WxArticleShowBean> articleList = new ArrayList<WxArticleShowBean>();
    private WxArticleRecyclerViewAdapter adapter;
    private int curPage, authorId, pagesCount;

    public WxArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wx_article, container, false);
        initView(view);

        // 获取作者列表
        presenter.getContract().requestWxAuthor();

        // RecyclerView_item 事件监听
        adapter.setOnItemClickListener(new WxArticleRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String link = articleList.get(position).link;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tl_wx_author);
        recyclerView = view.findViewById(R.id.rv_wx_article);
        adapter = new WxArticleRecyclerViewAdapter(articleList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void showNewTabArticle(int position) {
        authorId = idList.get(position);
        curPage = 0;
        articleList.clear();
        presenter.getContract().requestWxArticle(authorId, curPage);
    }

    // 判断并显示下方提示内容
    private boolean showLoadHint(int currentPage) {
        if (currentPage == 0) {
        } else if (currentPage >= pagesCount - 1) {
            Toast.makeText(getActivity(), "没有更多内容了~ QAQ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(getContext(), "正在加载中~ ^_^", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    // 加载同作者更多文章
    public void loadMore() {
        curPage++;
        showLoadHint(curPage);
        presenter.getContract().requestWxArticle(authorId, curPage);
    }

    @Override
    protected WxArticleContract.View getContract() {
        return new WxArticleContract.View<List<WxAuthorBean.Author>, List<WxArticleBean.Data.Article>>() {
            @Override
            public void handlerWxAuthor(List<WxAuthorBean.Author> authors) {
                idList.clear();
                for (WxAuthorBean.Author author : authors) {
                    tabLayout.addTab(tabLayout.newTab().setText(author.getName()));
                    idList.add(author.getId());
                }
                // 获取作者列表时，显示第一个作者的文章
                showNewTabArticle(0);
                // TabLayout 按键监听
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        if (authorId == idList.get(position)) return ;
                        showNewTabArticle(position);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            @Override
            public void handlerWxArticle(int totalPages, List<WxArticleBean.Data.Article> articles) {
                // 获取该作者的文章总页数
                pagesCount = totalPages;
                if (!showLoadHint(curPage)) {
                    return ;
                }
                for (WxArticleBean.Data.Article article : articles) {
                    articleList.add(new WxArticleShowBean(article.getTitle(), article.getNiceDate(), article.getLink()));
                }
                adapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    protected WxArticlePresenter getPresenter() {
        return new WxArticlePresenter();
    }
}