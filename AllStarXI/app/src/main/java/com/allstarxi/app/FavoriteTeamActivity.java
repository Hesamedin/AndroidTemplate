package com.allstarxi.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class FavoriteTeamActivity extends Activity implements AdapterView.OnItemClickListener
{
    ListView favoriteTeamListView;
    FlagTitleAdapter adapter;
    String selectedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        favoriteTeamListView = (ListView)findViewById(R.id.favorite_team_listview);
        favoriteTeamListView.setOnItemClickListener(this);

        adapter = new FlagTitleAdapter(this, getLayoutInflater());
        favoriteTeamListView.setAdapter(adapter);

        Ion.with(getApplicationContext(), "http://asxi.fpus.eu/api/teams")
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Token 1234567890ABCDEF")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {
                        // do stuff with the result or error
                        System.out.println(result.toString());
                        adapter.updateData(result.get("teams").getAsJsonArray());
                    }
                });
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
        JsonObject selectedElement = (JsonObject)adapter.getItem(position);
        selectedTeam = selectedElement.get("name").getAsString();
        super.finish();
    }
}
