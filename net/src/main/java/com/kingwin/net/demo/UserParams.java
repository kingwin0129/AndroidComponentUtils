package com.kingwin.net.demo;

import com.kingwin.net.RequestParams;

/**
 * author: kingwin
 * created on: 2020/12/16 10:51 AM
 * description:
 */
public class UserParams extends RequestParams {
    String username;
    String password;

    public UserParams(String username, String password){
        this.username = username;
        this.password = password;
    }

}
