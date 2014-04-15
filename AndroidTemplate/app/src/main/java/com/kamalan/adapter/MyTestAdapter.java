package com.kamalan.adapter;

import com.kamalan.app.R;
import com.kamalan.model.TestModel;
import com.kamalan.utility.MyImageClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Hesam on 15/04/14
 */
public class MyTestAdapter extends BaseAdapter {

    private static final String TAG = "MyTestAdapter";

    private Context mContext;
    private LayoutInflater myInflater;
    private MyImageClient mImageDownloader;
    private List<TestModel> mList;

    public MyTestAdapter(Context context) {
        this.mContext = context;

        mImageDownloader = MyImageClient.getInstance(mContext);
        myInflater = LayoutInflater.from(context);
    }

    public void setData(List<TestModel> testModelList) {
        this.mList = testModelList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.list_test_adapter, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // image url should be added
        mImageDownloader.displayImage(holder.imageView, mList.get(position).getPrice());

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
