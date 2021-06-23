package com.kingwin.net.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kingwin.net.KNetWork;
import com.kingwin.net.R;
import com.kingwin.net.callback.BaseNetWorkCallBack;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        testCall();


    }

    public void testCall(){
        TextView text = findViewById(R.id.tv_info);
        TestApi api =  KNetWork.getRetrofit().create(TestApi.class);
        KNetWork.requestApi(api.login(KNetWork.beanToRequestBody(new UserParams("汤福兴","111111"))), new DMSCallBackListener<String>() {

            @Override
            public void onSucceed(String result) {
                KNetWork.getCurOkHttpConfig().setToken(result);
                text.setText(KNetWork.getCurOkHttpConfig().getToken());

            }

            @Override
            public void onError(int code,String msg) {
                text.setText(msg);
            }

        });
    }



}