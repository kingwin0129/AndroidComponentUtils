package com.kingwin.net.demo;

import com.google.gson.Gson;
import com.kingwin.net.callback.BaseNetWorkCallBack;


/**
 * author: kingwin
 * created on: 2020/12/16 10:50 AM
 * description:
 */
public class DMSCallBack<T> extends BaseNetWorkCallBack<T> {


    /**
     * data : bearer-eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ6aG91eGlhb2p1biIsInVzZXJJZCI6InNVUkZuR3FjIiwidXNlck5hbWUiOiLlkajmmZPlhpsiLCJleHBpcmUiOjE1ODUwNDAxNjcyMzMsInVzZXJUeXBlIjoibnVsbCIsImRlcGFydCI6IkhZX1RZMm1IeFZ1IiwidGVuYW50IjoiZDNlMmNkZDJjMjYzNGIwM2EzMDY0YTYzOGMyYjYwZDEifQ.Wb5jPhXjUAo9bTISsJnZKspaebElfp4KWxq5I69KGFWLrCj2CnlWnvA5V63CQ6J1l0zTzoOC1c67GubEpnW_K_GW0NLjjOQBsYSwaIsfEQ0Bve4vHMOF2KbAZT_OJBOqOZYKZPeDDQe6xjDXv9zBRKkZ9cuWIgFIybqdslyrHqU
     * message : 成功
     * status : 200
     */

    private T data;
    private String message;
    private int status;

    @Override
    public int getCode() {
        return status;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean isSucceed() {
        return status == 200;
    }


    public String beanToJson() {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(this);
        System.out.println(jsonStr);
        return jsonStr;
    }


}

