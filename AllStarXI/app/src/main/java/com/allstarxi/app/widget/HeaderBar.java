package com.allstarxi.app.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allstarxi.app.R;

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
    private LayoutInflater mInflater;
    private ProgressBar progressBar;
    private ImageButton ibMenuSlider;
    private ImageButton ibMessage;
    private Button btnEdit;
    private TextView tvAppName;

    private boolean messageIconFlashingFlag = false;

    private int counter = 0; // Counter in order to activate God mode


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

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout barView = (LinearLayout) mInflater.inflate(R.layout.widget_headerbar, null);
        addView(barView);

        Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts/pacifico.ttf");
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvAppName.setTypeface(type);
        tvAppName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(SpGodMode.isGodModeEnabled(mContext))
                    onHeaderClicked.OnItem(v.getId());
                else {
                    counter++;
                    if(counter >= GOD_MODE_ACTIVATION) {
                        SpGodMode.setGodMode(mContext, true);
                        Toast.makeText(mContext, "God mode enabled.\nTap once more ;)", Toast.LENGTH_SHORT).show();
                        counter = 0;
                    }
                }*/
            }
        });

        progressBar = (ProgressBar) barView.findViewById(R.id.pbLoading);

        ibMenuSlider = (ImageButton) barView.findViewById(R.id.ibSlider);
        ibMenuSlider.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeaderClicked.OnItem(v.getId());
            }
        });

        ibMessage = (ImageButton) barView.findViewById(R.id.ibMessage);
        ibMessage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeaderClicked.OnItem(v.getId());
            }
        });

        btnEdit = (Button) barView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new OnClickListener() {
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
        ibMessage.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.INVISIBLE);
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
     * This method Will show menu slider and message item in header bar
     */
    public void showSliderAndMessage() {
        clearHeader();
        ibMenuSlider.setVisibility(View.VISIBLE);
        ibMessage.setVisibility(View.VISIBLE);
    }

    /**
     * This method Will show only slider in header bar
     */
    public void showSlider() {
        clearHeader();
        ibMenuSlider.setVisibility(View.VISIBLE);
    }

    /**
     * This method Will show menu slider and Edit item in header bar
     */
    public void showSliderAndEdit() {
        clearHeader();
        ibMenuSlider.setVisibility(View.VISIBLE);
        btnEdit.setVisibility(View.VISIBLE);
    }

    /**
     * @param onHeaderClicked listener
     */
    public void setOnHeaderClickedListener(OnHeaderClicked onHeaderClicked) {
        this.onHeaderClicked = onHeaderClicked;
    }

    public void startFlashMessageIcon() {
        ibMessage.setImageBitmap(null);
        ibMessage.setBackgroundResource(R.drawable.flasher_message);
        AnimationDrawable frameAnimation = (AnimationDrawable) ibMessage.getBackground();
        frameAnimation.start();

        messageIconFlashingFlag = true;
    }

    public void stopFlashMessageIcon() {
        if(messageIconFlashingFlag) {
            ibMessage.setBackgroundColor(Color.TRANSPARENT);
            ibMessage.setImageResource(R.drawable.ic_header_email);
            messageIconFlashingFlag = false;
        }
    }

    public boolean isMessageIconFlashing() {
        return messageIconFlashingFlag;
    }
}