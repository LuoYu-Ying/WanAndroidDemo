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
    private ArrayList<String> linkList = new ArrayList<>();
    private List<WxArticleShowBean> articleList = new ArrayList<WxArticleShowBean>();
    private WxArticleRecyclerViewAdapter adapter;

    private int curPages, authorId;

    public WxArticleFragment() {
        // Required empty public constructor
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tl_wx_author);
        recyclerView = view.findViewById(R.id.rv_wx_article);
        adapter = new WxArticleRecyclerViewAdapter(articleList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: onCreateSuccess");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wx_article, container, false);
        initView(view);
        presenter.getContract().requestWxAuthor();
        adapter.setOnItemClickListener(new WxArticleRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkList.get(position)));
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    protected WxArticlePresenter getPresenter() {
        return new WxArticlePresenter();
    }

    public void loadMore() {
        curPages++;
        presenter.getContract().requestWxArticle(authorId, curPages);
    }

    @Override
    protected WxArticleContract.View getContract() {
        return new WxArticleContract.View<List<WxAuthorBean.Author>, List<WxArticleBean.Data.Article>>() {
            @Override
            public void handlerWxAuthor(List<WxAuthorBean.Author> authors) {
                Log.d(TAG, "handlerWxAuthor: showAuthorSuccess");
                idList.clear();
                for (WxAuthorBean.Author author : authors) {
                    tabLayout.addTab(tabLayout.newTab().setText(author.getName()));
                    idList.add(author.getId());
                }
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        authorId = idList.get(position);
                        curPages = 0;
                        presenter.getContract().requestWxArticle(authorId, curPages);
                        Log.d(TAG, "onTabSelected: onTabSelectedSuccess");
//                        Toast.makeText(getContext(), "id = " + authorId + "\ncurPage = " + curPages, Toast.LENGTH_SHORT).show();
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
            public void handlerWxArticle(List<WxArticleBean.Data.Article> articles) {
                if (curPages == 0) {
                    articleList.clear();
                    linkList.clear();
                }
                if (articles.size() == 0) {
                    Toast.makeText(getActivity(), "没有更多内容了 QAQ", Toast.LENGTH_SHORT).show();
                    return ;
                }
                for (WxArticleBean.Data.Article article : articles) {
                    articleList.add(new WxArticleShowBean(article.getTitle(), article.getNiceDate()));
                    linkList.add(article.getLink());
                }
                adapter.notifyDataSetChanged();
            }
        };
    }
}