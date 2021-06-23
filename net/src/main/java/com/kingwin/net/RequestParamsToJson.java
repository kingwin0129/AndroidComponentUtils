package com.kingwin.net;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author KingWin
 * @since 2021/6/23 9:54 上午
 */

class RequestParamsToJson {


    public RequestBody beanToRequestBody(Object obj) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        System.out.println(jsonStr);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
    }
}
