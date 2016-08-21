package com.example.jyw.twayprototype;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jyw.twayprototype.data.GroupItem;

/**
 * Created by JYW on 2016-08-21.
 */
public class NaviGroupViewHolder extends RecyclerView.ViewHolder {
    ImageView iconView, toggleImageView;
    TextView titleView;
    LinearLayout layout;
    View shadow;

    public NaviGroupViewHolder(View itemView) {
        super(itemView);
        iconView = (ImageView) itemView.findViewById(R.id.view_icon);
        titleView =(TextView) itemView.findViewById(R.id.text_title);
        toggleImageView = (ImageView) itemView.findViewById(R.id.view_expand_toggle);
        layout = (LinearLayout) itemView.findViewById(R.id.view_navi_group);
        shadow = (View) itemView.findViewById(R.id.view_shadow);
    }

    GroupItem item;
    public void setData(GroupItem item) {
        this.item = item;
        iconView.setImageDrawable(item.icon);
        titleView.setText(item.title);
    }
}
