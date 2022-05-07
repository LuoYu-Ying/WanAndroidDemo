package com.example.wanandroiddemo.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.wanandroiddemo.R;
import com.example.wanandroiddemo.base_mvp.BaseView;
import com.example.wanandroiddemo.model.UserInformation;
import com.example.wanandroiddemo.utils.EditTextWithClear;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/7
 */
public class LoginFragment  extends BaseView<LoginPresenter,LoginContract.View> {
    private EditTextWithClear edit_username,edit_password;
    private Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                presenter.getContract().requestLogin(username,password);
            }
        });
        return view;
    }


    public void initView(View view){
        edit_username = view.findViewById(R.id.edit_username);
        edit_password = view.findViewById(R.id.edit_password);
        login = view.findViewById(R.id.btn_login);
    }


    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected LoginContract.View getContract() {
        return new LoginContract.View() {
            @Override
            public void handlerUesrInfo(UserInformation userInformation) {
                String name = userInformation.getData().getPublicName();
                Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
            }
        };
    }
}
