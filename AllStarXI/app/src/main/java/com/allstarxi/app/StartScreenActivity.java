package com.allstarxi.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class StartScreenActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Button facebookButton = (Button)findViewById(R.id.connect_facebook_button);
        facebookButton.setOnClickListener(this);

        ActionBar actionBar = getActionBar();
        actionBar.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_screen, menu);
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
    public void onClick(View v)
    {
        Intent toProfileSetupIntent;

        switch (v.getId())
        {
            case R.id.connect_facebook_button:
                toProfileSetupIntent = new Intent(this, ProfileSetupActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toProfileSetupIntent);
                break;
            case R.id.connect_twitter_button:
                toProfileSetupIntent = new Intent(this, ProfileSetupActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toProfileSetupIntent);
                break;
            case R.id.connect_email_button:
                Intent toEmailSignupIntent = new Intent(this, EmailSignUpActivity.class);
                //toProfileSetupIntent.putExtra("selected", selected);
                startActivity(toEmailSignupIntent);
                break;
            case R.id.have_account_button:
                break;
        }
    }
}
