package com.example.wanandroiddemo.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final String baseUrl = "https://www.wanandroid.com/";
    private RetrofitManager() {}

    public static Retrofit getInstance() {
        return RetrofitHolder.instance;
    }

    private static class RetrofitHolder {
        private static final Retrofit instance = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T create(Class<T> clz) {
        return getInstance().create(clz);
    }
}
