package com.kingwin.net.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 重试及重定向拦截器
 * author: kingwin
 * created on: 2021/1/28 6:25 PM
 * description:
 */
public class RetryAndFollowUpInterceptor implements Interceptor {

    private int count = 0;
    private int maxRetryCount = 3;

    public RetryAndFollowUpInterceptor(int count){
        maxRetryCount = count;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        Request request = chain.request();


        try {
            response = chain.proceed(request);
            while (!response.isSuccessful() && count < maxRetryCount){
                count++;
                response = intercept(chain);
            }
        }catch (Exception e){
            while (count < maxRetryCount){
                count++;
                response = intercept(chain);
            }
        }

        return response;
    }



}
