package com.walker.code.fragment;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walker.code.delegate.OnFragmentInfoPipe;
import com.walker.drip.ISummaryManager;
import com.walker.drip.R;
import com.walker.drip.bean.Summary;
import com.walker.flexiblecore.base.AbstractBaseActivityFragment;

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

    @BindView(R.id.tvShow)
    TextView tvShow;
    Unbinder unbinder;

    public static final String TAG = "MainTabUiFragment";

    private OnFragmentInfoPipe<ISummaryManager> mDelegate;

    /**
     * 获取Fragment实例
     *
     * @return Fragment
     */
    public static MainTabUiFragment getInstance() {
        return new MainTabUiFragment();
    }

    @Override
    protected void buildView(View baseView, Bundle savedInstanceState) {
        Log.i("MainTabUiFragment", "buildView\n");
        unbinder = ButterKnife.bind(this, baseView);
        if (mDelegate != null) {
            ISummaryManager summaryManager = mDelegate.onInfoPipe();
            if (summaryManager != null) {
                try {
                    List<Summary> dataList = summaryManager.listSummary(1);
                    if (dataList != null && dataList.isEmpty() == false) {
                        tvShow.setText(dataList.get(0).getTitle());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
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
}
