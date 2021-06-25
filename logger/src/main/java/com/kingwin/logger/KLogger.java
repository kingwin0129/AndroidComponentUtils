package com.kingwin.logger;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 日志组件
 * @author KingWin
 * @since 2021/4/21 12:20 PM
 */
public class KLogger {

    private static final String defaultTag = "KingWin";

    public static void init(){
        init(new KLogger.Builder().tag(defaultTag));
    }

    public static void init(Builder build){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().tag(build.tag).build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


    public static void d(@NonNull Object object){
        Logger.d(object);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message,args);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        Logger.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        Logger.xml(xml);
    }





    public static class Builder{

        /**
         * 输出标签
         */
        private String tag;

        public Builder tag(String str){
            this.tag = str;
            return this;
        }


    }

}


