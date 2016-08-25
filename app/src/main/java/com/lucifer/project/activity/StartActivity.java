package com.lucifer.project.activity;

import android.view.View;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.lucifer.project.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lucifer on 16/8/24.
 */
public class StartActivity extends BaseFragmentActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View view) {

    }

}
