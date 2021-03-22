package com.example.groupworksheffxplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import bolts.Task;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<TouristHotspots> toursitHotSpotList = null;
    private ArrayList<TouristHotspots> arraylist;

    public ListViewAdapter(Context context, List<TouristHotspots> animalNamesList) {
        mContext = context;
        this.toursitHotSpotList = animalNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<TouristHotspots>();
        this.arraylist.addAll(toursitHotSpotList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return toursitHotSpotList.size();
    }

    @Override
    public TouristHotspots getItem(int position) {
        return toursitHotSpotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(toursitHotSpotList.get(position).getTouristHotSpotName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        toursitHotSpotList.clear();
        if (charText.length() == 0) {
            toursitHotSpotList.addAll(arraylist);
        } else {
            for (TouristHotspots wp : arraylist) {
                if (wp.getTouristHotSpotName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    toursitHotSpotList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}