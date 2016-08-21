package com.example.jyw.twayprototype.data;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by JYW on 2016-08-21.
 */
public class GroupItem extends NaviItem{
    public Drawable icon;
    public String title;
    public List<ChildItem> children;
    public boolean isHaveChild;

    public GroupItem(int type, String title, Drawable icon, boolean isHaveChild) {
        this.type = type;
        this.title = title;
        this.icon = icon;
        this.isHaveChild = isHaveChild;
    }
}
