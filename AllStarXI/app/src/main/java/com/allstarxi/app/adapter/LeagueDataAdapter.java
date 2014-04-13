package com.allstarxi.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.allstarxi.app.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by liam on 4/13/14.
 */
public class LeagueDataAdapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater mInflater;
    public JsonArray mJsonArray;

    public LeagueDataAdapter(Context context, LayoutInflater inflater)
    {
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JsonArray();
    }

    public void updateData(JsonArray jsonArray)
    {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mJsonArray.size();
    }

    @Override
    public JsonElement getItem(int position)
    {
        return mJsonArray.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    private int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return mContext.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LeagueDataHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.leagues_item_row, parent, false);

            holder = new LeagueDataHolder();
            holder.nameView  = (TextView)convertView.findViewById(R.id.leagueNameText);
            holder.rankView  = (TextView)convertView.findViewById(R.id.leagueRankText);
            holder.teamsView = (TextView)convertView.findViewById(R.id.leagueTeamsText);

            convertView.setTag(holder);
        }
        else
        {
            holder = (LeagueDataHolder)convertView.getTag();
        }

        JsonObject jsonObject = (JsonObject)getItem(position);

        if(jsonObject.has("name"))
        {
            String leagueName = jsonObject.get("name").getAsString();
            holder.nameView.setText(leagueName);
        }
        if(jsonObject.has("rank"))
        {
            String rankNumber = jsonObject.get("rank").getAsString();
            holder.rankView.setText(rankNumber);
        }
        if(jsonObject.has("teams"))
        {
            String teamNumber = jsonObject.get("teams").getAsString();
            holder.teamsView.setText(teamNumber);
        }

        return convertView;
    }

    static class LeagueDataHolder
    {
        TextView nameView;
        TextView rankView;
        TextView teamsView;
    }
}
