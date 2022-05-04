package com.example.wanandroiddemo.wxarticle;

import com.example.wanandroiddemo.base_mvp.BaseModel;
import com.example.wanandroiddemo.base_mvp.BasePresenter;
import com.example.wanandroiddemo.model.WxArticleBean;
import com.example.wanandroiddemo.model.WxAuthorBean;

import java.util.List;

public class WxArticlePresenter extends BasePresenter<WxArticleModel, WxArticleFragment, WxArticleContract.Presenter> {

    @Override
    protected WxArticleContract.Presenter getContract() {
        return new WxArticleContract.Presenter<List<WxAuthorBean.Author>, List<WxArticleBean.Data.Article>>() {
            @Override
            public void requestWxAuthor() {
                model.getContract().downloadWxAuthor();
            }

            @Override
            public void requestWxArticle(int id, int page) {
                model.getContract().downloadWxArticle(id, page);
            }

            @Override
            public void returnWxAuthor(List<WxAuthorBean.Author> authors) {
                getView().getContract().handlerWxAuthor(authors);
            }

            @Override
            public void returnWxArticle(List<WxArticleBean.Data.Article> articles) {
                getView().getContract().handlerWxArticle(articles);
            }
        };
    }

    @Override
    protected WxArticleModel getModel() {
        return new WxArticleModel(this);
    }
}
