package com.kingwin.immersion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.kingwin.immersion.statusBar.KStatusBarUtils;
import com.kingwin.immersion.titleBar.KTitleBar;

/**
 * 沉浸式工具
 * @author KingWin
 * @since 2021/5/12 4:22 PM
 */

public class KImmersionUtils {


    /**
     * 初始化布局
     * @param activity 所属界面
     * @param layputId 布局id
     * @param config 配置文件
     */
    public static void init(AppCompatActivity activity, @LayoutRes int layputId, KImmersionConfig config){
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        ViewGroup inflate = (ViewGroup) LayoutInflater.from(activity).inflate(layputId, null);
        KTitleBar titleBar = new KTitleBar(activity,config);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleBar.setLayoutParams(params);
        inflate.addView(titleBar);
        activity.setContentView(inflate);
        KStatusBarUtils.immersionBar(activity.getWindow());
    }

}
