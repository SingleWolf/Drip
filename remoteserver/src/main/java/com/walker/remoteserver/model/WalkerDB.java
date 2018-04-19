package com.walker.remoteserver.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.walker.flexiblecore.data.sqlite.BaseDB;
import com.walker.flexiblecore.data.storage.StorageHelper;
import com.walker.flexiblecore.util.StringBuilderUtils;

import java.io.File;

/**
 * @author Walker
 * @date on 2018/4/11 0011 下午 18:47
 * @email feitianwumu@163.com
 * @desc 数据库操作
 */
public class WalkerDB extends BaseDB {
    protected Context mContext;

    public WalkerDB(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sqlCreateSummary = mContext.getApplicationContext().getString(R.string.remote_create_table_summary);
        String sqlCreateSummary = "create table summary (\n" +
                "show_id text not null,\n" +
                "type integer,\n" +
                "title text not null,\n" +
                "content text,\n" +
                "link_url text,\n" +
                "active text default '1',\n" +
                "action_router text,\n" +
                "action_flag integer,\n" +
                "create_date text not null,\n" +
                "update_date text,\n" +
                "backup_1 text,\n" +
                "backup_2 text,\n" +
                "backup_3 text,\n" +
                "constraint summary_primary primary key (show_id)\n" +
                ");";
        db.execSQL(sqlCreateSummary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    protected String setDbPath() {
        return StringBuilderUtils.pliceStr(StorageHelper.getRootPath(), File.separator,"database",File.separator,"drip.db");
    }

    @Override
    protected int setDbVersion() {
        return 1;
    }
}
