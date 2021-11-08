package com.kingwin.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author KingWin
 * @since 2021/5/12 5:16 PM
 */

public final class KConvertUtils {

    private static final int    BUFFER_SIZE      = 8192;

    private KConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * Value of dp to value of px.
     *
     * @param context 上下文
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(Context context , final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Value of px to value of dp.
     *
     * @param context 上下文
     * @param pxValue The value of px.
     * @return value of dp
     */
    public static int px2dp(Context context , final float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Value of sp to value of px.
     *
     * @param context 上下文
     * @param spValue The value of sp.
     * @return value of px
     */
    public static int sp2px(Context context , final float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * Value of px to value of sp.
     *
     * @param context 上下文
     * @param pxValue The value of px.
     * @return value of sp
     */
    public static int px2sp(Context context , final float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * Input stream to output stream.
     */
    public static ByteArrayOutputStream input2OutputStream(final InputStream is) {
        if (is == null) return null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[BUFFER_SIZE];
            int len;
            while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
                os.write(b, 0, len);
            }
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Input stream to bytes.
     */
    public static byte[] inputStream2Bytes(final InputStream is) {
        if (is == null) return null;
        return input2OutputStream(is).toByteArray();
    }
}
