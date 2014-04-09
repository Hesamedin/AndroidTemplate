package com.allstarxi.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by liam on 4/8/14.
 */
public class PlayerDataAdapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater mInflater;
    public JsonArray mJsonArray;

    public PlayerDataAdapter(Context context, LayoutInflater inflater)
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
        final PlayerDataHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.player_item_row, parent, false);

            holder = new PlayerDataHolder();
            holder.flagView  = (ImageView)convertView.findViewById(R.id.flagIcon);
            holder.nameView  = (TextView)convertView.findViewById(R.id.playerNameText);
            holder.priceView = (TextView)convertView.findViewById(R.id.playerPriceText);

            convertView.setTag(holder);
        }
        else
        {
            holder = (PlayerDataHolder)convertView.getTag();
        }

        JsonObject jsonObject = (JsonObject)getItem(position);

        if(jsonObject.has("country"))
        {
            String countryName = jsonObject.get("country").getAsString();

            Resources r = mContext.getResources();
            int drawableID = getResourceId(countryName.toLowerCase(), "drawable", mContext.getPackageName());
            holder.flagView.setImageResource(drawableID);
        }
        if(jsonObject.has("name"))
        {
            String playerName = jsonObject.get("name").getAsString();
            holder.nameView.setText(playerName);
        }
        if(jsonObject.has("price"))
        {
            String price = jsonObject.get("price").getAsString();
            holder.nameView.setText(price);
        }

        return convertView;
    }

    static class PlayerDataHolder
    {
        ImageView flagView;
        TextView  nameView;
        TextView  priceView;
    }
}