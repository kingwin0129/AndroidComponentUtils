package com.kingwin.componentutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kingwin.logger.KLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KLogger.d("基础组件输出，成功组装日志组件");

    }
}