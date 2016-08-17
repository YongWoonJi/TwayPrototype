package com.example.jyw.twayprototype;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    CollapsingToolbarLayout collapsing_toolbar;
    AppBarLayout appbar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appbar = (AppBarLayout) findViewById(R.id.appbar);
        imageView = (ImageView) findViewById(R.id.imageView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_dialog_info);

        final ColorDrawable newColor = new ColorDrawable(getResources().getColor(android.R.color.white));
        final Drawable homeAsUp = getResources().getDrawable(android.R.drawable.ic_dialog_info);
        newColor.setAlpha(0);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / appbar.getTotalScrollRange());
//                imageView.setAlpha( 1 - (offsetAlpha * -1));
                Log.i("AAAAA", "offsetAlpha값: " + 150 * offsetAlpha);
                Log.i("AAAAA", "값: " + (1 - (offsetAlpha * -1)) * 255);


                newColor.setAlpha((int)((offsetAlpha * -1) * 255));
                getSupportActionBar().setBackgroundDrawable(newColor);


//                ColorFilter cf = new LightingColorFilter(0, (int)((offsetAlpha * -1) * 255) * 255);
//                homeAsUp.setColorFilter(cf);
                int value;
                if (((int)(150 * offsetAlpha)) == 0) {
                    value = -6;
                } else {
                    value = (int) (-150 * offsetAlpha);
                }
                homeAsUp.setColorFilter(Color.rgb(value, value, value), PorterDuff.Mode.SRC_IN);
                homeAsUp.setTint(Color.rgb(value, value, value));

                getSupportActionBar().setHomeAsUpIndicator(homeAsUp);
            }
        });


        collapsing_toolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
//        collapsing_toolbar.setTitle("안녕?");
//        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
//        collapsing_toolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsing_toolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorAccent));


        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_media_play).setText("항공권 예매").setTag("tab1"));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_alert).setText("이벤트").setTag("tab2"));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_map).setText("운항 스케쥴").setTag("tab3"));
    }
}
