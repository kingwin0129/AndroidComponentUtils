package com.kingwin.net;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;

/**
 * KNetWork配置
 * author: kingwin
 * created on: 2020/12/16 10:15 AM
 * description: KNetWork 配置
 */
public class KNetWorkConfig {

    /**
     * 基础地址
     */
    private String baseUrl;

    /**
     * 请求策略
     */
    private Dispatcher dispatcher;

    /**
     * 拦截器
     */
    private List<Interceptor> interceptorArr;

    /**
     * 请求权限TOKEN
     */
    private String token;


    KNetWorkConfig(String baseUrl, Dispatcher dispatcher, List<Interceptor> interceptorArr){
        this.baseUrl = baseUrl;
        this.dispatcher = dispatcher;
        this.interceptorArr = interceptorArr;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public Dispatcher getDispatcher(){
        return this.dispatcher;
    }

    public List<Interceptor> getInterceptorArr(){
        return this.interceptorArr;
    }

    public String getToken(){
        return token == null ? "" : token;
    }

    public void setToken(String str){
        token = str;
    }



    public static class Builder{
        /**
         * 基础地址
         */
        private String baseUrl;

        /**
         * 请求策略
         */
        private Dispatcher dispatcher;

        /**
         * 拦截器
         */
        private List<Interceptor> interceptorArr;

        public Builder(){
            interceptorArr = new ArrayList<>();
        }


        public Builder setBaseUrl(String str){
            this.baseUrl = str;
            return this;
        }

        public Builder setDispatcher(Dispatcher obj){
            this.dispatcher = obj;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor){
            this.interceptorArr.add(interceptor);
            return this;
        }

        public KNetWorkConfig build(){
            return new KNetWorkConfig(this.baseUrl,this.dispatcher,this.interceptorArr);
        }


    }


}
