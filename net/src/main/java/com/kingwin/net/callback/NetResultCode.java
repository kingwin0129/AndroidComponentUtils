package com.kingwin.net.callback;

/**
 * @author KingWin
 * @since 2021/6/21 10:39 上午
 */

public class NetResultCode {

    // 成功
    public static final int OK = 200;

    // 无网络
    public static final int NoNetwork = -1;

    // 请求超时
    public static final int TimeOut = -2;

    // Http错误
    public static final int HttpError = -3;

    // 连接错误
    public static final int ConnectError = -4;

    // 解析错误
    public static final int ParseError = -5;


    // 未知错误
    public static final int UNKNOWN_ERROR = -99;




    // 服务器不理解客户端的请求，未做任何处理
    public static final int BadRequest = 400;

    // 用户未提供身份验证凭据，或者没有通过身份验证
    public static final int Unauthorized = 401;

    // 用户通过了身份验证，但是不具有访问资源所需的权限
    public static final int Forbidden = 403;

    // 所请求的资源不存在，或不可用
    public static final int NotFound = 404;

    // 用户已经通过身份验证，但是所用的 HTTP 方法不在他的权限之内
    public static final int MethodNotAllowed = 405;

    // 所请求的资源已从这个地址转移，不再可用
    public static final int Gone = 410;

    // 客户端要求的返回格式不支持。比如，API 只能返回 JSON 格式，但是客户端要求返回 XML 格式
    public static final int UnsupportedMediaType = 415;

    // 客户端上传的附件无法处理，导致请求失败
    public static final int UnprocessableEntity = 422;

    // 客户端的请求次数超过限额
    public static final int TooManyRequests = 429;

    // 客户端请求有效，服务器处理时发生了意外
    public static final int InternalServerError = 500;

    // 服务器无法处理请求，一般用于网站维护状态
    public static final int ServiceUnavailable = 503;

}
