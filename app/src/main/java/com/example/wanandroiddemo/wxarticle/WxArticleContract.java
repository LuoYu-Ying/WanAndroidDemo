package com.example.wanandroiddemo.wxarticle;

import com.example.wanandroiddemo.model.WxArticleBean;
import com.example.wanandroiddemo.model.WxAuthorBean;

import java.util.List;

public interface WxArticleContract {
    interface Model {
        void downloadWxAuthor();
        void downloadWxArticle(int id, int page);
        void addFavArticle(int articleId);
        void deleteFavArticle(int articleId);
    }

    interface View<T extends List<WxAuthorBean.Author>, P extends List<WxArticleBean.Data.Article>> {
        void handlerWxAuthor(T t);
        void handlerWxArticle(int totalPages, P p);
    }

    interface Presenter<T extends List<WxAuthorBean.Author>, P extends List<WxArticleBean.Data.Article>> {
        void requestWxAuthor();
        void requestWxArticle(int id, int page);
        void addFavArticle(int articleId);
        void deleteFavArticle(int articleId);
        void returnWxAuthor(T t);
        void returnWxArticle(int totalPages, P p);
    }
}
