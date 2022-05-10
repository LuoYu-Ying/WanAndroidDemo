package com.example.wanandroiddemo.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final String baseUrl = "https://www.wanandroid.com/";
    private RetrofitManager() {}

    public static Retrofit getInstance() {
        return RetrofitHolder.instance;
    }

    private static class RetrofitHolder {
        private static final CookieForClient cookieForClient = new CookieForClient();
        private static final Retrofit instance = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(cookieForClient.getAddInterceptor())
                .client(cookieForClient.getReceiveInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static boolean isCookieSaved() {
        return new CookieDao().isCookieSaved();
    }

    public static <T> T create(Class<T> cls) {
        return getInstance().create(cls);
    }
}
