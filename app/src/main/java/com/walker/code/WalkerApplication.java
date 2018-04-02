package com.walker.code;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.walker.flexiblecore.exception.CrashHandler;
import com.walker.flexiblecore.exception.OnCrashListener;
import com.walker.flexiblecore.util.ToastUtils;

/**
 * @author Walker
 * @date on 2018/3/30 0030 下午 13:11
 * @email feitianwumu@163.com
 * @desc Application
 */
public class WalkerApplication extends Application {
    /**
     * application
     */
    private static WalkerApplication sInstance;

    /**
     * 获取实例
     *
     * @return WalkerApplication
     */
    public static WalkerApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        sInstance = this;
        ToastUtils.init(this);
        //为应用设置异常处理，然后程序才能获取未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(new OnCrashListener() {
            @Override
            public void onTransact(Throwable e) {
                ToastUtils.showCenterShort(e.toString());
            }
        });
    }
}
