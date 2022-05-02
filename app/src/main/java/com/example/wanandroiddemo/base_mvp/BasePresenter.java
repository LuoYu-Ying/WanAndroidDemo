package com.example.wanandroiddemo.base_mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView, CONTRACT> {
    protected M model;
    protected WeakReference<V> weakReference;

    public BasePresenter() {
        this.model = getModel();
    }

    protected void bindView(V v) {
        weakReference = new WeakReference<>(v);
    }

    protected void unbindView() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
            System.gc();
        }
    }

    protected V getView() {
        if (weakReference != null)
            return weakReference.get();
        return null;
    }

    protected abstract CONTRACT getContract();
    protected abstract M getModel();

}