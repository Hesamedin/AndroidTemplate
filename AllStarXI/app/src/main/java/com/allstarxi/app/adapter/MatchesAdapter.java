package com.allstarxi.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allstarxi.app.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by liam on 4/13/14.
 */
public class MatchesAdapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater mInflater;
    public JsonArray mJsonArray;

    public MatchesAdapter(Context context, LayoutInflater inflater)
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
        MatchesDataHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.matches_item_row, parent, false);

            holder = new MatchesDataHolder();

            holder.leftFlag = (ImageView)convertView.findViewById(R.id.leftFlag);
            holder.leftCountryName = (TextView)convertView.findViewById(R.id.leftTeamText);
            holder.timeName = (TextView)convertView.findViewById(R.id.timeText);
            holder.rightCountryName = (TextView)convertView.findViewById(R.id.rightTeamText);
            holder.rightFlag = (ImageView)convertView.findViewById(R.id.rightFlag);

            convertView.setTag(holder);
        }
        else
        {
            holder = (MatchesDataHolder)convertView.getTag();
        }

        JsonObject jsonObject = (JsonObject)getItem(position);

        if(jsonObject.has("left_country"))
        {
            String leftCountryName = jsonObject.get("left_country").getAsString();
            holder.leftCountryName.setText(leftCountryName);

            Resources r = mContext.getResources();
            int drawableID = getResourceId(leftCountryName.toLowerCase(), "drawable", mContext.getPackageName());
            holder.leftFlag.setImageResource(drawableID);
        }
        if(jsonObject.has("right_country"))
        {
            String rightCountryName = jsonObject.get("right_country").getAsString();
            holder.rightCountryName.setText(rightCountryName);

            Resources r = mContext.getResources();
            int drawableID = getResourceId(rightCountryName.toLowerCase(), "drawable", mContext.getPackageName());
            holder.rightFlag.setImageResource(drawableID);
        }
        if(jsonObject.has("time"))
        {
            String timeString = jsonObject.get("time").getAsString();
            holder.timeName.setText(timeString);
        }

        return convertView;
    }

    static class MatchesDataHolder
    {
        ImageView leftFlag;
        TextView leftCountryName;
        TextView timeName;
        TextView rightCountryName;
        ImageView rightFlag;
    }
}
