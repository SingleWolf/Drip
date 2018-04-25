package com.walker.code.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.walker.code.adapter.SummaryAdapter;
import com.walker.code.delegate.OnFragmentInfoPipe;
import com.walker.drip.ISummaryManager;
import com.walker.drip.R;
import com.walker.drip.bean.Summary;
import com.walker.flexiblecore.base.AbstractBaseActivityFragment;
import com.walker.flexiblecore.delegate.OnRecyclerItemClickListener;
import com.walker.flexiblecore.util.ExecutorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Walker
 * @date on 2018/4/2 0002 下午 13:19
 * @email feitianwumu@163.com
 * @desc 主页tab中的ui项
 */

public class MainTabUiFragment extends AbstractBaseActivityFragment {
    @BindView(R.id.rvSummary)
    RecyclerView rvSummary;
    @BindView(R.id.srLayout)
    SwipeRefreshLayout srLayout;
    Unbinder unbinder;

    public static final String TAG = "MainTabUiFragment";
    private static final int CODE_REFRESH_VIEW = 1;

    private OnFragmentInfoPipe<ISummaryManager> mDelegate;
    private List<Summary> mSummaryList;
    private SummaryAdapter mAdapter;

    /**
     * 获取Fragment实例
     *
     * @return Fragment
     */
    public static MainTabUiFragment getInstance() {
        return new MainTabUiFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSummaryList = new ArrayList<>();
        getData();
        mAdapter = new SummaryAdapter(mSummaryList);
    }

    @Override
    protected void buildView(View baseView, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, baseView);
        initRecyclerView();
        initRefresh();
    }

    private void initRecyclerView() {
        rvSummary.setAdapter(mAdapter);
        rvSummary.addOnItemTouchListener(new OnRecyclerItemClickListener(rvSummary) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
    }

    private void initRefresh() {
        srLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.yellow, R.color.blue);
        srLayout.setSize(SwipeRefreshLayout.DEFAULT);
        srLayout.setProgressBackgroundColorSchemeResource(R.color.lightblue);
        //srLayout.setPadding(20, 20, 20, 20);
        srLayout.setProgressViewOffset(true, 50, 100);
        //srLayout.setDistanceToTriggerSync(50);
        //srLayout.setProgressViewEndTarget(true, 100);
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ExecutorUtils.executeTask(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        mHandler.sendEmptyMessage(CODE_REFRESH_VIEW);
                    }
                });
            }
        });
    }

    private void getData() {
        List<Summary> dataList = null;
        if (mDelegate != null) {
            ISummaryManager summaryManager = mDelegate.onInfoPipe();
            if (summaryManager != null) {
                try {
                    dataList = summaryManager.listSummary(1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        if (dataList != null) {
            mSummaryList.clear();
            mSummaryList.addAll(dataList);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.app_fragment_main_ui;
    }

    public void setInfoPipe(OnFragmentInfoPipe<ISummaryManager> delegate) {
        mDelegate = delegate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_REFRESH_VIEW:
                    mAdapter.notifyDataSetChanged();
                    srLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
}
