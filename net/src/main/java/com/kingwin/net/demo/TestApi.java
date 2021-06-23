package com.kingwin.net.demo;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * author: kingwin
 * created on: 2020/12/16 10:49 AM
 * description:
 */
public interface TestApi {

    final String PATH = "auth";

    @POST(PATH+"/jwt/token")
    Observable<DMSCallBack<String>> login(@Body RequestBody params);
}
