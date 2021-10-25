package com.kingwin.utils;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author KingWin
 * @since 2021/10/20 3:52 下午
 */

public class KContextManage {

    private Application mApp;

    private Activity mActivity;

    private Fragment mFragment;

    private static volatile KContextManage mInstance = null;

    public static synchronized KContextManage getInstance(){
            if(null == mInstance){
                synchronized (KConvertUtils.class){
                    if(null == mInstance){
                        mInstance = new KContextManage();
                    }
                }
        }
        return mInstance;
    }


    public void setApp(Application app){
        mApp = app;
    }

    public Application getApp(){
        if(null == mApp){
            mApp = getApplicationByReflect();
        }
        if(null == mApp){
            throw new NullPointerException("reflect failed.");
        }
        return mApp;
    }

    public void setActivity(Activity activity){
        mActivity = activity;
    }

    public Activity getActivity(){
        return mActivity;
    }

    public void setFragment(Fragment fragment){
        mFragment = fragment;
    }

    public Fragment getFragment(){
        return mFragment;
    }

    /**
     * 通过反射获取Appliction
     * @return
     */
    private Application getApplicationByReflect() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object thread = getActivityThread();
            Object app = activityThreadClass.getMethod("getApplication").invoke(thread);
            if (app == null) {
                return null;
            }
            return (Application) app;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Activity线程
     * @return
     */
    private Object getActivityThread() {
        Object activityThread = getActivityThreadInActivityThreadStaticField();
        if (activityThread != null) return activityThread;
        return getActivityThreadInActivityThreadStaticMethod();
    }

    /**
     * 通过静态字段获取Activity线程
     * @return
     */
    private Object getActivityThreadInActivityThreadStaticField() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            return sCurrentActivityThreadField.get(null);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
            return null;
        }
    }

    /**
     * 通过静态方法获取Activity线程
     * @return
     */
    private Object getActivityThreadInActivityThreadStaticMethod() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            return activityThreadClass.getMethod("currentActivityThread").invoke(null);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
            return null;
        }
    }


}
