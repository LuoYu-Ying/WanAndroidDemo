package com.example.wanandroiddemo.servcice;

import com.example.wanandroiddemo.model.WxArticleBean;
import com.example.wanandroiddemo.model.WxAuthorBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 添加收藏文章
     */
    @POST("lg/collect/{id}/json")
    Call<WxArticleBean> postFavArticle(@Path("id") int id);

    /**
     * 删除添加的文章
     */
    @POST("lg/uncollect_originId/{id}/json")
    Call<WxArticleBean> cancelFavArticle(@Path("id") int id);
}
