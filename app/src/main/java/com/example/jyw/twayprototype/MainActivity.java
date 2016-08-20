package com.example.jyw.twayprototype;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    CollapsingToolbarLayout collapsing_toolbar;
    AppBarLayout appbar;
    ImageView logo_t, logo_quotation, logo_way;
    ImageView rightIcon;
    ViewPager viewPager;
    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;

    private List<String> numberList;

    private CircleAnimIndicator circleAnimIndicator;

    private static final String TAB1 = "tab1";
    private static final String TAB2 = "tab2";
    private static final String TAB3 = "tab3";

    private static final int MESSAGE_BACK_KEY_TIMEOUT = 1;
    private static final int TIMEOUT_TIME = 2000;

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_BACK_KEY_TIMEOUT:
                    isBackPressd = false;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);


        final ColorDrawable newColor = new ColorDrawable(getResources().getColor(android.R.color.white));
        final Drawable homeAsUp = getResources().getDrawable(R.drawable.ic_menu_white_24dp);
//        final Drawable rightIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_alert);
        newColor.setAlpha(0);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / appbar.getTotalScrollRange());
//                imageView.setAlpha( 1 - (offsetAlpha * -1));
                Log.i("AAAAA", "offset계산값: " + (255 - (-130 * offsetAlpha)) + " , offset: " + offsetAlpha);
                Log.i("AAAAA", "값: " + (1 - (offsetAlpha * -1)) * 255);


                newColor.setAlpha((int)((offsetAlpha * -1) * 255));
                getSupportActionBar().setBackgroundDrawable(newColor);


//                ColorFilter cf = new LightingColorFilter(0, (int)((offsetAlpha * -1) * 255) * 255);
//                homeAsUp.setColorFilter(cf);
                int value;
                int r, g, b;
                if (((int)(255 - (-130 * offsetAlpha))) == 0) {
                    value = 255;
                    r = g = b = 255;
                } else {
                    value = (int) (255 - (-130 * offsetAlpha));
                    r = (int) (255 - (-48 * offsetAlpha));
                    g = (int) (255 - (-206 * offsetAlpha));
                    b = (int) (255 - (-206 * offsetAlpha));
                }
//                homeAsUp.setColorFilter(Color.rgb(value, value, value), PorterDuff.Mode.SRC_IN);
                homeAsUp.setColorFilter(Color.rgb(value, value, value), PorterDuff.Mode.SRC_IN);

                logo_t.setColorFilter(Color.rgb(r, g, b));
                logo_way.setColorFilter(Color.rgb(r, g, b));

                if (offsetAlpha == 0) {
                    r = 190;
                    g = 190;
                    b = 190;
                } else {
                    r = (int) (190 - (-190 * offsetAlpha));
                    g = (int) (190 + (-10 * offsetAlpha));
                    b = (int) (190 - (-190 * offsetAlpha));
                    Log.i("AAAAA", "R, B : " + r);
                }
                logo_quotation.setColorFilter(Color.rgb(r, g, b));


                if (rightIcon != null) {
                    rightIcon.setColorFilter(Color.rgb(value, value, value));
                }

                getSupportActionBar().setHomeAsUpIndicator(homeAsUp);
            }
        });


        collapsing_toolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
//        collapsing_toolbar.setTitle("안녕?");
//        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
//        collapsing_toolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsing_toolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorAccent));


        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_flight_takeoff_white_24dp).setText("항공권 예매").setTag(TAB1));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_card_giftcard_white_24dp).setText("이벤트").setTag(TAB2));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_schedule_white_24dp).setText("운항 스케쥴").setTag(TAB3));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Toast.makeText(MainActivity.this, "tab1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "tab2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "tab3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        try {
//            Field declaredFiled = toolbar.getClass().getDeclaredField("mTitleTextView");
//            declaredFiled.setAccessible(true);
//            ImageView logoView = (ImageView) declaredFiled.get(toolbar);
//            ViewGroup.LayoutParams layoutParams = logoView.getLayoutParams();
//            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        int id_home = getResources().getIdentifier("home", "id", "android.support.v7.appcompat");
        ImageView iv = (ImageView) findViewById(android.support.v7.appcompat.R.id.home);
        Log.i("AAAAA", "iv = " + iv);
        if (iv != null) {
            iv.setPadding(0, 0, 0, 0);
        }

    }

    boolean isBackPressd = false;
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (!isBackPressd) {
            Toast.makeText(MainActivity.this, R.string.back_press, Toast.LENGTH_SHORT).show();
            isBackPressd = true;
            mHandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, TIMEOUT_TIME);
        } else {
            mHandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }

    private void initLayout() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        logo_t = (ImageView) findViewById(R.id.logo_t);
        logo_quotation = (ImageView) findViewById(R.id.logo_quotation);
        logo_way = (ImageView) findViewById(R.id.logo_way);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        circleAnimIndicator = (CircleAnimIndicator) findViewById(R.id.circleAnimIndicator);
        recyclerView = (RecyclerView) findViewById(R.id.rv_view);
    }

    private void init() {
        initViewPager();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration();
        recyclerView.addItemDecoration(decoration);
    }

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                circleAnimIndicator.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        circleAnimIndicator.setItemMargin(15);
        circleAnimIndicator.setAnimDuration(300);
        circleAnimIndicator.createDotPanel(4, R.drawable.non_indicator, R.drawable.sel_indicator);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.item1);
        View view = MenuItemCompat.getActionView(item);
        rightIcon = (ImageView) view.findViewById(R.id.imageView2);

        return true;
    }
}
