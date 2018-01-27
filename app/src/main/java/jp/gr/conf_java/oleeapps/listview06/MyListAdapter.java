package jp.gr.conf_java.oleeapps.listview06;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by orisa on 2018/01/27.
 */

public class MyListAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<ListItem> data = null;
    private int resource = 0;

    public MyListAdapter(Context context, ArrayList<ListItem> data, int resource) {
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity)context;
        ListItem item = (ListItem) getItem(position);
        if (convertView == null) {
            // initial call to getView
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        // set contents for each item
        ((TextView)convertView.findViewById(R.id.date)).setText(item.getDate());

        return convertView;
    }
}
