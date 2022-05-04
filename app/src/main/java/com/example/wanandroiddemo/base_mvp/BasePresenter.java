package com.example.wanandroiddemo.base_mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView, CONTRACT> {
    protected M model;
    protected V v;

    public BasePresenter() {
        this.model = getModel();
    }

    protected void bindView(V v) {
        this.v = v;
    }

    protected V getView() {
        return v;
    }

    protected abstract CONTRACT getContract();
    protected abstract M getModel();

}