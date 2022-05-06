package com.example.wanandroiddemo.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.wxarticle.WxArticleRecyclerViewAdapter;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/5
 */
public class myFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }
}
