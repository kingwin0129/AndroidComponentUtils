package com.kingwin.componentutils;

import android.app.Application;

import com.kingwin.logger.KLogger;
import com.kingwin.net.Interceptor.CustomLogInterceptor;
import com.kingwin.net.Interceptor.RedirectInterceptor;
import com.kingwin.net.Interceptor.RetryAndFollowUpInterceptor;
import com.kingwin.net.KNetWork;
import com.kingwin.net.KNetWorkConfig;

import okhttp3.Dispatcher;

/**
 * @author KingWin
 * @since 2021/4/21 2:41 PM
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KLogger.init();
        initNetWork();
    }

    private void initNetWork(){
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(10);
        dispatcher.setMaxRequestsPerHost(5);
        KNetWork.init(new KNetWorkConfig.Builder()
                .setBaseUrl("http://36.156.144.71:8765/api/")
                .setDispatcher(dispatcher)
                .addInterceptor(new RedirectInterceptor())
                .addInterceptor(new CustomLogInterceptor())
                //.addInterceptor(new RetryAndFollowUpInterceptor(3))
                .build());
    }
}
