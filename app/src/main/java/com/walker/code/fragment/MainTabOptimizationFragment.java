package com.walker.code.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.walker.drip.R;
import com.walker.flexiblecore.base.AbstractBaseActivityFragment;

/**
 * @author Walker
 * @date on 2018/4/2 0002 下午 13:19
 * @email feitianwumu@163.com
 * @desc 主页tab中的优化页面
 */

public class MainTabOptimizationFragment extends AbstractBaseActivityFragment {
    public static final String TAG = "MainTabOptimizationFragment";
    /**
     * 获取Fragment实例
     *
     * @return Fragment
     */
    public static Fragment getInstance() {
        return new MainTabOptimizationFragment();
    }

    @Override
    protected void buildView(View baseView, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.app_fragment_main_optimization;
    }

}
