package com.kingwin.componentutils;

import android.app.Application;

import com.kingwin.logger.KLogger;

/**
 * @author KingWin
 * @since 2021/4/21 2:41 PM
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KLogger.init();
    }
}
