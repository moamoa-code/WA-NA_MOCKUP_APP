package com.example.administrator.hello;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoreListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomMarker> mItems =  new ArrayList<CustomMarker>();

    public StoreListAdapter(Context context) {mContext = context;}

    public void addItem(CustomMarker cm){ mItems.add(cm);}

    public void clearItem(){ mItems.clear(); }

    public void setListItems(List<CustomMarker> lit){
        mItems = lit;
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreItemView itemView;
        if (convertView == null) {
            itemView = new StoreItemView(mContext, mItems.get(position));
        } else {
            itemView = (StoreItemView) convertView;
            itemView.setIv(mItems.get(position).getPic1());
            itemView.setTitle(mItems.get(position).getName());
            itemView.setScore(mItems.get(position).getScore());
            itemView.setRvcount(mItems.get(position).getRv_count());
            itemView.setIcons(mContext, mItems.get(position));

        }

        return itemView;
    }
}
