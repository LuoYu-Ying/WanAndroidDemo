package com.example.wanandroiddemo.servcice;

import com.example.wanandroiddemo.model.WxArticleBean;
import com.example.wanandroiddemo.model.WxAuthorBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WxArticleService {
    /**
     * 显示公众号作者列表
     */
    @GET("wxarticle/chapters/json")
    Call<WxAuthorBean> getWxArticleAuthor();

    /**
     * 显示公众号作者文章列表
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Call<WxArticleBean> getWxArticleArticle(@Path("id") int id, @Path("page") int page);
}
