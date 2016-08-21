package com.example.jyw.twayprototype;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jyw.twayprototype.data.ChildItem;

/**
 * Created by JYW on 2016-08-21.
 */
public class NaviChildViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public ImageView toggleImageView;
    public LinearLayout layout;


    public NaviChildViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.text_title);
        toggleImageView = (ImageView) itemView.findViewById(R.id.view_expand_toggle);
        layout = (LinearLayout) itemView.findViewById(R.id.view_navi_child);
    }

    ChildItem item;
    public void setData(ChildItem item) {
        this.item = item;
        titleView.setText(item.title);
        toggleImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
    }
}
