package com.kingwin.net;

import okhttp3.RequestBody;

/**
 * @author KingWin
 * @since 2021/6/23 9:59 上午
 */

public class RequestParams {

    RequestParamsToJson requestParamsToJson;

    public RequestBody toJson(){
        if(null == requestParamsToJson){
            requestParamsToJson = new RequestParamsToJson();
        }
        return requestParamsToJson.beanToRequestBody(this);
    }
}
