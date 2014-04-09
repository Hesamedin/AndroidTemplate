package com.allstarxi.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TeamPageWithDrawerActivity extends Activity {

    private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_page_with_drawer);

        // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.nav_drawer_items);
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, drawerListViewItems));

        // 2. App Icon
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 2.1 create ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_launcher,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        // just styling option
        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.team_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void goToScreen(int position)
    {
        switch(position)
        {
            case 0:
                Intent toTeamPageIntent;
                toTeamPageIntent = new Intent(this, TeamPageWithDrawerActivity.class);
                startActivity(toTeamPageIntent);
                break;
            case 1:
                    /*Intent toProfileIntent = new Intent(this, TeamPageWithDrawerActivity.class);
                    //toProfileSetupIntent.putExtra("selected", selected);
                    startActivity(toTeamPageIntent);*/
                break;
            case 2:
                Intent toTransfersIntent = new Intent(this, TransfersActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toTransfersIntent);
                break;
            case 3:
                Intent toLeaguesIntent = new Intent(this, LeaguesActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toLeaguesIntent);
                break;
            case 4:
                break;
            case 5:
                Intent toMatchesIntent = new Intent(this, MatchesActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toMatchesIntent);
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            default:
                break;
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            goToScreen(position);
            Toast.makeText(TeamPageWithDrawerActivity.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
            drawerLayout.closeDrawer(drawerListView);

        }
    }
}