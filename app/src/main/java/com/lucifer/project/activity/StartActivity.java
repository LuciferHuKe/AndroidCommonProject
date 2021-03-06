package com.lucifer.project.activity;

import android.view.View;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.lucifer.project.R;

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

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

        int[] resIds = {
                R.id.lyStartUI,
                R.id.lyStartNetwork
        };
        for (int id : resIds) {
            findViewById(id).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.lyStartUI: {

            }
            break;
            case R.id.lyStartNetwork: {
                switchActivity(NetworkActivity.class);
            }
            break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
