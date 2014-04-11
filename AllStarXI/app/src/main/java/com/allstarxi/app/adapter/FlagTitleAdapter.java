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
 * Created by liam on 4/5/14.
 */
public class FlagTitleAdapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater mInflater;
    public JsonArray mJsonArray;

    public FlagTitleAdapter(Context context, LayoutInflater inflater)
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
        FlagTitleHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.flagcountry_item_row, parent, false);

            holder = new FlagTitleHolder();
            holder.flagIcon = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.countryTitle = (TextView)convertView.findViewById(R.id.txtTitle);

            convertView.setTag(holder);
        }
        else
        {
            holder = (FlagTitleHolder)convertView.getTag();
        }

        JsonObject jsonObject = (JsonObject)getItem(position);

        if(jsonObject.has("name"))
        {
            String countryName = jsonObject.get("name").getAsString();
            holder.countryTitle.setText(countryName);

            Resources r = mContext.getResources();
            //int drawableId = r.getIdentifier("drawable/" + countryName.toLowerCase() + ".png", null, mContext.getPackageName());
            int drawableID = getResourceId(countryName.toLowerCase(), "drawable", mContext.getPackageName());
            holder.flagIcon.setImageResource(drawableID);
        }
        if(jsonObject.has(""))
        {

        }

        return convertView;
    }

    static class FlagTitleHolder
    {
        ImageView flagIcon;
        TextView countryTitle;
    }
}
