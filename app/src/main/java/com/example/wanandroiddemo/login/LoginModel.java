package com.example.wanandroiddemo.login;

import android.util.Log;

import com.example.wanandroiddemo.base_mvp.BaseModel;
import com.example.wanandroiddemo.model.UserInformation;
import com.example.wanandroiddemo.servcice.LoginService;
import com.example.wanandroiddemo.servcice.WxArticleService;
import com.example.wanandroiddemo.utils.RetrofitManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/7
 */
public class LoginModel extends BaseModel<LoginPresenter,LoginContract.Model> {


    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    protected LoginContract.Model getContract() {
        LoginService service = RetrofitManager.create(LoginService.class);
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String username, String password) throws Exception {
                service.login(username, password).enqueue(new Callback<UserInformation>() {
                    @Override
                    public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {

                        if (response.body() != null){
                            final UserInformation userInfo = new UserInformation();
                            Log.d("login",response.body().getErrorMsg());
                            userInfo.setErrorCode(response.body().getErrorCode());
                            userInfo.setErrorMsg(response.body().getErrorMsg());
                            userInfo.setData(response.body().getData());
                            presenter.getContract().responseResult(userInfo);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInformation> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        };
    }
}
