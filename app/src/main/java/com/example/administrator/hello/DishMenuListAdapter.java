package com.example.administrator.hello;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class DishMenuListAdapter extends BaseAdapter {

    private Context mContext;
    private List<DishMenu> mItems =  new ArrayList<DishMenu>();

    public DishMenuListAdapter(Context context) {mContext = context;}

    public void addItem(DishMenu dm){ mItems.add(dm);}

    public void clearItem(){ mItems.clear(); }

    public void setListItems(List<DishMenu> lit){
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
        DishMenuItemView itemView;
        if (convertView == null) {
            itemView = new DishMenuItemView(mContext, mItems.get(position));
        } else {
            itemView = (DishMenuItemView) convertView;
            //itemView.setIv(mItems.get(position).getPic1());
            itemView.setTitle(mItems.get(position).getName());
            itemView.setPrice(mItems.get(position).getPrice());
            itemView.setIcons(mContext, mItems.get(position));

        }

        return itemView;
    }
}
