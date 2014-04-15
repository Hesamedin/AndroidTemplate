package com.kamalan.app;

import com.kamalan.adapter.MyTestAdapter;
import com.kamalan.model.TestModel;
import com.kamalan.utility.Log;
import com.kamalan.utility.MyHttpClient;
import com.kamalan.utility.MyInternetConnection;
import com.kamalan.utility.MyUrlManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Hesam on 15/04/14
 */
public class SampleActivity extends AbstractActivity {

    private static final String TAG = "SampleActivity";
    private static final String PREFS_NAME = "PrefSample";

    private Context mContext;
    private SharedPreferences mPrefs;
    private ListView mListView;
    private MyTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // attach layout to activity
        setContentView(R.layout.activity_sample);

        // store context for later usage
        mContext = SampleActivity.this;

        // Get shared preferences
        mPrefs = mContext.getSharedPreferences(PREFS_NAME, 0);

        // init adapter
        adapter = new MyTestAdapter(mContext);

        // Link other views to activity...
        mListView = (ListView) findViewById(R.id.listView);

        // get list of items from server
        getNewUpdatesFromServer();

        // display last info first
        displayData();
    }

    private void getNewUpdatesFromServer() {
        if(MyInternetConnection.isAvailable(mContext))
            new getNewUpdateTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
    }

    private void storeServerResponse(String response) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREFS_NAME, response);
        editor.commit();
    }

    private String getServerResponse() {
        return mPrefs.getString(PREFS_NAME, null);
    }

    private void displayData() {

        List<TestModel> testModelList = TestModel.parseJson(getServerResponse());

        if(testModelList != null && testModelList.size() > 0) {
            adapter.setData(testModelList);
            mListView.setAdapter(adapter);
        } else
            Log.e(TAG, "ERROR! testModelList is null or doesn't have any item!!!");
    }

    /**************************
     *   Async Task Classes   *
     **************************/
    private class getNewUpdateTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "getNewUpdateTask is about to start...");

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url = MyUrlManager.getProductsUrl();
            String response = MyHttpClient.getServerResponseByHttpGet(url);

            storeServerResponse(response);

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Log.i(TAG, "getNewUpdateTask finished its task.");

            displayData();
        }
    }
}
