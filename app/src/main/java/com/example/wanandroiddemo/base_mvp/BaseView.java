package com.example.wanandroiddemo.base_mvp;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseView<P extends BasePresenter, CONTRACT> extends Activity {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        presenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }

    protected abstract P getPresenter();
    protected abstract CONTRACT getContract();
}
