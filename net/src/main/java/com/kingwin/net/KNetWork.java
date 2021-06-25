package com.kingwin.net;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.kingwin.logger.KLogger;
import com.kingwin.net.callback.BaseNetWorkCallBack;
import com.kingwin.net.callback.BaseNetWorkCallBackListener;
import com.kingwin.net.callback.CustomObserver;
import com.kingwin.net.callback.NetResultCode;
import com.kingwin.net.callback.NetResultObject;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络组件
 * @author KingWin
 * @since  2020/12/16 9:56 AM
 */
public class KNetWork {

    private static OkHttpClient okHttpClient;

    private static Retrofit mRetrofit;

    private static KNetWorkConfig curOkHttpConfig;

    public static void init(KNetWorkConfig config){
        curOkHttpConfig = config;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(null != config.getDispatcher()){
            builder.dispatcher(config.getDispatcher());
        }
        for (Interceptor interceptor:config.getInterceptorArr()) {
            builder.addInterceptor(interceptor);
        }


        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(config.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

    }

    public static KNetWorkConfig getCurOkHttpConfig(){
        return curOkHttpConfig;
    }

    public static Retrofit getRetrofit(){
        if(null == mRetrofit){
            throw new NullPointerException("请初始化KNetWork组价,使用KNetWork.init()");
        }
        return mRetrofit;
    }

    public static RequestBody beanToRequestBody(Object obj) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        System.out.println(jsonStr);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
    }



    public static <T> void requestApi(final Observable<? extends BaseNetWorkCallBack<T>> baseNetWorkCallBackObservable, BaseNetWorkCallBackListener<T> listener){

        baseNetWorkCallBackObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new CustomObserver<BaseNetWorkCallBack<T>>() {
                    @Override
                    protected void onFault(Throwable e) {
                        KLogger.d("网络请求客户端异常==>"+e.getMessage());
                        if (e instanceof HttpException) {     //   HTTP错误
                            listener.onError(NetResultCode.HttpError,NetResultObject.getErrorMsg(NetResultCode.HttpError));
                        } else if (e instanceof ConnectException
                                || e instanceof UnknownHostException) {   //   连接错误
                            listener.onError(NetResultCode.ConnectError,NetResultObject.getErrorMsg(NetResultCode.ConnectError));
                        } else if (e instanceof InterruptedIOException) {   //  连接超时
                            listener.onError(NetResultCode.TimeOut,NetResultObject.getErrorMsg(NetResultCode.TimeOut));
                        } else if (e instanceof JsonParseException
                                || e instanceof JSONException
                                || e instanceof ParseException) {   //  解析错误
                            listener.onError(NetResultCode.ParseError,NetResultObject.getErrorMsg(NetResultCode.ParseError));
                        } else {
                            listener.onError(NetResultCode.UNKNOWN_ERROR,NetResultObject.getErrorMsg(NetResultCode.UNKNOWN_ERROR));
                        }
                        onFinish();
                    }



                    @Override
                    protected void onSuccess(BaseNetWorkCallBack<T> callBackObj) {
                        NetResultObject<BaseNetWorkCallBack> netResultObject = new NetResultObject((BaseNetWorkCallBack) callBackObj);
                        if(netResultObject.isSucceed()){
                            listener.onSucceed((T) netResultObject.getResult());
                        }else{
                            listener.onError(netResultObject.getCode(),netResultObject.getErrorMsg());
                        }

                    }
//                        if(null == callBackObj){
//                            listener.onError("服务器异常,请稍后再试(错误代码:e002)");
//                        }else{
//                            if(callBackObj.isSucceed()){
//                                listener.onSucceed(callBackObj);
//                            }
//                            else {
//                                StringBuffer message = new StringBuffer();
//                                if(null == callBackObj.getMessage()){
//                                    message.append("数据解析异常(错误代码:e003)");
//                                }else{
//                                    message.append(callBackObj.getMessage());
//                                    message.append("(错误代码:e004)");
//                                }
//                                listener.onError(message.toString());
//                            }
//                        }
//
//
//                    }
                });
    }


}
