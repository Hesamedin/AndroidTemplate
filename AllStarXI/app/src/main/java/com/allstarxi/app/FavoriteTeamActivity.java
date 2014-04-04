package com.allstarxi.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FavoriteTeamActivity extends Activity implements AdapterView.OnItemClickListener
{
    ListView favoriteTeamListView;
    ArrayAdapter arrayAdapter;
    ArrayList teamList;
    String selectedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        favoriteTeamListView = (ListView)findViewById(R.id.favorite_team_listview);

        favoriteTeamListView.setOnItemClickListener(this);

        teamList = new ArrayList<String>();

        teamList.add("Argentina");
        teamList.add("Brazil");
        teamList.add("England");


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, teamList);

        favoriteTeamListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorite_team, menu);
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
    public void onItemClick(AdapterView parent, View view, int position, long id)
    {
        selectedTeam = (String) arrayAdapter.getItem(position);
    }
}
