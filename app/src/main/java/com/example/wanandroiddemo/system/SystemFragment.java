package com.example.wanandroiddemo.system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.wanandroiddemo.R;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/5
 */
public class SystemFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_system, container, false);

        return view;
    }
}
