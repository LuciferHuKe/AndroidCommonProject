package com.zc.degou.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lucifer.common.util.LogUtil;
import com.zc.degou.CommonConstants;
import com.zc.degou.model.UserInfoBean;

/**
 * Created by lucifer on 17/1/7.
 *
 * @deprecated 缓存工具类--单例模式
 */

public class CacheUtil {

    private Context mContext;

    private CacheUtil(Context context) {
        this.mContext = context;
    }

    private static CacheUtil instance = null;

    public static CacheUtil getInstance(Context context) {
        if(null == instance) {
            instance = new CacheUtil(context);
        }
        return instance;
    }

    // -------------------------------- Public Method -------------------------

    /**
     * 缓存当前是否登录状态
     * @param isLogin
     */
    public void saveIsLogin(boolean isLogin) {

        SharedPreferences.Editor editor = getSharePreferences(CommonConstants.KEY_DEFAULT_CACHE).edit();
        editor.putBoolean(CommonConstants.KEY_IS_LOGIN, isLogin);
        editor.commit();
    }

    /**
     * 获取缓存的登录状态
     * @return
     */
    public boolean isLogin() {

        return getSharePreferences(CommonConstants.KEY_DEFAULT_CACHE).getBoolean(CommonConstants.KEY_IS_LOGIN, false);
    }

    /**
     * 缓存当前用户信息
     * @param userInfoBean
     */
    public void saveUserInfo(UserInfoBean userInfoBean) {

        if(null == userInfoBean) {
            return;
        }

        Gson gson = new Gson();
        try {
            String jsonData = gson.toJson(userInfoBean);
            SharedPreferences.Editor editor = getSharePreferences(CommonConstants.KEY_DEFAULT_CACHE).edit();
            editor.putString(CommonConstants.KEY_USER_INFO, jsonData);
            editor.commit();
        } catch (Exception e) {
            LogUtil.error("缓存用户信息异常:" + e.getMessage());
        }
    }

    /**
     * 获取缓存的用户信息
     * @return
     */
    public UserInfoBean getUserInfo() {

        UserInfoBean userInfoBean = new UserInfoBean();

        Gson gson = new Gson();
        try {
            String jsonData = getSharePreferences(CommonConstants.KEY_DEFAULT_CACHE).getString(CommonConstants.KEY_USER_INFO, "");
            userInfoBean = gson.fromJson(jsonData, UserInfoBean.class);
        } catch (Exception e) {
            LogUtil.error("获取缓存的用户信息失败:" + e.getMessage());
        }

        return userInfoBean;
    }

    // -------------------------------- Private Method --------------------------

    /**
     * 获取指定key的sp
     * @param key
     * @return
     */
    private SharedPreferences getSharePreferences(String key) {
        if(TextUtils.isEmpty(key)) {
            return mContext.getSharedPreferences(CommonConstants.KEY_DEFAULT_CACHE, Context.MODE_PRIVATE);
        } else {
            return mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
        }
    }

}
