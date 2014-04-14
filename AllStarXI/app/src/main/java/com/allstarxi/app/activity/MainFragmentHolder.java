package com.allstarxi.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.allstarxi.app.R;
import com.allstarxi.app.widget.HeaderBar;

public class MainFragmentHolder extends FragmentActivity {
    
    private HeaderBar headerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link layout to activity
        setContentView(R.layout.activity_main_new);

        // Link HeaderBar to activity and clear all buttons
        headerBar = (HeaderBar) findViewById(R.id.Header);
        headerBar.showSliderAndMessage();
        headerBar.setOnHeaderClickedListener(new HeaderBar.OnHeaderClicked() {
            @Override
            public void OnItem(int itemId) {
                switch (itemId) {
                    case R.id.ibSlider:
                        //menu.showMenu();
                        return;
                    case R.id.btnEdit:
                        //Intent intent = new Intent(MainFragmentHolder.this, RegistrationFragmentHolder.class);
                        //startActivity(intent);
                        return;
                    case R.id.ibMessage:
                        //headerBar.stopFlashMessageIcon();
                        //gotoMessagesScreen();
                        return;
                    case R.id.tvAppName:
                        //gotoGodModeScreen();
                        return;
                    default:
                }
            }
        });
    }
}