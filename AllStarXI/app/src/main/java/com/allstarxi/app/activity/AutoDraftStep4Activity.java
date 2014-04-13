package com.allstarxi.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.allstarxi.app.adapter.PlayerDataAdapter;
import com.allstarxi.app.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;


public class AutoDraftStep4Activity extends Activity implements AdapterView.OnItemClickListener
{
    ListView playerDataListView;
    PlayerDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_draft_step4);

        playerDataListView = (ListView)findViewById(R.id.wanted_players_listview);
        playerDataListView.setOnItemClickListener(this);

        adapter = new PlayerDataAdapter(this, getLayoutInflater());

        final String json = "{\n" +
                "    \"players\": [\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name1\",\n" +
                "            \"price\": \"price1\",\n" +
                "            \"type\": \"gk\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name2\",\n" +
                "            \"price\": \"price2\",\n" +
                "            \"type\": \"def\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name3\",\n" +
                "            \"price\": \"price3\",\n" +
                "            \"type\": \"midfielders\"\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        try
        {
            JsonObject object = (JsonObject)new JsonParser().parse(json);
            JsonArray array = object.getAsJsonArray("players");
            adapter.mJsonArrayFull = array;
            adapter.notifyDataSetChanged();
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }

        playerDataListView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.auto_draft_step4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_next)
        {
            Intent toTeamPageIntent = new Intent(this, TeamPageWithDrawerActivity.class);
            //toProfileSetupIntent.putExtra("selected", selected);
            startActivity(toTeamPageIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
