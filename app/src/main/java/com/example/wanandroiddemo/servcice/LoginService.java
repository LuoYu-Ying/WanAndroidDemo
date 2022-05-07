package com.example.wanandroiddemo.servcice;

import com.example.wanandroiddemo.model.UserInformation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/7
 */
public interface LoginService {
    /**
     * 登录请求
     */
    @FormUrlEncoded
    @POST("user/login")
    Call<UserInformation>login(@Field("username") String username, @Field("password") String password);

}
