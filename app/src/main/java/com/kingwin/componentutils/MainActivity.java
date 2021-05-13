package com.kingwin.componentutils;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kingwin.immersion.KImmersionConfig;
import com.kingwin.immersion.KImmersionMode;
import com.kingwin.immersion.KImmersionUtils;
import com.kingwin.logger.KLogger;
import com.kingwin.utils.KConvertUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<View> viewList = new ArrayList<>();

        TextView tv  = new TextView(this);
        tv.setText("菜单");
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLogger.d("点击文本");
            }
        });
        viewList.add(tv);

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(KConvertUtils.dp2px(this,30), KConvertUtils.dp2px(this,30));
        iv.setLayoutParams(params);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLogger.d("点击图片");
            }
        });
        viewList.add(iv);


        KImmersionConfig config = new KImmersionConfig.KImmersionConfigBuild()
                .setTitleName("推荐页")
                .setMenuList(viewList)
                .setLeftClickListener(new KImmersionConfig.LeftViewClick() {
                    @Override
                    public void onClick() {
                        KLogger.d("左视图点击");
                    }
                })
                .build();
        KImmersionUtils.init(this,R.layout.activity_main,config);

        KLogger.d("基础组件输出，成功组装日志组件");

    }
}