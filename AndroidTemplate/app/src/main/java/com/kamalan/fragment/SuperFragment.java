package com.kamalan.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Hesam on 15/04/14
 */
public class SuperFragment extends Fragment {

    public interface OnMenuClickedListener {
        public void OpenDrawer();
    }


    public OnMenuClickedListener mListener;

    // Empty constructor
    public SuperFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnMenuClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement all interfaces of SuperFragment class.");
        }
    }
}
