package com.lucifer.common.uitl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by lucifer on 16/8/24.
 * <p/>
 * Toast显示信息工具类
 */
public class ToastUtil {

    /**
     * Toast显示提示信息－－1秒
     *
     * @param context
     * @param resId
     */
    public static void showShortInfo(Context context, int resId) {
        if (null == context) {
            return;
        }

        String content = context.getString(resId);
        if (TextUtils.isEmpty(content)) {
            return;
        }

        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast显示提示信息－－1秒
     *
     * @param context
     * @param content
     */
    public static void showShortInfo(Context context, String content) {

        if (null == content) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            return;
        }

        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast显示提示信息－－3秒
     *
     * @param context
     * @param content
     */
    public static void showLongInfo(Context context, String content) {

        if (null == content) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            return;
        }

        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast显示提示信息－－3秒
     *
     * @param context
     * @param resId
     */
    public static void showLongInfo(Context context, int resId) {
        if (null == context) {
            return;
        }

        String content = context.getString(resId);
        if (TextUtils.isEmpty(content)) {
            return;
        }

        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

}
