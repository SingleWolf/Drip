package com.walker.flexiblecore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Walker
 * @date on 2018/4/2 0002 下午 13:03
 * @email feitianwumu@163.com
 * @desc activity基类
 */
public abstract class AbstractBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onTransactBeforeContent();
        setContentView(getContentViewId());
        init(savedInstanceState);
    }

    /**
     * setContentView之前的处理操作
     */
    protected void onTransactBeforeContent() {

    }

    /**
     * 初始化
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 获取layout的id
     *
     * @return layout的id
     */
    protected abstract int getContentViewId();

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            execRelease();
        }
    }

    /**
     * 释放资源
     * 因为onDestroy（）方法执行太迟，顾在onStop（）中如果activity终止了就释放资源
     */
    protected void execRelease() {
    }

}
