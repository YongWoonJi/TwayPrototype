package com.example.jyw.twayprototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    private int position;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public static ViewPagerFragment newInstance(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_MESSAGE, position);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.view_picture);
        switch (position) {
            case 0:
                iv.setImageResource(R.drawable.banner1);
                break;
            case 1:
                iv.setImageResource(R.drawable.banner2);
                break;
            case 2:
                iv.setImageResource(R.drawable.banner3);
                break;
            case 3:
                iv.setImageResource(R.drawable.banner4);
                break;
        }
        return view;
    }

}
