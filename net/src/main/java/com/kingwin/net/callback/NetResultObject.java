package com.kingwin.net.callback;

/**
 * @author KingWin
 * @since 2021/6/21 10:56 上午
 */

public class NetResultObject<T> {


    private BaseNetWorkCallBack netWorkCallBack;

    public NetResultObject(BaseNetWorkCallBack netWorkCallBack){
        this.netWorkCallBack = netWorkCallBack;
    }

    /**
     * 获取返回码
     * @return
     */
    public int getCode() {
        return netWorkCallBack.getCode();
    }

    /**
     * 获取返回结果
     * @return
     */
    public T getResult() {
        return (T) netWorkCallBack.getData();
    }

    public boolean isSucceed(){
        return this.netWorkCallBack.isSucceed();
    }

    public static String getErrorMsg(int code) {
        String errMsg = "";

        switch (code){
            case NetResultCode.NoNetwork: errMsg = "网络连接失败，请检查网络"; break;
            case NetResultCode.TimeOut: errMsg = "请求超时，请检查网络"; break;
            case NetResultCode.HttpError: errMsg = "请求错误，请检查网络"; break;
            case NetResultCode.ConnectError: errMsg = "连接错误，请检查网络"; break;
            case NetResultCode.ParseError: errMsg = "数据异常，请联系技术人员"; break;
            case NetResultCode.UNKNOWN_ERROR: errMsg = "网络异常，请联系技术人员"; break;
            case NetResultCode.BadRequest: errMsg = "请求异常，请联系相关技术人员"; break;
            case NetResultCode.Unauthorized: errMsg = "用户身份验证凭据失败"; break;
            case NetResultCode.Forbidden: errMsg = "用户权限异常"; break;
            case NetResultCode.NotFound: errMsg = "所请求的资源不存在，或不可用"; break;
            case NetResultCode.MethodNotAllowed: errMsg = "用户已经通过身份验证，但是所用的 HTTP 方法不在他的权限之内"; break;
            case NetResultCode.Gone: errMsg = "所请求的资源已从这个地址转移，不再可用"; break;
            case NetResultCode.UnsupportedMediaType: errMsg = "客户端要求的返回格式不支持。比如，API 只能返回 JSON 格式，但是客户端要求返回 XML 格式"; break;
            case NetResultCode.UnprocessableEntity: errMsg = "客户端上传的附件无法处理，导致请求失败"; break;
            case NetResultCode.TooManyRequests: errMsg = "客户端的请求次数超过限额"; break;
            case NetResultCode.InternalServerError: errMsg = "客户端请求有效，服务器处理时发生了意外败"; break;
            case NetResultCode.ServiceUnavailable: errMsg = "服务器无法处理请求，一般用于网站维护状态"; break;

        }

        return errMsg;
    }

    public String getErrorMsg() {
        return getErrorMsg(netWorkCallBack.getCode());
    }
}
