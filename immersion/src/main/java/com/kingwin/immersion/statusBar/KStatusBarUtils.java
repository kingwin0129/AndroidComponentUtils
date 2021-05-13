package com.kingwin.immersion.statusBar;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

/**
 * @author KingWin
 * @since 2021/5/8 4:45 PM
 */

public class KStatusBarUtils {


    /**
     * 调暗（仅一次有效，触摸后如再次调暗需要再次调用）
     * @param window
     */
    public static void dim(Window window){
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }

    /**
     * 隐藏状态栏
     * @param window
     */
    public static void hide(Window window){
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    /**
     * 状态栏 着色
     * @param window
     * @param color 着色的Color
     */
    public static void colour(Window window,@ColorInt int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }

    }



    /**
     * 融合状态栏至布局
     * @param window
     */
    public static void merge(Window window){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        window.getDecorView().setSystemUiVisibility(option);

    }


    /**
     * 设置沉浸式状态栏
     * 自动设置融合状态栏并设置透明
     * @param window
     */
    public static void immersionBar(Window window){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            merge(window);
            colour(window,Color.TRANSPARENT);
        }
    }

    /**
     * 设置沉浸式状态栏
     * 根据color着色状态栏 状态栏和布局还是单独占位存在
     * @param window
     * @param color 着色的Color
     */
    public static void immersionBar(Window window,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            colour(window,color);
        }
    }


    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



}
