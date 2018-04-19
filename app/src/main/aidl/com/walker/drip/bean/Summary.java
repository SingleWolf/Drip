package com.walker.drip.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @date on 2018/4/11 0011 下午 16:01
 * @author Walker
 * @email feitianwumu@163.com
 * @desc  摘要
 */

public class Summary implements Parcelable{
    private String show_id;
    private int type;
    private String title;
    private String content;
    private String link_url;
    private String active;
    private int action_flag;
    private String action_router;
    private String create_date;
    private String update_date;
    private String backup_1;
    private String backup_2;
    private String backup_3;

    public Summary() {
    }

    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getAction_flag() {
        return action_flag;
    }

    public void setAction_flag(int action_flag) {
        this.action_flag = action_flag;
    }

    public String getAction_router() {
        return action_router;
    }

    public void setAction_router(String action_router) {
        this.action_router = action_router;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getBackup_1() {
        return backup_1;
    }

    public void setBackup_1(String backup_1) {
        this.backup_1 = backup_1;
    }

    public String getBackup_2() {
        return backup_2;
    }

    public void setBackup_2(String backup_2) {
        this.backup_2 = backup_2;
    }

    public String getBackup_3() {
        return backup_3;
    }

    public void setBackup_3(String backup_3) {
        this.backup_3 = backup_3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(show_id);
        dest.writeInt(type);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(link_url);
        dest.writeString(active);
        dest.writeInt(action_flag);
        dest.writeString(action_router);
        dest.writeString(create_date);
        dest.writeString(update_date);
        dest.writeString(backup_1);
        dest.writeString(backup_2);
        dest.writeString(backup_3);
    }

    public static final Creator<Summary> CREATOR =new Creator<Summary>(){
        @Override
        public Summary createFromParcel(Parcel source) {
            return new Summary(source);
        }

        @Override
        public Summary[] newArray(int size) {
            return new Summary[size];
        }
    };

    private Summary(Parcel source) {
        show_id=source.readString();
        type=source.readInt();
        title=source.readString();
        content=source.readString();
        link_url=source.readString();
        active=source.readString();
        action_flag=source.readInt();
        action_router=source.readString();
        create_date=source.readString();
        update_date=source.readString();
        backup_1=source.readString();
        backup_2=source.readString();
        backup_3=source.readString();
    }
}
