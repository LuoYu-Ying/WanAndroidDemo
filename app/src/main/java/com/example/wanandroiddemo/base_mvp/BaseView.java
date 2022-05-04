package com.example.wanandroiddemo.base_mvp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseView<P extends BasePresenter, CONTRACT> extends Fragment {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.bindView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract P getPresenter();
    protected abstract CONTRACT getContract();
}
