package com.example.wanandroiddemo.wxarticle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WxArticleService {
    /**
     * 显示公众号作者列表
     */
    @GET("wxarticle/chapters/json")
    Call<List<WxAuthorBean>> getData();

    /**
     * 显示公众号作者文章列表
     */

}
