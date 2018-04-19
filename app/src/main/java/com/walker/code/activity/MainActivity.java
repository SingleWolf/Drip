package com.walker.code.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.haha.perflib.Main;
import com.walker.code.delegate.OnFragmentInfoPipe;
import com.walker.code.fragment.MainTabNotesFragment;
import com.walker.code.fragment.MainTabOptimizationFragment;
import com.walker.code.fragment.MainTabPreferenceFragment;
import com.walker.code.fragment.MainTabUiFragment;
import com.walker.drip.IOnErrorListener;
import com.walker.drip.ISummaryManager;
import com.walker.drip.R;
import com.walker.drip.bean.Summary;
import com.walker.flexiblecore.base.AbstractBaseFragmentActivity;
import com.walker.flexiblecore.util.ToastUtils;
import com.walker.remoteserver.service.CreateDatabaseService;
import com.walker.remoteserver.service.SummaryManagerService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AbstractBaseFragmentActivity implements OnFragmentInfoPipe<ISummaryManager> {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private ISummaryManager mISummaryManager;
    private List<Summary> mSummaryList;

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        Intent intent = new Intent(this, CreateDatabaseService.class);
        startService(intent);

        binderRemoteServer();

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                initBottomBar();
            }
        });
    }

    /**
     * 绑定远程服务
     */
    private void binderRemoteServer() {
        Intent summaryManager = new Intent(this, SummaryManagerService.class);
        bindService(summaryManager, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mISummaryManager!=null&&mISummaryManager.asBinder().isBinderAlive()){
            try {
                //移出异常监听
                mISummaryManager.removeOnErrorListener(mErrorListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
    }

    private void initBottomBar() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tabUi:
                        if (getFragmentByTag(MainTabUiFragment.TAG) != null) {
                            addFragment((Fragment) getFragmentByTag(MainTabUiFragment.TAG), MainTabUiFragment.TAG);
                        } else {
                            MainTabUiFragment uiFragment = MainTabUiFragment.getInstance();
                            uiFragment.setInfoPipe(MainActivity.this);
                            addFragment(uiFragment, MainTabUiFragment.TAG);
                        }
                        break;
                    case R.id.tabOptimization:
                        if (getFragmentByTag(MainTabOptimizationFragment.TAG) != null) {
                            addFragment((Fragment) getFragmentByTag(MainTabOptimizationFragment.TAG), MainTabOptimizationFragment.TAG);
                        } else {
                            addFragment(MainTabOptimizationFragment.getInstance(), MainTabOptimizationFragment.TAG);
                        }
                        break;
                    case R.id.tabPreference:
                        if (getFragmentByTag(MainTabPreferenceFragment.TAG) != null) {
                            addFragment((Fragment) getFragmentByTag(MainTabPreferenceFragment.TAG), MainTabPreferenceFragment.TAG);
                        } else {
                            addFragment(MainTabPreferenceFragment.getInstance(), MainTabPreferenceFragment.TAG);
                        }
                        break;
                    case R.id.tabNotes:
                        if (getFragmentByTag(MainTabNotesFragment.TAG) != null) {
                            addFragment((Fragment) getFragmentByTag(MainTabNotesFragment.TAG), MainTabNotesFragment.TAG);
                        } else {
                            addFragment(MainTabNotesFragment.getInstance(), MainTabNotesFragment.TAG);
                        }
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
                        break;
                    case R.id.tabOptimization:
                        ToastUtils.showCenterShort("Optimization");
                        break;
                    case R.id.tabPreference:
                        break;
                    case R.id.tabNotes:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.app_activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.flContent;
    }

    /**
     * 根据标签获取对应的fragment
     *
     * @param tag fragment的标签
     * @return Object
     */
    private Object getFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mISummaryManager = ISummaryManager.Stub.asInterface(service);
            try {
                //设置异常监听
                mISummaryManager.setOnErrorListener(mErrorListener);
                //设置死亡代理
                service.linkToDeath(mDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mISummaryManager = null;
            //也可以在此方法中重新绑定远程服务
            //binderRemoteServer();
        }
    };

    /**
     * 异常监听器
     */
    private IOnErrorListener mErrorListener=new IOnErrorListener.Stub(){

        @Override
        public void onError(int code, String error) throws RemoteException {
            Log.e("IOnErrorListener", error+"\n");
        }
    };

    /**
     * binder的死亡接收器
     * 当binder死亡时，系统回调binderDied方法，这里移出之前绑定的binder死亡代理，然后重新绑定远程服务
     */
    private IBinder.DeathRecipient mDeathRecipient=new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mISummaryManager==null){
                return;
            }
            //移出之前绑定的binder死亡代理
            mISummaryManager.asBinder().unlinkToDeath(mDeathRecipient,0);
            mISummaryManager=null;
            //重新绑定远程服务
            binderRemoteServer();
        }
    };

    @Override
    public ISummaryManager onInfoPipe() {
        return mISummaryManager;
    }
}
