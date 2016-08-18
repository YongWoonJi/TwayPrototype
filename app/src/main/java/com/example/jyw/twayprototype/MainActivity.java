package com.example.jyw.twayprototype;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    CollapsingToolbarLayout collapsing_toolbar;
    AppBarLayout appbar;
    ImageView imageView;
    ImageView rightIcon;

    private static final String TAB1 = "tab1";
    private static final String TAB2 = "tab2";
    private static final String TAB3 = "tab3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appbar = (AppBarLayout) findViewById(R.id.appbar);
        imageView = (ImageView) findViewById(R.id.logo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_dialog_info);

        final ColorDrawable newColor = new ColorDrawable(getResources().getColor(android.R.color.white));
        final Drawable homeAsUp = getResources().getDrawable(android.R.drawable.ic_dialog_info);
//        final Drawable rightIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_alert);
        newColor.setAlpha(0);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / appbar.getTotalScrollRange());
//                imageView.setAlpha( 1 - (offsetAlpha * -1));
                Log.i("AAAAA", "offsetAlpha값: " + (-150 * offsetAlpha) + " , offset: " + offsetAlpha);
                Log.i("AAAAA", "값: " + (1 - (offsetAlpha * -1)) * 255);


                newColor.setAlpha((int)((offsetAlpha * -1) * 255));
                getSupportActionBar().setBackgroundDrawable(newColor);


//                ColorFilter cf = new LightingColorFilter(0, (int)((offsetAlpha * -1) * 255) * 255);
//                homeAsUp.setColorFilter(cf);
                int value;
                if (((int)(150 * offsetAlpha)) == 0) {
                    value = 255;
                } else {
                    value = (int) (255 - (-150 * offsetAlpha));
                }
//                homeAsUp.setColorFilter(Color.rgb(value, value, value), PorterDuff.Mode.SRC_IN);
                homeAsUp.setTint(Color.rgb(value, value, value));

                imageView.setColorFilter(Color.rgb(255, value, value));

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
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_media_play).setText("항공권 예매").setTag(TAB1));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_alert).setText("이벤트").setTag(TAB2));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_map).setText("운항 스케쥴").setTag(TAB3));
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
