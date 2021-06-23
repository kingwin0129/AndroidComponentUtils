package com.kingwin.net;

import android.app.Application;

import com.kingwin.logger.KLogger;
import com.kingwin.net.Interceptor.CustomLogInterceptor;
import com.kingwin.net.Interceptor.RedirectInterceptor;
import com.kingwin.net.Interceptor.RetryAndFollowUpInterceptor;

import okhttp3.Dispatcher;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author KingWin
 * @since 2021/6/17 4:27 下午
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
                .addInterceptor(new RetryAndFollowUpInterceptor(3))
                .build());
    }
}
