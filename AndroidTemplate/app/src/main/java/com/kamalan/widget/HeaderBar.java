package com.kamalan.widget;

import com.kamalan.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created with IntelliJ IDEA.
 * User: hesamedin
 * Date: 3/7/13
 * Time: 4:05 PM
 */
public class HeaderBar extends LinearLayout {

    public interface OnHeaderClicked {
        public void OnItem(int itemId);
    }

    private static final String TAG = "HeaderBar";
    private static final int GOD_MODE_ACTIVATION = 7;

    private OnHeaderClicked onHeaderClicked;
    private Context mContext;
    private ProgressBar progressBar;
    private ImageButton ibMenuSlider;

    private boolean messageIconFlashingFlag = false;


    public HeaderBar(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public HeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public HeaderBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    private void init() {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) mInflater.inflate(R.layout.widget_headerbar, null);
        addView(view);

        progressBar = (ProgressBar) view.findViewById(R.id.pbLoading);
        ibMenuSlider = (ImageButton) view.findViewById(R.id.ibSlider);
        ibMenuSlider.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeaderClicked.OnItem(v.getId());
            }
        });
    }

    /**
     * This method sets visibility of all buttons and progressbar to invisible.
     */
    public void clearHeader() {
//        progressBar.setVisibility(View.INVISIBLE);
        ibMenuSlider.setVisibility(View.INVISIBLE);
    }

    /**
     * Show progress bar in order to tell user something is happening in background.
     */
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Hide progress bar in order to tell user background process has been finished.
     */
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * This method Will show only slider in header bar
     */
    public void showSlider() {
        clearHeader();
        ibMenuSlider.setVisibility(View.VISIBLE);
    }

    /**
     * @param onHeaderClicked listener
     */
    public void setOnHeaderClickedListener(OnHeaderClicked onHeaderClicked) {
        this.onHeaderClicked = onHeaderClicked;
    }
}