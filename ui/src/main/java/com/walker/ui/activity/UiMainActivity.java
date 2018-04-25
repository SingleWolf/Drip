package com.walker.ui.activity;

import android.os.Bundle;

import com.walker.flexiblecore.base.AbstractBaseActivity;
import com.walker.ui.R;

public class UiMainActivity extends AbstractBaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.ui_activity_main;
    }
}
