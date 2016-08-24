package com.lucifer.common.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucifer.common.R;
import com.lucifer.common.view.ProgressHUD;

/**
 * Created by lucifer on 16/8/23.
 */
public abstract class BaseFragment extends Fragment
        implements View.OnClickListener, DialogInterface.OnCancelListener {

    protected Context mContext;

    protected View mContentView;

    private ProgressHUD mProgressHUD;

    // --------------------------------------- Override Method ---------------------------------

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutResId(), null);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 初始化界面数据
        initData();

        // 初始化界面组件
        initView();

        // 注册组件监听事件
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 提示信息对话框消失
        dismissProgressHUD();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        // 提示信息对话框消失
        dismissProgressHUD();
    }

    // --------------------------------------- Public Method -----------------------------------

    /**
     * 页面跳转
     * @param cls 目的Activity
     */
    public void switchActivity(Class<?> cls) {
        switchActivity(cls, null);
    }

    /**
     * 页面跳转－－带数据
     * @param cls 目的Activity
     * @param bundle 界面传递数据
     */
    public void switchActivity(Class<?> cls, Bundle bundle) {
        switchActivity(cls, bundle, -1);
    }

    /**
     * 页面跳转
     * @param cls 目的Activity
     * @param bundle 界面传递数据
     * @param flag
     */
    public void switchActivity(Class<?> cls, Bundle bundle, int flag) {
        Intent intent = new Intent(mContext, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        if (flag != -1) {
            intent.setFlags(flag);
        }
        startActivity(intent);
    }

    /**
     * 页面跳转，完毕后关闭当前界面
     * @param cls 目的Activity
     */
    public void switchActivityAndFinish(Class<?> cls) {
        switchActivityAndFinish(cls, null);
    }

    /**
     * 页面跳转，带数据，完毕后关闭当前界面
     * @param cls 目的Activity
     * @param bundle 界面传递数据
     */
    public void switchActivityAndFinish(Class<?> cls, Bundle bundle) {
        switchActivityAndFinish(cls, bundle, -1);
    }

    /**
     * 页面跳转，带数据，完毕后关闭当前界面
     * @param cls 目的Activity
     * @param bundle 界面传递数据
     * @param flag
     */
    public void switchActivityAndFinish(Class<?> cls, Bundle bundle, int flag) {
        switchActivity(cls, bundle, flag);
        getActivity().finish();
    }

    /**
     * 页面跳转，获取目的界面的返回数据
     * @param cls 目的Activity
     * @param requestCode 请求CODE
     */
    public void switchActivityForResult(Class<?> cls, int requestCode) {
        switchActivityForResult(cls, requestCode, null);
    }

    /**
     * 页面跳转，获取目的界面的返回数据
     * @param cls 目的Activity
     * @param requestCode 请求CODE
     * @param bundle 界面传递数据
     */
    public void switchActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        switchActivityForResult(cls, requestCode, bundle, -1);
    }

    /**
     * 页面跳转，获取目的界面的返回数据
     * @param cls 目的Activity
     * @param requestCode 请求CODE
     * @param bundle 界面传递数据
     * @param flag
     */
    public void switchActivityForResult(Class<?> cls, int requestCode, Bundle bundle, int flag) {
        Intent intent = new Intent(mContext, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        if (flag != -1) {
            intent.setFlags(flag);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 显示提示信息Dialog
     */
    public void showProgressHUD() {

        showProgressHUD(R.string.base_remaind_loading);
    }

    /**
     * 显示提示信息Dialog
     * @param resId
     */
    public void showProgressHUD(int resId) {

        String content = getString(resId);
        if(TextUtils.isEmpty(content)) {
            content = getString(R.string.base_remaind_loading);
        }

        if (null == mProgressHUD) {
            mProgressHUD = ProgressHUD.show(mContext, content, true, true, this);
        } else {
            if (!mProgressHUD.isShowing()) {
                mProgressHUD = ProgressHUD.show(mContext, content, true, true, this);
            }
        }
    }

    /**
     * 显示提示信息Dialog
     * @param content
     */
    public void showProgressHUD(String content) {
        if(TextUtils.isEmpty(content)) {
            content = getString(R.string.base_remaind_loading);
        }

        if (null == mProgressHUD) {
            mProgressHUD = ProgressHUD.show(mContext, content, true, true, this);
        } else {
            if (!mProgressHUD.isShowing()) {
                mProgressHUD = ProgressHUD.show(mContext, content, true, true, this);
            }
        }
    }

    /**
     * 提示信息对话框消失
     */
    public void dismissProgressHUD() {
        if (mProgressHUD != null && mProgressHUD.isShowing())
            mProgressHUD.dismiss();
    }

    // --------------------------------------- Protect Method -----------------------------------

    // 获取当前Activity的布局文件资源id
    protected abstract int getLayoutResId();

    // 初始化页面数据，获取上一个页面传递的数据
    protected abstract void initData();

    // 初始化页面组件
    protected abstract void initView();

    // 注册页面组件的监听事件
    protected abstract void initListener();

    // --------------------------------------- Private Method -----------------------------------

}