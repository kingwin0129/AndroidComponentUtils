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

    /**
     * 字符串是否为空或空白
     *
     * @param s 字符串.
     * @return {@code true}: yes<br> {@code false}: no
     */
    public static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
