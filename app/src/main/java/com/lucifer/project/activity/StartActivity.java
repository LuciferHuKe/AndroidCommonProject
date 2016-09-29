package com.lucifer.project.activity;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucifer.common.activity.BaseFragmentActivity;
import com.lucifer.common.model.EventMessage;
import com.lucifer.common.uitl.LogUtil;
import com.lucifer.project.ConstantValue;
import com.lucifer.project.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucifer on 16/8/24.
 */
public class StartActivity extends BaseFragmentActivity {

    private TextView tv_start;

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

        tv_start = (TextView) findViewById(R.id.tvStartMsg);

        switchActivity(MainActivity.class);

        List<Object> picList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String picUrl = "http://www.baidu.com" + i;
            picList.add(picUrl);
        }

        Gson gson = new Gson();
        String jsonData = gson.toJson(picList);

        List<Object> picDetailList = gson.fromJson(jsonData, new TypeToken<List<Object>>() {
        }.getType());

        LogUtil.info("PIC DETAIL LIST SIZE : " + picDetailList.size());

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(EventMessage message) {

        switch (message.getAction()) {
            case ConstantValue.EVENT_ACTION_START: {
                tv_start.setText("HELLO WORLD");
            }
            break;
            default:
                break;
        }
    }

}
