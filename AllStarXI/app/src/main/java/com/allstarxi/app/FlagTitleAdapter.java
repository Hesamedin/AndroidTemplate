package com.allstarxi.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by liam on 4/5/14.
 */
public class FlagTitleAdapter extends ArrayAdapter<FlagTitle> {
    Context context;
    int layoutResourceId;
    FlagTitle data[] = null;

    public FlagTitleAdapter(Context context, int layoutResourceId, FlagTitle[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        FlagTitleHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new FlagTitleHolder();
            holder.flagIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.countryTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (FlagTitleHolder)row.getTag();
        }

        FlagTitle flagTitle = data[position];
        holder.countryTitle.setText(flagTitle.country);
        holder.flagIcon.setImageResource(flagTitle.flag);

        return row;
    }

    static class FlagTitleHolder
    {
        ImageView flagIcon;
        TextView countryTitle;
    }
}
