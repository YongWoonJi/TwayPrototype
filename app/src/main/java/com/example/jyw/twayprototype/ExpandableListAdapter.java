package com.example.jyw.twayprototype;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jyw.twayprototype.data.ChildItem;
import com.example.jyw.twayprototype.data.GrandChildItem;
import com.example.jyw.twayprototype.data.GroupItem;
import com.example.jyw.twayprototype.data.NaviItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JYW on 2016-08-21.
 */
public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int GROUP = 1;
    public static final int CHILD = 2;
    public static final int GRANDCHILLD = 3;

    private List<NaviItem> data;

    public ExpandableListAdapter (List<NaviItem> data) {
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int grandChildItemPaddingLeft = (int) (30 * dp);
        int grandChildItemPaddingTopAndBotom = (int) (14 * dp);

        switch (viewType) {
            case HEADER : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_navi_header, parent, false);
                return new RecyclerView.ViewHolder(view) {};
            }
            case GROUP : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_navi_group, parent, false);
                NaviGroupViewHolder holder = new NaviGroupViewHolder(view);
                return holder;
            }
            case CHILD : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_navi_child, parent, false);
                NaviChildViewHolder holder = new NaviChildViewHolder(view);
                return holder;
            }
            case GRANDCHILLD : {
                TextView view = new TextView(context);
                view.setPadding(grandChildItemPaddingLeft, grandChildItemPaddingTopAndBotom, 0, grandChildItemPaddingTopAndBotom);
                view.setTextColor(Color.parseColor("#898989"));
                view.setBackgroundColor(Color.parseColor("#CACACA"));
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new RecyclerView.ViewHolder(view) {};
            }
        }
        return null;
    }


    boolean grandChildExist = false;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NaviItem item = data.get(position);

        switch (item.type) {
            case GROUP : {
                final GroupItem groupItem = (GroupItem) item;
                final NaviGroupViewHolder nvh = (NaviGroupViewHolder) holder;
                nvh.setData(groupItem);
                if (!groupItem.isHaveChild) {
                    nvh.toggleImageView.setVisibility(View.GONE);
                } else {
                    nvh.toggleImageView.setVisibility(View.VISIBLE);
                    nvh.layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (groupItem.children == null) {
                                groupItem.children = new ArrayList<>();
                                int count = 0;
                                int pos = data.indexOf(nvh.item);
                                if (grandChildExist) {
                                    int scount = 0;
                                    while (pos + 1 < data.size() && data.get(pos + 1).type != GROUP) {
                                        NaviItem item = data.remove(pos + 1);
                                        scount++;
                                        if (item instanceof ChildItem) {
                                            groupItem.children.add((ChildItem) item);
                                        } else {
                                            if (groupItem.children.get(groupItem.children.size() - 1).children == null) {
                                                groupItem.children.get(groupItem.children.size() - 1).children = new ArrayList<>();
                                            }
                                            groupItem.children.get(groupItem.children.size() - 1).children.add((GrandChildItem) item);
                                        }
                                    }
                                    grandChildExist = false;
                                    notifyItemRangeRemoved(pos + 1, scount);
                                } else {
                                    while (pos + 1 < data.size() && data.get(pos + 1).type == CHILD) {
                                        groupItem.children.add((ChildItem) data.remove(pos + 1));
                                        count++;
                                    }
                                    notifyItemRangeRemoved(pos + 1, count);
                                }

                                nvh.toggleImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
                                nvh.shadow.setVisibility(View.GONE);
                            } else {
                                int pos = data.indexOf(nvh.item);
                                int index = pos + 1;
                                for (ChildItem child : groupItem.children) {
                                    data.add(index, child);
                                    index++;
                                }
                                notifyItemRangeInserted(pos + 1, index - pos - 1);
                                nvh.toggleImageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
                                nvh.shadow.setVisibility(View.VISIBLE);
                                groupItem.children = null;
                            }
                        }
                    });
                }
                break;
            }
            case CHILD : {
                final ChildItem childItem = (ChildItem) item;
                final NaviChildViewHolder cvh = (NaviChildViewHolder) holder;
                cvh.setData(childItem);
                if (!childItem.isHaveChild) {
                    cvh.toggleImageView.setVisibility(View.GONE);
                } else {
                    cvh.toggleImageView.setVisibility(View.VISIBLE);
                    cvh.layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (childItem.isHaveChild) {
                                if (childItem.children == null) {
                                    childItem.children = new ArrayList<>();
                                    int count = 0;
                                    int pos = data.indexOf(cvh.item);
                                    while (pos + 1 < data.size() && data.get(pos + 1).type == GRANDCHILLD) {
                                        childItem.children.add((GrandChildItem) data.remove(pos + 1));
                                        count++;
                                    }
                                    notifyItemRangeRemoved(pos + 1, count);
                                    cvh.toggleImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
                                    grandChildExist = false;
                                } else {
                                    int pos = data.indexOf(cvh.item);
                                    int index = pos + 1;
                                    for (GrandChildItem child : childItem.children) {
                                        data.add(index, child);
                                        index++;
                                    }
                                    notifyItemRangeInserted(pos + 1, index - pos - 1);
                                    cvh.toggleImageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
                                    childItem.children = null;
                                    grandChildExist = true;
                                }
                            }
                        }
                    });
                }
                break;
            }
            case GRANDCHILLD : {
                GrandChildItem grandChildItem = (GrandChildItem) item;
                TextView tv = (TextView) holder.itemView;
                tv.setText(grandChildItem.title);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }
}
