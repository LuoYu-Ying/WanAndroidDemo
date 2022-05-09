package com.example.wanandroiddemo.utils;

import android.widget.Toast;

public class ToastUtils {
    private String str;
    private int time;

    private ToastUtils(String str, int time) {
        this.str = str;
        this.time = time;
    }

    public static void show(String str, int time) {
        Toast toast = Toast.makeText(MyApp.context, str, Toast.LENGTH_SHORT);
        toast.setDuration(time);
        toast.show();
    }
}
