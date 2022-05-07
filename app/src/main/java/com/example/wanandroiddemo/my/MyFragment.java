package com.example.wanandroiddemo.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.wanandroiddemo.LoginActivity;
import com.example.wanandroiddemo.MainActivity;
import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.model.UserInformation;
import com.example.wanandroiddemo.wxarticle.WxArticleRecyclerViewAdapter;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/5
 */
public class MyFragment extends Fragment {
    private TextView tv_name;
    private ImageView pic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void initView(View view) {
        tv_name = view.findViewById(R.id.tv_name);
        pic = view.findViewById(R.id.image);
    }


}
