package com.zc.degou.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.zc.degou.R;
import com.zc.degou.util.CacheUtil;

/**
 * Created by lucifer on 16/10/16.
 * <p>
 * 启动界面
 */

public class StartActivity extends BaseFragmentActivity {

    private ImageView img_start;

    private Animation mFadeIn;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        img_start = (ImageView) findViewById(R.id.imgStart);

        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_laucher_fade_in);
        img_start.startAnimation(mFadeIn);
    }

    @Override
    protected void initListener() {

        mFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(CacheUtil.getInstance(StartActivity.this).isLogin()) {
                    switchActivityAndFinish(MainActivity.class);
                } else {
                    switchActivityAndFinish(LoginActivity.class);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
