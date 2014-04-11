package com.allstarxi.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.allstarxi.app.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Created by liam on 4/8/14.
 */
public class PredictionAdapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater mInflater;
    public JsonArray mJsonArray;

    public PredictionAdapter(Context context, LayoutInflater inflater)
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
        final PredictionHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.autodraft_predict_item_row, parent, false);

            holder = new PredictionHolder();
            holder.leftButton   = (ToggleButton)convertView.findViewById(R.id.predict_left_win_button);
            holder.middleButton = (ToggleButton)convertView.findViewById(R.id.predict_tie_button);
            holder.rightButton  = (ToggleButton)convertView.findViewById(R.id.predict_right_win_button);

            convertView.setTag(holder);
        }
        else
        {
            holder = (PredictionHolder)convertView.getTag();
        }

        holder.leftButton.setText("leftWin");
        holder.middleButton.setText("tie");
        holder.rightButton.setText("rightWin");

        holder.leftButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                   System.out.println("CHECKED LEFT");
                   holder.middleButton.setChecked(false);
                   holder.rightButton.setChecked(false);
                }
                else
                {
                    System.out.println("UNCHECKED LEFT");

                }
            }
        });

        holder.middleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    System.out.println("CHECKED MIDDLE");
                    holder.leftButton.setChecked(false);
                    holder.rightButton.setChecked(false);
                }
                else
                {
                    System.out.println("UNCHECKED MIDDLE");
                }
            }
        });

        holder.rightButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    System.out.println("CHECKED RIGHT");
                    holder.leftButton.setChecked(false);
                    holder.middleButton.setChecked(false);
                }
                else
                {
                    System.out.println("UNCHECKED RIGHT");
                }
            }
        });


        /*JsonObject jsonObject = (JsonObject)getItem(position);

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

        }*/

        return convertView;
    }

    static class PredictionHolder
    {
        ToggleButton leftButton;
        ToggleButton middleButton;
        ToggleButton rightButton;
    }
}
