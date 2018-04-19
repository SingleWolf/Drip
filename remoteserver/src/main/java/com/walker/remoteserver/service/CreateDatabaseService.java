package com.walker.remoteserver.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.walker.drip.bean.Summary;
import com.walker.flexiblecore.data.sp.SPHelper;
import com.walker.flexiblecore.util.ToastUtils;
import com.walker.remoteserver.constant.ModelConstants;
import com.walker.remoteserver.model.ExecDB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Walker
 * @date on 2018/4/11 0011 下午 16:32
 * @email feitianwumu@163.com
 * @desc 创建是数据库的服务线程
 */
public class CreateDatabaseService extends IntentService {
    private static final String TAG = "CreateDatabaseService";

    public CreateDatabaseService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int dbActionVersion = SPHelper.getInt("DB_ACTION_VERSION", 0);
        switch (dbActionVersion) {
            case 0:
                ExecDB db = new ExecDB(getApplicationContext());
                List<Summary> list = new ArrayList<>();
                Summary summary = new Summary();
                summary.setShow_id("test_1");
                summary.setAction_flag(1);
                summary.setActive("1");
                summary.setTitle("这是个测试");
                summary.setType(ModelConstants.TYPE_UI);
                summary.setCreate_date(String.valueOf(System.currentTimeMillis()));
                list.add(summary);
                boolean isResult = db.addSummary(list);
                SPHelper.put("DB_ACTION_VERSION", 1);
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
