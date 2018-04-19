package com.walker.remoteserver.activity;

import android.os.Bundle;

import com.github.mzule.activityrouter.annotation.Router;
import com.walker.flexiblecore.base.AbstractBaseActivity;
import com.walker.remoteserver.R;

/**
 * @date on 2018/4/13 0013 下午 14:21
 * @author Walker
 * @email feitianwumu@163.com
 * @desc  remoteserver的主页面
 */
@Router("main")
public class RemoteMainActivity extends AbstractBaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.remote_activity_main;
    }
}
