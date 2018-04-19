package com.walker.remoteserver.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.walker.drip.bean.Summary;
import com.walker.remoteserver.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Walker
 * @date on 2018/4/11 0011 下午 19:19
 * @email feitianwumu@163.com
 * @desc 数据库执行者
 */
public class ExecDB extends WalkerDB {

    public ExecDB(Context context) {
        super(context);
    }

    /**
     * 添加概要
     *
     * @param list 数据集合
     * @return 操作成功与否
     */
    public boolean addSummary(List<Summary> list) {
        boolean result = false;
        try {
            getExecDB().beginTransaction();
            for (Summary entity : list) {
                ContentValues values = new ContentValues();
                values.put("show_id", entity.getShow_id());
                values.put("type", entity.getType());
                values.put("title", entity.getTitle());
                values.put("content", entity.getContent());
                values.put("link_url", entity.getLink_url());
                values.put("active", entity.getActive());
                values.put("action_flag", entity.getAction_flag());
                values.put("action_router", entity.getAction_router());
                values.put("create_date", entity.getCreate_date());
                values.put("update_date", entity.getUpdate_date());
                values.put("backup_1", entity.getBackup_1());
                values.put("backup_2", entity.getBackup_2());
                values.put("backup_3", entity.getBackup_3());
                long insert = getExecDB().insert("summary", null, values);
                if (0 < insert) {
                    result = true;
                }
            }
            getExecDB().setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("addSummary", e.toString());
        } finally {
            getExecDB().endTransaction();
        }
        return result;
    }

    /**
     * 获取示例
     *
     * @return ArrayList<Summary>
     */
    public ArrayList<Summary> listSummary() {
        ArrayList<Summary> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            String querySQL = mContext.getString(R.string.remote_get_all_summary);
            cursor = getExecDB().rawQuery(querySQL, null);
            while (cursor.moveToNext() == true) {
                Summary entity = new Summary();
                String show_id = cursor.getString(cursor
                        .getColumnIndex("show_id"));
                entity.setShow_id(show_id);
                int type = cursor.getInt(cursor
                        .getColumnIndex("type"));
                entity.setType(type);
                String title = cursor.getString(cursor
                        .getColumnIndex("title"));
                entity.setTitle(title);
                String content = cursor.getString(cursor
                        .getColumnIndex("content"));
                entity.setContent(content);
                String link_url = cursor.getString(cursor
                        .getColumnIndex("link_url"));
                entity.setLink_url(link_url);
                String active = cursor.getString(cursor
                        .getColumnIndex("active"));
                entity.setActive(active);
                int action_flag = cursor.getInt(cursor
                        .getColumnIndex("action_flag"));
                entity.setAction_flag(action_flag);
                String action_router = cursor.getString(cursor
                        .getColumnIndex("action_router"));
                entity.setAction_router(action_router);
                String create_date = cursor.getString(cursor
                        .getColumnIndex("create_date"));
                entity.setCreate_date(create_date);
                String update_date = cursor.getString(cursor
                        .getColumnIndex("update_date"));
                entity.setUpdate_date(update_date);
                String backup_1 = cursor.getString(cursor
                        .getColumnIndex("backup_1"));
                entity.setBackup_1(backup_1);
                String backup_2 = cursor.getString(cursor
                        .getColumnIndex("backup_2"));
                entity.setBackup_2(backup_2);
                String backup_3 = cursor.getString(cursor
                        .getColumnIndex("backup_3"));
                entity.setBackup_3(backup_3);
                list.add(entity);
            }
        } catch (Exception e) {
            Log.e("listSummary", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    /**
     * 根据类型获取相应摘要
     *
     * @param type 类型
     * @return ArrayList<Summary>
     */
    public ArrayList<Summary> listSummary(int type) {
        ArrayList<Summary> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            String querySQL = mContext.getString(R.string.remote_get_summary_by_type);
            cursor = getExecDB().rawQuery(querySQL, new String[]{String.valueOf(type)});
            while (cursor.moveToNext() == true) {
                Summary entity = new Summary();
                String show_id = cursor.getString(cursor
                        .getColumnIndex("show_id"));
                entity.setShow_id(show_id);
                int typeValue = cursor.getInt(cursor
                        .getColumnIndex("type"));
                entity.setType(typeValue);
                String title = cursor.getString(cursor
                        .getColumnIndex("title"));
                entity.setTitle(title);
                String content = cursor.getString(cursor
                        .getColumnIndex("content"));
                entity.setContent(content);
                String link_url = cursor.getString(cursor
                        .getColumnIndex("link_url"));
                entity.setLink_url(link_url);
                String active = cursor.getString(cursor
                        .getColumnIndex("active"));
                entity.setActive(active);
                int action_flag = cursor.getInt(cursor
                        .getColumnIndex("action_flag"));
                entity.setAction_flag(action_flag);
                String action_router = cursor.getString(cursor
                        .getColumnIndex("action_router"));
                entity.setAction_router(action_router);
                String create_date = cursor.getString(cursor
                        .getColumnIndex("create_date"));
                entity.setCreate_date(create_date);
                String update_date = cursor.getString(cursor
                        .getColumnIndex("update_date"));
                entity.setUpdate_date(update_date);
                String backup_1 = cursor.getString(cursor
                        .getColumnIndex("backup_1"));
                entity.setBackup_1(backup_1);
                String backup_2 = cursor.getString(cursor
                        .getColumnIndex("backup_2"));
                entity.setBackup_2(backup_2);
                String backup_3 = cursor.getString(cursor
                        .getColumnIndex("backup_3"));
                entity.setBackup_3(backup_3);
                list.add(entity);
            }
        } catch (Exception e) {
            Log.e("listSummary", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
}
