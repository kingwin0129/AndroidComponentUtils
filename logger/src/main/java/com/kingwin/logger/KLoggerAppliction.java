package com.kingwin.logger;

import android.app.Application;

/**
 * @author KingWin
 * @since 2021/4/21 12:19 PM
 */

public class KLoggerAppliction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KLogger.init();
    }
}
