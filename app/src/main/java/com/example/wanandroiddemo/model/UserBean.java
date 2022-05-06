package com.example.wanandroiddemo.model;

/**
 * @author Zack
 * @Description User登录数据
 * @CreateDate 2022/5/6
 */
public class UserBean {
    private String username;
    private String password;

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
