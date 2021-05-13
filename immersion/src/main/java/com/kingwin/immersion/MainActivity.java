package com.kingwin.immersion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import com.kingwin.immersion.statusBar.KStatusBarUtils;
import com.kingwin.immersion.titleBar.KTitleBar;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_main);

//        ViewGroup inflate = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_main, null);
//        KTitleBar titleBar = new KTitleBar(this);
//        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
//        titleBar.setLayoutParams(params);
//        inflate.addView(titleBar);
//        setContentView(inflate);
//        KStatusBarUtils.immersionBar(getWindow(),getResources().getColor(R.color.teal_200));
//
//        ImmersionConfig config = new ImmersionConfig.ImmersionConfigBuild().build();

    }


}