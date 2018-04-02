package com.walker.flexiblecore.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @date on 2018/3/30 0030 下午 15:43
 * @author Walker
 * @email feitianwumu@163.com
 * @desc  fragment的基类
 */
public abstract class AbstractBaseFragment extends Fragment{
    private Context mHoldActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHoldActivity=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
