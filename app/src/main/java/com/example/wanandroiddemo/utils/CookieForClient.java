package com.example.wanandroiddemo.utils;

import okhttp3.OkHttpClient;

public class CookieForClient {
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();

    public OkHttpClient getAddInterceptor() {
        return builder.addInterceptor(new AddCookieInterceptor()).build();
    }

    public OkHttpClient getReceiveInterceptor() {
        return builder.addInterceptor(new ReceiveCookieInterceptor()).build();
    }
}
