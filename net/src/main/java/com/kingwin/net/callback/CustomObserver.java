package com.kingwin.net.callback;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * author: kingwin
 * created on: 2020/12/16 10:57 AM
 * description:
 */
public abstract class CustomObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFault(e);
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }


    protected void onFinish(){

    }


    protected abstract void onFault(Throwable e);

    protected abstract void onSuccess(T t);

}

