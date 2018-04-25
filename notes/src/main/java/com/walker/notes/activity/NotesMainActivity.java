package com.walker.notes.activity;

import android.os.Bundle;

import com.walker.flexiblecore.base.AbstractBaseActivity;
import com.walker.notes.R;

public class NotesMainActivity extends AbstractBaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.notes_activity_main;
    }
}
