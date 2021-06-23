package com.kingwin.net.Interceptor;

import com.kingwin.net.KNetWork;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: kingwin
 * created on: 2021/2/25 10:25 AM
 * description:
 */
public class RedirectInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 以拦截到的请求为基础创建一个新的请求对象，然后插入Header
        HttpUrl newBaseUrl = null;
        if(null != chain.request().header("requestType")){
            if(chain.request().header("requestType").equals("other")){
                newBaseUrl= chain.request().url();
            }
        }




        //从request中获取原有的HttpUrl实例oldHttpUrl

        HttpUrl oldHttpUrl = chain.request().url();

        //重建新的HttpUrl，修改需要修改的url部分

        HttpUrl newFullUrl = newBaseUrl == null ? oldHttpUrl : oldHttpUrl

                .newBuilder()

                .scheme(newBaseUrl.scheme())

                .host(newBaseUrl.host())

                .port(newBaseUrl.port())

                .build();

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", KNetWork.getCurOkHttpConfig().getToken())
                .url(newFullUrl)
                .build();

        return chain.proceed(request);
    }
}
