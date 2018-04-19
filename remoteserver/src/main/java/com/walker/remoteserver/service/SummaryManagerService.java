package com.walker.remoteserver.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.walker.drip.IOnErrorListener;
import com.walker.drip.ISummaryManager;
import com.walker.drip.bean.Summary;
import com.walker.remoteserver.model.ExecDB;

import java.util.List;

/**
 * @author Walker
 * @date on 2018/4/18 0018 下午 13:54
 * @email feitianwumu@163.com
 * @desc 摘要管理服务
 */
public class SummaryManagerService extends Service {
    private static final String TAG = "SummaryManagerService";

    private ExecDB mDB;
    //private CopyOnWriteArrayList<Summary> mSummaryList = new CopyOnWriteArrayList<>();
    /**
     * 跨进程接口管理器
     */
    private RemoteCallbackList<IOnErrorListener> mErrorListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new ISummaryManager.Stub() {

        @Override
        public List<Summary> listAllSummary() throws RemoteException {
            if(mDB==null){
                broadcastError(2, "db is null");
                return null;
            }
            return mDB.listSummary();
        }

        @Override
        public List<Summary> listSummary(int type) throws RemoteException {
            if(mDB==null){
                broadcastError(2, "db is null");
                return null;
            }
            return mDB.listSummary(type);
        }

        @Override
        public void setOnErrorListener(IOnErrorListener listener) throws RemoteException {
            if (listener != null) {
                mErrorListenerList.register(listener);
            }
        }

        @Override
        public void removeOnErrorListener(IOnErrorListener listener) throws RemoteException {
            if (listener != null) {
                mErrorListenerList.unregister(listener);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //使用权限验证，仅让通过权限验证的应用可与本服务通信
        int check = checkCallingOrSelfPermission("com.walker.drip.permission.ACCESS_REMOTE_SERVER");
        if (check == PackageManager.PERMISSION_DENIED) {
            //传递未通过权限验证的信息
            Log.i("SummaryManagerService", "no permission");
            return null;
        }
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mDB = new ExecDB(getApplicationContext());
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    /**
     * 传递error的信息
     *
     * @param code  编号
     * @param error 信息
     */
    private void broadcastError(int code, String error) {
        final int N = mErrorListenerList.beginBroadcast();
        for (int pos = 0; pos < N; pos++) {
            IOnErrorListener errorListener = mErrorListenerList.getBroadcastItem(pos);
            if (errorListener != null) {
                try {
                    errorListener.onError(code, error);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mErrorListenerList.finishBroadcast();
    }
}
