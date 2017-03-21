package com.zc.degou.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lucifer.common.activity.BaseDataLoadActivity;
import com.zc.degou.R;

/**
 * Created by lucifer on 17/1/7.
 *
 * @deprecated 登录界面
 */

public class LoginActivity extends BaseDataLoadActivity {

    private EditText edt_username;
    private EditText edt_password;

    private Button btn_login;

    private String mUserName = "";  // 用户名
    private String mPassword = "";  // 密码

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

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
            case R.id.btnLogin:
                break;
            default:
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

