package com.example.jyw.twayprototype.data;

import java.util.List;

/**
 * Created by JYW on 2016-08-21.
 */
public class ChildItem extends NaviItem{
    public String title;
    public List<GrandChildItem> children;
    public boolean isHaveChild;

    public ChildItem(int type, String title, boolean isHaveChild) {
        this.type = type;
        this.title = title;
        this.isHaveChild = isHaveChild;
    }
}
