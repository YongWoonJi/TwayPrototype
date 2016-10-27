package com.example.jyw.twayprototype;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jyw.twayprototype.data.ChildItem;
import com.example.jyw.twayprototype.data.GrandChildItem;
import com.example.jyw.twayprototype.data.GroupItem;
import com.example.jyw.twayprototype.data.NaviItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NaviFragment extends Fragment {

    public static final int HEADER = 0;
    public static final int GROUP = 1;
    public static final int CHILD = 2;
    public static final int GRANDCHILLD = 3;

    private OnMenuSelectListener mListener;

    RecyclerView rv_list;
    ExpandableListAdapter mAdapter;


    public NaviFragment() {
        // Required empty public constructor
    }

    public interface OnMenuSelectListener {
        void onMenuSelected(int menuId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<NaviItem> data = new ArrayList<>();
        Context context = getActivity();

        NaviItem header = new NaviItem();
        header.type = HEADER;

        GroupItem group1 = new GroupItem(GROUP, "항공권 예매", ContextCompat.getDrawable(context, R.drawable.ic_flight_black_24dp), true);
        group1.children = new ArrayList<>();
        group1.children.add(new ChildItem(CHILD, "항공권 예매", false));
        group1.children.add(new ChildItem(CHILD, "최저가항공권", true));
        group1.children.add(new ChildItem(CHILD, "운항일정", true));
        group1.children.add(new ChildItem(CHILD, "운임안내", true));

        group1.children.get(1).children = new ArrayList<>();  // 최저가항공권
        group1.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "최저가달력"));
        group1.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "얼리버드 이벤트"));
        group1.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "최저가 그래프"));

        group1.children.get(2).children = new ArrayList<>();  // 운항일정
        group1.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "운항 스케줄"));
        group1.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "출도착 조회"));

        group1.children.get(3).children = new ArrayList<>();  // 운임안내
        group1.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "국내선 운임안내"));
        group1.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "국제선 운임안내"));





        GroupItem group2 = new GroupItem(GROUP, "마이페이지", ContextCompat.getDrawable(context, R.drawable.ic_account_circle_black_24dp), true);
        group2.children = new ArrayList<>();
        group2.children.add(new ChildItem(CHILD, "예약관리", true));
        group2.children.add(new ChildItem(CHILD, "개인정보 관리", true));
        group2.children.add(new ChildItem(CHILD, "혜택 관리", true));
        group2.children.add(new ChildItem(CHILD, "참여내역 관리", true));

        group2.children.get(0).children = new ArrayList<>();  // 예약관리
        group2.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "항공권 예약관리"));
        group2.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "에어텔 예약관리"));
        group2.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "국내선 웹체크인"));

        group2.children.get(1).children = new ArrayList<>();  // 개인정보 관리
        group2.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "개인정보"));
        group2.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "탑승자정보"));
        group2.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "희망여행지"));

        group2.children.get(2).children = new ArrayList<>();  // 혜택 관리
        group2.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "내 쿠폰함"));

        group2.children.get(3).children = new ArrayList<>();  // 참여내역 관리
        group2.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "문의합니다"));
        group2.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "U\'Story"));
        group2.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "칭찬합니다"));
        group2.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "개선해주세요"));



        GroupItem group3 = new GroupItem(GROUP, "서비스안내", ContextCompat.getDrawable(context, R.drawable.ic_label_outline_black_24dp), true);
        group3.children = new ArrayList<>();
        group3.children.add(new ChildItem(CHILD, "항공권", true));
        group3.children.add(new ChildItem(CHILD, "공항에서", true));
        group3.children.add(new ChildItem(CHILD, "수하물 보내기", true));
        group3.children.add(new ChildItem(CHILD, "기내에서", true));
        group3.children.add(new ChildItem(CHILD, "쿠폰", true));

        group3.children.get(0).children = new ArrayList<>();  // 항공권
        group3.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "항공권 예매"));
        group3.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "웹체크인"));

        group3.children.get(1).children = new ArrayList<>();  // 공항에서
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "탑승 수속 안내"));
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "사전좌석지정 서비스"));
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "옆 좌석 구매서비스"));
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "공항 카운터 정보"));
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "도움이 필요한 고객"));
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "서식 다운로드"));
        group3.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "출입국 신고서"));

        group3.children.get(2).children = new ArrayList<>();  // 수하물 보내기
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "무료수하물"));
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "초과수하물"));
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "특수수하물"));
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "운송제한물품"));
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "유료아이템"));
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "수하물 배상"));
        group3.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "유실물 센터"));


        group3.children.get(3).children = new ArrayList<>();  // 기내에서
        group3.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "항공사 소개"));
        group3.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "기내 제공서비스"));
        group3.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "기내 이벤트"));
        group3.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "기내식 주문 예약"));
        group3.children.get(3).children.add(new GrandChildItem(GRANDCHILLD, "기내 쇼핑"));

        group3.children.get(4).children = new ArrayList<>();  // 쿠폰
        group3.children.get(4).children.add(new GrandChildItem(GRANDCHILLD, "쿠폰 안내"));




        GroupItem group4 = new GroupItem(GROUP, "t\'ravel", ContextCompat.getDrawable(context, R.drawable.ic_business_black_24dp), true);
        group4.children = new ArrayList<>();
        group4.children.add(new ChildItem(CHILD, "에어텔", true));
        group4.children.add(new ChildItem(CHILD, "t\'pass 제휴할인", true));

        group4.children.get(0).children = new ArrayList<>();  // 에어텔
        group4.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "에어텔 메인"));
        group4.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "오사카"));
        group4.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "방콕"));
        group4.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "제주(김포출발)"));

        group4.children.get(1).children = new ArrayList<>();  // t'pass 제휴할인
        group4.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "관광"));
        group4.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "맛집"));
        group4.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "제휴상품"));




        GroupItem group5 = new GroupItem(GROUP, "t\'ogether", ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp), true);
        group5.children = new ArrayList<>();
        group5.children.add(new ChildItem(CHILD, "이벤트", true));
        group5.children.add(new ChildItem(CHILD, "U\'story", true));
        group5.children.add(new ChildItem(CHILD, "부토여행기", true));
        group5.children.add(new ChildItem(CHILD, "쿠폰북", false));
        group5.children.add(new ChildItem(CHILD, "공지사항", false));

        group5.children.get(0).children = new ArrayList<>();  // 이벤트
        group5.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "진행중인 이벤트"));
        group5.children.get(0).children.add(new GrandChildItem(GRANDCHILLD, "지난 이벤트"));

        group5.children.get(1).children = new ArrayList<>();  // U'story
        group5.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "이벤트 안내"));
        group5.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "이벤트 신청"));
        group5.children.get(1).children.add(new GrandChildItem(GRANDCHILLD, "이벤트 후기"));

        group5.children.get(2).children = new ArrayList<>();  // 부토여행기
        group5.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "부토의 여행일기"));
        group5.children.get(2).children.add(new GrandChildItem(GRANDCHILLD, "부토 갤러리"));



        GroupItem group6 = new GroupItem(GROUP, "고객서비스센터", ContextCompat.getDrawable(context, R.drawable.ic_headset_mic_black_24dp), true);
        group6.children = new ArrayList<>();
        group6.children.add(new ChildItem(CHILD, "자주 묻는 질문", false));
        group6.children.add(new ChildItem(CHILD, "문의합니다", false));
        group6.children.add(new ChildItem(CHILD, "칭찬합니다", false));
        group6.children.add(new ChildItem(CHILD, "개선해주세요", false));


        GroupItem group7 = new GroupItem(GROUP, "서비스이용약관", ContextCompat.getDrawable(context, R.drawable.ic_content_paste_black_24dp), false);
        GroupItem group8 = new GroupItem(GROUP, "여객운송약관", ContextCompat.getDrawable(context, R.drawable.ic_content_paste_black_24dp), false);
        GroupItem group9 = new GroupItem(GROUP, "개인정보 취급방침", ContextCompat.getDrawable(context, R.drawable.ic_content_paste_black_24dp), false);
        GroupItem group10 = new GroupItem(GROUP, "설정", ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp), false);
        GroupItem group11 = new GroupItem(GROUP, "언어", ContextCompat.getDrawable(context, R.drawable.ic_language_black_24dp), false);



        data.add(header);
        data.add(group1);
        data.add(group2);
        data.add(group3);
        data.add(group4);
        data.add(group5);
        data.add(group6);
        data.add(group7);
        data.add(group8);
        data.add(group9);
        data.add(group10);
        data.add(group11);

        mAdapter = new ExpandableListAdapter(data);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navi, container, false);
        rv_list = (RecyclerView) view.findViewById(R.id.rv_navi);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_list.setAdapter(mAdapter);

        return view;
    }

}
