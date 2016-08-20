package com.example.jyw.twayprototype;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jyw.twayprototype.data.EventData;

/**
 * Created by JYW on 2016-08-19.
 */
public class MainViewHolder extends RecyclerView.ViewHolder {

    ImageView pictureView;
    TextView titleView, writeTimeView, contentView, smsNumView, favoriteView, idView, messageView, messageTimeView;

    public MainViewHolder(View itemView) {
        super(itemView);
        pictureView = (ImageView) itemView.findViewById(R.id.view_recycler_picture);
        titleView = (TextView) itemView.findViewById(R.id.text_title);
        writeTimeView = (TextView) itemView.findViewById(R.id.text_writeTime);
        contentView = (TextView) itemView.findViewById(R.id.text_content);
        smsNumView = (TextView) itemView.findViewById(R.id.text_sms_num);
        favoriteView = (TextView) itemView.findViewById(R.id.text_favorite_num);
        idView = (TextView) itemView.findViewById(R.id.text_id);
        messageView = (TextView) itemView.findViewById(R.id.text_message);
        messageTimeView = (TextView) itemView.findViewById(R.id.text_message_time);
    }

    EventData data;
    public void setData(EventData data) {
        this.data = data;
        pictureView.setImageDrawable(data.picture);
        titleView.setText(data.title);
        writeTimeView.setText(data.writeTime);
        contentView.setText(data.content);
        smsNumView.setText(data.smsNum);
        favoriteView.setText(data.favoriteNum);
        idView.setText(data.messageId);
        messageView.setText(data.message);
        messageTimeView.setText(data.messageTime);
    }
}
