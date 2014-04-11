package com.allstarxi.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.allstarxi.app.R;
import com.allstarxi.app.activity.AutoDraftStep1Activity;
import com.allstarxi.app.activity.FavoriteTeamActivity;


public class ProfileSetupActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        Button changeProfileButton = (Button)findViewById(R.id.change_profile_pic_button);
        changeProfileButton.setOnClickListener(this);

        Button favoriteTeamButton = (Button)findViewById(R.id.favorite_team_button);
        favoriteTeamButton.setOnClickListener(this);

        Button toAutodraftButton = (Button)findViewById(R.id.to_autodraft_button);
        toAutodraftButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_setup, menu);
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
            Intent toAutodraftIntent = new Intent(this, AutoDraftStep1Activity.class);
            //toProfileSetupIntent.putExtra("selected", selected);
            startActivity(toAutodraftIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Intent toAutodraftIntent;
        Intent toFavoriteTeamIntent;

        switch (v.getId())
        {
            case R.id.change_profile_pic_button:
                //toAutodraftIntent = new Intent(this, ProfileSetupActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                //startActivity(toProfileSetupIntent);
                break;
            case R.id.favorite_team_button:
                toFavoriteTeamIntent = new Intent(this, FavoriteTeamActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toFavoriteTeamIntent);
                break;
            case R.id.to_autodraft_button:
                toAutodraftIntent = new Intent(this, AutoDraftStep1Activity.class);
                startActivity(toAutodraftIntent);
                break;
        }
    }
}
