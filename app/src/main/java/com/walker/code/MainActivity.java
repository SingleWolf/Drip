package com.walker.code;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.walker.drip.R;
import com.walker.flexiblecore.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ButterKnife.bind(this);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tabUi:
                        ToastUtils.showShort("UI");
                        break;
                    case R.id.tabOptimization:
                        ToastUtils.showShort("Optimization");
                        break;
                    case R.id.tabPreference:
                        ToastUtils.showShort("Preference");
                        break;
                    case R.id.tabNotes:
                        ToastUtils.showShort("Notes");
                        break;
                    default:
                        break;
                }
            }
        });
        //listen for reselection events
        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tabUi:
                        Toast.makeText(WalkerApplication.getInstance(), "ReSelected UI", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tabOptimization:
                        ToastUtils.showCenterShort("Optimization");
                        break;
                    case R.id.tabPreference:
                        Toast.makeText(MainActivity.this, "ReSelected Preference", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tabNotes:
                        Toast.makeText(MainActivity.this, "ReSelected Notes", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
