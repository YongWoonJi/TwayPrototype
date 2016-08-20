package com.example.jyw.twayprototype;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by JYW on 2016-08-20.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
//    private static final int[] ATTRS = new int[] {android.R.attr.listDivider};
//    private Drawable mDivider;
//
//    public DividerItemDecoration(Context context) {
//        final TypedArray a = context.obtainStyledAttributes(ATTRS);
//        mDivider = a.getDrawable(0);
//        a.recycle();
//    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 60);
    }


}
