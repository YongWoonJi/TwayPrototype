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
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NaviFragment.OnMenuSelectListener {

    TabLayout tabs;
    AppBarLayout appbar;
    ImageView logo_t, logo_quotation, logo_way;
    ImageView rightIcon;
    ViewPager viewPager;
    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    DrawerLayout drawer;


    ColorDrawable newColor;
    Drawable homeAsUp;

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


        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offset = (appBarLayout.getY() / appbar.getTotalScrollRange());

                newColor.setAlpha((int)((offset * -1) * 255));
                getSupportActionBar().setBackgroundDrawable(newColor);

                changeToolbarIconsColor(offset);
            }
        });


        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_flight_takeoff_white_24dp).setText("항공권 예매").setTag(TAB1));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_card_giftcard_white_24dp).setText("이벤트").setTag(TAB2));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_schedule_white_24dp).setText("운항 스케줄").setTag(TAB3));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Toast.makeText(MainActivity.this, "항공권예매", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "이벤트", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "운항스케줄", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Toast.makeText(MainActivity.this, "항공권예매", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "이벤트", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "운항스케줄", Toast.LENGTH_SHORT).show();
                        break;
                }
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

//        int id_home = getResources().getIdentifier("home", "id", "android.support.v7.appcompat");
//        ImageView iv = (ImageView) findViewById(android.support.v7.appcompat.R.id.home);
//        Log.i("AAAAA", "iv = " + iv);
//        if (iv != null) {
//            iv.setPadding(0, 0, 0, 0);
//        }
    }

    private void changeToolbarIconsColor(float offset) {
        int value;
        int r, g, b;
        if (((int)(255 - (-130 * offset))) == 0) {
            value = 255;
            r = g = b = 255;
        } else {
            value = (int) (255 - (-130 * offset));
            r = (int) (255 - (-48 * offset));
            g = (int) (255 - (-206 * offset));
            b = (int) (255 - (-206 * offset));
        }
        homeAsUp.setColorFilter(Color.rgb(value, value, value), PorterDuff.Mode.SRC_IN);
        getSupportActionBar().setHomeAsUpIndicator(homeAsUp);

        if (rightIcon != null) {
            rightIcon.setColorFilter(Color.rgb(value, value, value));
        }

        logo_t.setColorFilter(Color.rgb(r, g, b));
        logo_way.setColorFilter(Color.rgb(r, g, b));

        if (offset == 0) {
            r = 190;
            g = 190;
            b = 190;
        } else {
            r = (int) (190 - (-190 * offset));
            g = (int) (190 + (-10 * offset));
            b = (int) (190 - (-190 * offset));
        }
        logo_quotation.setColorFilter(Color.rgb(r, g, b));
    }

    boolean isBackPressd = false;
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else if (!isBackPressd) {
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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void init() {
        initViewPager();
        initRecyclerView();

        newColor = new ColorDrawable(Color.rgb(255, 255, 255));
        homeAsUp = ContextCompat.getDrawable(this, R.drawable.ic_menu_white_24dp);
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
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuSelected(int menuId) {

    }
}
