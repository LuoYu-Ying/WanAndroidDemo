package com.example.wanandroiddemo.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.wanandroiddemo.LoginActivity;
import com.example.wanandroiddemo.MainActivity;
import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.wxarticle.WxArticleRecyclerViewAdapter;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/5
 */
public class MyFragment extends Fragment {

    private Button loginBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);

        //button click
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void initView(View view) {
        loginBtn = view.findViewById(R.id.my_btn_login);
    }


}
