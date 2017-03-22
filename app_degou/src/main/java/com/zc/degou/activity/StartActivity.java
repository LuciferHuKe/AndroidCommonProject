package com.zc.degou.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.zc.degou.R;
import com.zc.degou.network.socket.TcpDataService;
import com.zc.degou.util.CacheUtil;

import java.util.List;

/**
 * Created by lucifer on 16/10/16.
 * <p>
 * 启动界面
 */

public class StartActivity extends BaseFragmentActivity {

    private ImageView img_start;

    private Animation mFadeIn;

    private Intent startServiceIntent;  // 启动网络服务

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initData() {

        if (!isServiceRunning(StartActivity.this,
                TcpDataService.class.getName())) {
            startServiceIntent = new Intent(this, TcpDataService.class);
            startService(startServiceIntent);
        } else {
            startServiceIntent = new Intent(this, TcpDataService.class);
            stopService(startServiceIntent);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startService(startServiceIntent);
        }

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

    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(500);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            // System.out.println(serviceList.get(i).service.getClassName());
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

}
