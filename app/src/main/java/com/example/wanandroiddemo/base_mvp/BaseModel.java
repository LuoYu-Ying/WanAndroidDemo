package com.example.wanandroiddemo.base_mvp;

public abstract class BaseModel<P extends BasePresenter, CONTRACT> {
    protected P presenter;

    public BaseModel(P p) {
        this.presenter = p;
    }

    protected abstract CONTRACT getContract();
}

