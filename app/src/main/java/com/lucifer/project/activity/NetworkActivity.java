package com.lucifer.project.activity;

import android.view.View;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.lucifer.project.R;

/**
 * Created by lucifer on 16/9/29.
 *
 * 网络通信部分
 */
public class NetworkActivity extends BaseFragmentActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_network;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initListener() {

        int[] resIds = {
                R.id.btnBaseBack
        };
        for (int id : resIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnBaseBack: {
                finish();
            }
            break;
            default:
                break;
        }
    }
}
