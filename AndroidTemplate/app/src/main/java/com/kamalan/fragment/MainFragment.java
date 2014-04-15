package com.kamalan.fragment;

import com.kamalan.app.R;
import com.kamalan.widget.HeaderBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hesam on 15/04/14
 */
public class MainFragment extends SuperFragment {

    private static final String TAG = "MainFragment";


    // Empty constructor
    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        HeaderBar headerBar = (HeaderBar) view.findViewById(R.id.headerBar);
        headerBar.setOnHeaderClickedListener(new HeaderBar.OnHeaderClicked() {
            @Override
            public void OnItem(int itemId) {
                switch (itemId) {
                    case R.id.ibSlider:
                        mListener.OpenDrawer();
                        break;
                }
            }
        });

        return view;
    }
}
