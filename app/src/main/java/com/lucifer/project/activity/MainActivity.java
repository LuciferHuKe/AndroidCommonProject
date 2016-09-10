package com.lucifer.project.activity;

import android.view.View;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.lucifer.common.model.EventMessage;
import com.lucifer.project.ConstantValue;
import com.lucifer.project.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by lucifer on 16/8/26.
 *
 * 主界面
 */
public class MainActivity extends BaseFragmentActivity {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
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

        int[] resIds = {
                R.id.btnMainSend
        };
        for (int id : resIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnMainSend: {
                EventMessage message = new EventMessage();
                message.setAction(ConstantValue.EVENT_ACTION_START);
                EventBus.getDefault().post(message);
                finish();
            }
            break;
            default:
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(EventMessage message) {

    }

}
