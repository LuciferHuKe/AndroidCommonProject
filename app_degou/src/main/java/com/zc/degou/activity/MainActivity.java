package com.zc.degou.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioGroup;

import com.lucifer.common.activity.BaseFragmentActivity;
import com.zc.degou.R;
import com.zc.degou.fragment.ActiveFragment;
import com.zc.degou.fragment.CategoryFragment;
import com.zc.degou.fragment.HomeFragment;
import com.zc.degou.fragment.MineFragment;
import com.zc.degou.fragment.ShopcartFragment;

/**
 * Created by lucifer on 16/10/20.
 * <p>
 * 主界面
 */

public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment mCurrentFragment;

    private FragmentManager mFragmentMana;

    private HomeFragment mHomeFragment;  // 首页
    private ActiveFragment mActiveFragment;  // 活动
    private CategoryFragment mCategoryFragment;  // 分类
    private ShopcartFragment mShopcartFragment;  // 购物车
    private MineFragment mMineFragment;  // 我

    private RadioGroup rg_main;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        rg_main = (RadioGroup) findViewById(R.id.rgMainTabbar);

        mHomeFragment = new HomeFragment();
        mActiveFragment = new ActiveFragment();
        mCategoryFragment = new CategoryFragment();
        mShopcartFragment = new ShopcartFragment();
        mMineFragment = new MineFragment();

        mFragmentMana = getSupportFragmentManager();
        mCurrentFragment = mHomeFragment;
        if (!mHomeFragment.isAdded()) {
            mFragmentMana.beginTransaction()
                    .add(R.id.flMain, mHomeFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    protected void initListener() {

        rg_main.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i) {
            case R.id.rbMainHome: {
                changeFragment(mCurrentFragment, mHomeFragment);
            }
            break;
            case R.id.rbMainActive: {
                changeFragment(mCurrentFragment, mActiveFragment);
            }
            break;
            case R.id.rbMainCategory: {
                changeFragment(mCurrentFragment, mCategoryFragment);
            }
            break;
            case R.id.rbMainShopcart: {
                changeFragment(mCurrentFragment, mShopcartFragment);
            }
            break;
            case R.id.rbMainMine: {
                changeFragment(mCurrentFragment, mMineFragment);
            }
            break;
            default:
                break;
        }
    }

    /**
     * Fragment切换
     *
     * @param currentFragment
     * @param targetFragment
     */
    private void changeFragment(Fragment currentFragment, Fragment targetFragment) {

        if (mCurrentFragment != targetFragment) {
            mCurrentFragment = targetFragment;
            if (!targetFragment.isAdded()) {
                mFragmentMana.beginTransaction().hide(currentFragment)
                        .add(R.id.flMain, targetFragment)
                        .commitAllowingStateLoss();
                mFragmentMana.beginTransaction().hide(currentFragment)
                        .show(targetFragment).commitAllowingStateLoss();
            } else {
                mFragmentMana.beginTransaction().hide(currentFragment)
                        .show(targetFragment).commitAllowingStateLoss();
            }
        }
    }

}
