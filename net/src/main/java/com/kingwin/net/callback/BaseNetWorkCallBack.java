package com.kingwin.net.callback;

/**
 * 基础网络层返回对象
 * author: kingwin
 * created on: 2021/2/2 5:09 PM
 * description:
 */
public abstract class BaseNetWorkCallBack<T> {

    public abstract int getCode();

    public abstract T getData();

    public abstract boolean isSucceed();


}
