package com.zc.degou.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lucifer.common.activity.BaseDataLoadActivity;
import com.lucifer.common.util.ToastUtil;
import com.zc.degou.R;
import com.zc.degou.server.base.RefreshData;
import com.zc.degou.server.base.RefreshUtil;
import com.zc.degou.server.base.ServerData;
import com.zc.degou.server.tool.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucifer on 17/1/7.
 *
 * @deprecated 登录界面
 */

public class LoginActivity extends BaseDataLoadActivity implements RefreshData{

    // 业务号
    public static final int TAG_LOGIN = 30;

    private EditText edt_username;
    private EditText edt_password;

    private Button btn_login;

    private String mUserName = "";  // 用户名
    private String mPassword = "";  // 密码

    // 网络通信工具类
    private RefreshUtil requestUtil = null;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

        requestUtil = new RefreshUtil(this, "Login", this, null);

    }

    @Override
    protected void initView() {

        edt_username = (EditText) findViewById(R.id.edtLoginUser);
        edt_password = (EditText) findViewById(R.id.edtLoginPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);

        btn_login.setEnabled(false);
        btn_login.setBackgroundResource(R.color.login_btn_unfocus);
    }

    @Override
    protected void initListener() {
        int[] resIds = {
                R.id.btnLogin
        };
        for (int id : resIds) {
            findViewById(id).setOnClickListener(this);
        }
        edt_username.addTextChangedListener(new UserEditChangeListener());
        edt_password.addTextChangedListener(new PasswordEditChangeListener());
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnLogin: {
                // 登录按钮点击
                onClickLogin();
            }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(null != requestUtil) {
            requestUtil.releaseUtil();
        }
    }

    @Override
    public void refreshData(ServerData serverData) {

        int flat = serverData.getFlag();
        switch (flat) {
            case TAG_LOGIN: {
                dismissProgressHUD();
                analyzeLoginData(serverData);
            }
                break;
        }

    }

    @Override
    public void showNoData(int flag) {

        switch (flag) {
            case TAG_LOGIN: {
                dismissProgressHUD();
                ToastUtil.showShortInfo(this, "登录失败，未能获取到返回值");
            }
                break;
        }
    }

    @Override
    public void showError(int flag) {

        switch (flag) {
            case TAG_LOGIN: {
                dismissProgressHUD();
                ToastUtil.showShortInfo(this, "登录失败");
            }
                break;
        }

    }

    // ---------------------------- Private Method ------------------------------

    /**
     * 判断用户名和密码是否为空
     * @return
     */
    private boolean isEdtEmpty() {

        mUserName = edt_username.getText().toString();
        mPassword = edt_password.getText().toString();

        if(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)) {
            btn_login.setEnabled(false);
            btn_login.setBackgroundResource(R.color.login_btn_unfocus);
            return false;
        } else {
            btn_login.setEnabled(true);
            btn_login.setBackgroundResource(R.drawable.sel_login_btn);
            return true;
        }

    }

    /**
     * 点击登录
     */
    private void onClickLogin() {

        showProgressHUD("登录中，请稍后....");
        HashMap<String, String> mParams = new HashMap<String, String>();
        mParams.put("USER_NAME", mUserName);
        mParams.put("PASSWORD", mPassword);
        requestUtil.getData(mParams, "YDA10030", TAG_LOGIN);

    }

    /**
     * 解析登录返回数据
     * @param serverData
     */
    private void analyzeLoginData(ServerData serverData) {

        Map<String, String> resData = serverData.getSingleData(1);
        if(null == resData || "0".equals(resData.get("STATUS"))) {
            ToastUtil.showShortInfo(this, "登录失败，" + resData.get("NOTE"));
        } else {
            LogUtil.info(this, "登录成功，返回用户信息：" + resData.get("DATA"));
            // 跳转到主界面
            switchActivityAndFinish(MainActivity.class);
        }

    }


    // ---------------------------- Inner Class ---------------------------------

    private class UserEditChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isEdtEmpty();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }

    private class PasswordEditChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isEdtEmpty();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}

