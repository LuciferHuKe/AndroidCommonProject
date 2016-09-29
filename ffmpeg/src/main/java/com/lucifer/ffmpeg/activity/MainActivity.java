package com.lucifer.ffmpeg.activity;


import android.view.View;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.lucifer.ffmpeg.R;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
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
                R.id.lyMainFFmpeg
        };
        for (int id : resIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.lyMainFFmpeg: {
                switchActivity(SDLActivity.class);
            }
            break;
            default:
                break;
        }

    }
}
