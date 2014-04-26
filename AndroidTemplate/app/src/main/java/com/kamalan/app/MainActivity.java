package com.kamalan.app;

import com.kamalan.fragment.MainFragment;
import com.kamalan.fragment.MenuFragment;
import com.kamalan.fragment.SuperFragment;
import com.kamalan.utility.Log;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AbstractActivity implements
        SuperFragment.OnMenuClickedListener,
        MenuFragment.OnSlideMenuClickedListener {

    private static final String TAG = "MainActivity";
    private static final String TAG_FRAG_HOME = "MainFragment";
    private static final String TAG_FRAG_MENU = "MenuFragment";

    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "***************************");
        Log.d(TAG, "*** Application started ***");
        Log.d(TAG, "***************************");

        // TODO: set it false in production release
        Log.setLogStatus(true); // setting log status.

        // assign layout to activity
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_menu);
        mFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();

                // display view for selected nav drawer item
//                displayItem(selectedDrawerItem);
            }

            public void onDrawerOpened(View drawerView) {
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time
            displayHomeScreen();
            displayMenu();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void OpenDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();
    }

    @Override
    public void OpenMenuItem(int position) {
        Intent intent = null;

        switch (position) {
            case 0: intent = new Intent(mContext, SampleActivity.class); break;
        }

        if(intent != null)
            startActivity(intent);

        closeDrawer();
    }

    private void displayHomeScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, new MainFragment()).addToBackStack(TAG_FRAG_HOME).commit();
    }

    private void displayMenu() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_menu, new MenuFragment()).addToBackStack(null).commit();
    }



}
