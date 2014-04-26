package com.kamalan.fragment;

import com.kamalan.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Hesam on 15/04/14
 */
public class MenuFragment extends Fragment {

    public interface OnSlideMenuClickedListener {
        public void OpenMenuItem(int position);
    }

    private static final String TAG = "MenuFragment";

    private OnSlideMenuClickedListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnSlideMenuClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSlideMenuClickedListener() interface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        LinearLayout llContainer = (LinearLayout) view.findViewById(R.id.container);
        llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OpenMenuItem(-1);
            }
        });

        return view;
    }
}
