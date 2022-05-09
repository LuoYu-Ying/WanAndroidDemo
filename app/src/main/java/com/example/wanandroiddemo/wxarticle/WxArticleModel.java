package com.example.wanandroiddemo.wxarticle;

import com.example.wanandroiddemo.base_mvp.BaseModel;
import com.example.wanandroiddemo.model.WxArticleBean;
import com.example.wanandroiddemo.model.WxAuthorBean;
import com.example.wanandroiddemo.servcice.WxArticleService;
import com.example.wanandroiddemo.utils.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WxArticleModel extends BaseModel<WxArticlePresenter, WxArticleContract.Model> {


    public WxArticleModel(WxArticlePresenter wxArticlePresenter) {
        super(wxArticlePresenter);
    }

    @Override
    protected WxArticleContract.Model getContract() {
        WxArticleService service = RetrofitManager.create(WxArticleService.class);

        return new WxArticleContract.Model() {
            @Override
            public void downloadWxAuthor() {
                service.getWxArticleAuthor().enqueue(new Callback<WxAuthorBean>() {
                    @Override
                    public void onResponse(Call<WxAuthorBean> call, Response<WxAuthorBean> response) {
                        List<WxAuthorBean.Author> authors = response.body().getData();
                        presenter.getContract().returnWxAuthor(authors);
                    }

                    @Override
                    public void onFailure(Call<WxAuthorBean> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void downloadWxArticle(int id, int page) {
                service.getWxArticleArticle(id, page).enqueue(new Callback<WxArticleBean>() {
                    @Override
                    public void onResponse(Call<WxArticleBean> call, Response<WxArticleBean> response) {
                        List<WxArticleBean.Data.Article> articles = response.body().getData().getDatas();
                        int pages = response.body().getData().getPageCount();
                        presenter.getContract().returnWxArticle(pages, articles);
                    }

                    @Override
                    public void onFailure(Call<WxArticleBean> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        };
    }


}
