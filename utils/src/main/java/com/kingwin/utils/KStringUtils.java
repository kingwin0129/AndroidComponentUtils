package com.kingwin.utils;

/**
 * @author KingWin
 * @since 2021/5/12 6:08 PM
 */

public class KStringUtils {

    public static String ellipsize(String str,int max){
        String result = str;
        if(str.length()>max){
            result = str.substring(0,max) + "...";
        }
        return result;
    }
}
