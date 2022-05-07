package com.example.wanandroiddemo.login;

import com.example.wanandroiddemo.base_mvp.BasePresenter;
import com.example.wanandroiddemo.model.UserInformation;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/7
 */
public class LoginPresenter extends BasePresenter<LoginModel,LoginFragment,LoginContract.Presenter> {

    @Override
    protected LoginContract.Presenter getContract() {
        return new LoginContract.Presenter() {
            @Override
            public void requestLogin(String name, String password) {
                try {
                    getModel().getContract().executeLogin(name,password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseResult(UserInformation userInformation) {
                getView().getContract().handlerUesrInfo(userInformation);
            }
        };
    }

    @Override
    protected LoginModel getModel() {
        return new LoginModel(this);
    }
}
