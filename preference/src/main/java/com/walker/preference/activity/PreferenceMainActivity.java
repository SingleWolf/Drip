package com.walker.preference.activity;

import android.os.Bundle;

import com.walker.flexiblecore.base.AbstractBaseActivity;
import com.walker.preference.R;

public class PreferenceMainActivity extends AbstractBaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.preference_activity_main;
    }
}
