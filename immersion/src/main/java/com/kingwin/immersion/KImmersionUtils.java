package com.kingwin.immersion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    public static void init(Activity activity, @LayoutRes int layputId, KImmersionConfig config){
        if(activity instanceof AppCompatActivity){
            ((AppCompatActivity)activity).supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }


        RelativeLayout baseLayout = new RelativeLayout(activity);

        KTitleBar titleBar = new KTitleBar(activity,config);
        titleBar.setId(R.id.ktitlebar);

        ViewGroup inflate = (ViewGroup) LayoutInflater.from(activity).inflate(layputId, null);
        //baseLayout.addView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(config.getMainLayoutRule() != -1){
            lp.addRule(config.getMainLayoutRule(),titleBar.getId());
        }

        baseLayout.addView(inflate,lp);



        baseLayout.addView(titleBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        activity.setContentView(baseLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        KStatusBarUtils.immersionBar(activity.getWindow());
    }


    /**
     * 初始化布局
     * @param activity 所属界面
     * @param rootView 布局视图
     * @param config 配置文件
     */
    public static void init(Activity activity, View rootView, KImmersionConfig config){
        if(activity instanceof AppCompatActivity){
            ((AppCompatActivity)activity).supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }


        RelativeLayout baseLayout = new RelativeLayout(activity);

        KTitleBar titleBar = new KTitleBar(activity,config);
        titleBar.setId(R.id.ktitlebar);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(config.getMainLayoutRule() != -1){
            lp.addRule(config.getMainLayoutRule(),titleBar.getId());
        }

        baseLayout.addView(rootView,lp);



        baseLayout.addView(titleBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        activity.setContentView(baseLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        KStatusBarUtils.immersionBar(activity.getWindow());
    }

    /**
     * 初始化fragment布局
     * @param fragment 所属碎片
     * @param layputId 布局id
     * @param config 配置文件
     */
    public static View inflate(Fragment fragment, LayoutInflater inflater, ViewGroup container, @LayoutRes int layputId, KImmersionConfig config){


        RelativeLayout baseLayout = new RelativeLayout(fragment.getActivity());

        KTitleBar titleBar = new KTitleBar(fragment.getActivity(),config);
        titleBar.setId(R.id.ktitlebar);

        ViewGroup inflate = (ViewGroup) inflater.inflate(layputId,container,false);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(config.getMainLayoutRule() != -1){
            lp.addRule(config.getMainLayoutRule(),titleBar.getId());
        }

        baseLayout.addView(inflate,lp);



        baseLayout.addView(titleBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));



        return baseLayout;
    }


    /**
     * 初始化fragment布局
     * @param fragment 所属碎片
     * @param rootView 布局视图
     * @param config 配置文件
     */
    public static View inflate(Fragment fragment, LayoutInflater inflater, ViewGroup container, View rootView, KImmersionConfig config){


        RelativeLayout baseLayout = new RelativeLayout(fragment.getActivity());

        KTitleBar titleBar = new KTitleBar(fragment.getActivity(),config);
        titleBar.setId(R.id.ktitlebar);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(config.getMainLayoutRule() != -1){
            lp.addRule(config.getMainLayoutRule(),titleBar.getId());
        }

        baseLayout.addView(rootView,lp);



        baseLayout.addView(titleBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));



        return baseLayout;
    }
}
