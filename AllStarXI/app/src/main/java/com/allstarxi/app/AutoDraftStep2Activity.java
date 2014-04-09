package com.allstarxi.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class AutoDraftStep2Activity extends Activity implements AdapterView.OnItemClickListener
{
    ListView bestOffenceTeamListView;
    FlagTitleAdapter adapter;
    String selectedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_draft_step2);

        bestOffenceTeamListView = (ListView) findViewById(R.id.offence_team_listview);
        bestOffenceTeamListView.setOnItemClickListener(this);

        adapter = new FlagTitleAdapter(this, getLayoutInflater());
        bestOffenceTeamListView.setAdapter(adapter);

        Ion.with(getApplicationContext(), "http://asxi.fpus.eu/api/teams")
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Token 1234567890ABCDEF")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        System.out.println(result.toString());
                        adapter.updateData(result.get("teams").getAsJsonArray());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.auto_draft_step2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_next)
        {
            Intent toAutodraftNextIntent = new Intent(this, AutoDraftStep3Activity.class);
            //toProfileSetupIntent.putExtra("selected", selected);
            startActivity(toAutodraftNextIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        JsonObject selectedElement = (JsonObject)adapter.getItem(position);
        selectedTeam = selectedElement.get("name").getAsString();
    }
}
