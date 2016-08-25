package com.lucifer.common.uitl;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by lucifer on 16/8/24.
 *
 * 日志打印工具类
 */
public class LogUtil {

    public static boolean mIsDebugMode = true;  // true为debug模式 false为release模式

    public static String mPackageName = "";  // 项目包名

    /**
     * 设置日志项目的包名
     * @param packageName
     */
    public static void setPackageName(String packageName) {
        if(TextUtils.isEmpty(packageName)) {
            packageName = "";
        }

        mPackageName = packageName;
    }

    /**
     * 设置module是否为调试模式
     * @param mode true为debug模式，flase为release模式
     */
    public static void setModuleMode(boolean mode) {
        mIsDebugMode = mode;
    }

    /**
     * 日志打印info信息
     * @param content
     */
    public static void info(String content) {

        if(TextUtils.isEmpty(content)) {
            content = "";
        }

        if(mIsDebugMode) {
            Log.i(mPackageName, "-------------------------------------------------------------------");
            Log.i(mPackageName, content);
            Log.i(mPackageName, "-------------------------------------------------------------------");
        }
    }

    /**
     * 日志打印error信息
     * @param content
     */
    public static void error(String content) {

        if(TextUtils.isEmpty(content)) {
            content = "";
        }

        if(mIsDebugMode) {
            Log.e(mPackageName, "-------------------------------------------------------------------");
            Log.e(mPackageName, content);
            Log.e(mPackageName, "-------------------------------------------------------------------");
        }
    }

    /**
     * 日志打印warn信息
     * @param content
     */
    public static void warn(String content) {

        if(TextUtils.isEmpty(content)) {
            content = "";
        }

        if(mIsDebugMode) {
            Log.w(mPackageName, "-------------------------------------------------------------------");
            Log.w(mPackageName, content);
            Log.w(mPackageName, "-------------------------------------------------------------------");
        }
    }

    /**
     * 日志打印verbose信息
     * @param content
     */
    public static void verbose(String content) {

        if(TextUtils.isEmpty(content)) {
            content = "";
        }

        if(mIsDebugMode) {
            Log.v(mPackageName, "-------------------------------------------------------------------");
            Log.v(mPackageName, content);
            Log.v(mPackageName, "-------------------------------------------------------------------");
        }
    }

}
