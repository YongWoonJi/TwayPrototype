package com.example.jyw.twayprototype;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jyw.twayprototype.data.EventData;

import java.util.List;

/**
 * Created by JYW on 2016-08-20.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<EventData> items;
    Context context;

    public void addItems(List<EventData> items) {
        if (this.items != items) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_MAIN_VIEWHOLDER = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_TYPE_HEADER;
        return VIEW_TYPE_MAIN_VIEWHOLDER;
    }


    FrameLayout searchContainer;
    TextView cancelView;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_main_search, parent, false);
                searchContainer = (FrameLayout) view.findViewById(R.id.search_container);
                searchContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (searchContainer.getVisibility() == View.VISIBLE) {
                            searchContainer.setVisibility(View.GONE);
                        }
                    }
                });
                cancelView = (TextView) view.findViewById(R.id.text_cancel);
                cancelView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (searchContainer.getVisibility() == View.GONE) {
                            searchContainer.setVisibility(View.VISIBLE);
                        }
                    }
                });

                MainSearchViewHolder holder = new MainSearchViewHolder(view);
                return holder;
            }
            case VIEW_TYPE_MAIN_VIEWHOLDER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_main, parent, false);
                MainViewHolder holder = new MainViewHolder(view);
                return holder;
            }
        }
        throw new IllegalArgumentException("invalid viewType");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
                return;
            case 1: {
                MainViewHolder mvh = (MainViewHolder) holder;
                EventData data = new EventData();
                data.writeTime = "2016/08/18 오후 05:44";
                data.content = "[티타임 EPISODE 13]\n\n안녕하세요! 티웨이항공 입니다\n티웨이항공 승무원이 직접 참여하여\n만드는 소소한 컨텐츠...";
                data.picture = ContextCompat.getDrawable(context, R.drawable.rv_content1);
                data.smsNum = "2";
                data.favoriteNum = "4";
                data.messageId = "밍밍메구미";
                data.message = "이쁜 손톱도 좋지만 너무 길면 승객들이 위험해요...";
                data.messageTime = "2016/08/18 오후 10:50";
                mvh.setData(data);
                return;
            }
            case 2: {
                MainViewHolder mvh = (MainViewHolder) holder;
                EventData data = new EventData();
                data.writeTime = "2016/08/17 오후 01:50";
                data.content = "[여행정보-후쿠오카]\n9월 1일 대구-후쿠오카 신규취항!!\n인천에 이어 대구에서도\n후쿠오카행 비행이 시작됩니다^^\n후쿠오...";
                data.picture = ContextCompat.getDrawable(context, R.drawable.rv_content4);
                data.smsNum = "3";
                data.favoriteNum = "4";
                data.messageId = "건진";
                data.message = "티웨이 이번 8월 얼리버드 다음주 22 23월화에...";
                data.messageTime = "2016/08/17 오전 10:01";
                mvh.setData(data);
                return;
            }
            case 3: {
                MainViewHolder mvh = (MainViewHolder) holder;
                EventData data = new EventData();
                data.writeTime = "2016/08/10 오후 04:41";
                data.content = "[이벤트-대구 야구장 이벤트]\n9월 1일!! 대구-도쿄, 대수-후쿠오카 신규취항!\n화려함과 낭만이 함께하는 도시 도쿄와\n힐링...";
                data.picture = ContextCompat.getDrawable(context, R.drawable.rv_content3);
                data.smsNum = "87";
                data.favoriteNum = "10";
                data.messageId = "레아";
                data.message = "덕분에 도쿄 편하게 가게되네요 10월에 출발!!!...";
                data.messageTime = "2016/08/18 오후 10:04";
                mvh.setData(data);
                return;
            }
            case 4:{
                MainViewHolder mvh = (MainViewHolder) holder;
                EventData data = new EventData();
                data.writeTime = "2016/08/02 오후 07:03";
                data.content = "[정보-티웨이x트래블라인 제주 100배 즐기기!]\n카카오 여행 랭킹 서비스 트래블라인과 함께하는\n제주도의 드라이브 코스 랭킹...";
                data.picture = ContextCompat.getDrawable(context, R.drawable.rv_content2);
                data.smsNum = "3";
                data.favoriteNum = "4";
                data.messageId = "제주 느영나영";
                data.message = "제주여행은 느영나영";
                data.messageTime = "2016/08/17 오전 10:01";
                mvh.setData(data);
                return;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
