package com.kingwin.componentutils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.kingwin.net.KNetWork;
import com.kingwin.net.callback.BaseNetWorkCallBack;
import com.kingwin.net.callback.BaseNetWorkCallBackListener;
import com.kingwin.net.callback.NetResultObject;
import com.kingwin.net.demo.DMSCallBack;
import com.kingwin.net.demo.DMSCallBackListener;
import com.kingwin.net.demo.TestApi;
import com.kingwin.net.demo.UserParams;
import com.kingwin.utils.KConvertUtils;
import com.kingwin.utils.KFileUtils;
import com.kingwin.utils.KPathUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView tv_info;

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        List<View> viewList = new ArrayList<>();

        TextView tv  = new TextView(this);
        tv.setText("菜单");
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLogger.d("点击文本");
                testNet();
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
                .hide()
                .setLeftClickListener(new KImmersionConfig.LeftViewClick() {
                    @Override
                    public void onClick() {
                        KLogger.d("返回点击");
                    }
                })
                .build();
        KImmersionUtils.init(this,R.layout.activity_main,config);

        KLogger.d("基础组件输出，成功组装日志组件");

        tv_info = findViewById(R.id.tv_info);
        tv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLogger.d("点击文本");
                Intent t = new Intent(MainActivity.this,PdfActivity.class);
                startActivity(t);
//                KNetWork.downloadFile("http://221.181.173.253:8018/Affairs/ShowFile?filePath=/YmsFileUpload/AffairGuidFiles/RSZ047.pdf", new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        KLogger.d("下载失败");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        KLogger.d("下载成功");
//                        InputStream inputStream = response.body().byteStream();
//                        if(null != inputStream){
//                            String savePath = KPathUtils.getExternalAppPicturesPath() + File.separator + "curAffair.pdf";
//                            //KFileUtils.createFile(savePath);
//                            Boolean isDownloadFile = KFileUtils.writeFileFromIS(savePath,inputStream,false);
//                            if(isDownloadFile){
//                                KLogger.d("保存成功");
//                            }else{
//                                KLogger.d("保存失败");
//                            }
//
//                        }
//                    }
//                });
                //testNet();
            }
        });
    }


    private  void testNet(){
        TestApi api =  KNetWork.getRetrofit().create(TestApi.class);
        KNetWork.requestApi(api.login(new UserParams("汤福兴","111111").toJson()), new BaseNetWorkCallBackListener<String>() {



            @Override
            public void onSucceed(BaseNetWorkCallBack<String> t) {
                KNetWork.getCurOkHttpConfig().setToken(t.getData());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_info.setText(KNetWork.getCurOkHttpConfig().getToken());
                    }
                });

            }

            @Override
            public void onFault(int code, String msg) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_info.setText(msg);
                    }
                });
            }


        });
    }
}