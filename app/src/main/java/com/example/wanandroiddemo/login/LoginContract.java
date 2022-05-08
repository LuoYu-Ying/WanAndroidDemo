package com.example.wanandroiddemo.login;

import com.example.wanandroiddemo.model.UserInformation;

/**
 * @author Zack
 * @Description
 * @CreateDate 2022/5/7
 */
public interface LoginContract{
    interface Model{
        /*
        处理登录
         */
        void executeLogin(String username, String password)throws  Exception;
    }
    interface View<T extends UserInformation>{
        //处理结果
        void handlerUserInfo(T t);
    }
    interface Presenter<T extends UserInformation>{
        //登录请求(接受到View层指令，让Model层执行）
        void requestLogin(String name, String password);
        //结果响应（接受到Model层处理的结果，通知View层刷新）
        void responseResult(T t);
    }
}
