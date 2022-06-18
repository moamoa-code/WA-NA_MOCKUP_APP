package com.example.administrator.hello;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemCastActivity extends Activity {

    ViewPager viewPager;
    ViewPagerAdapterIc adapter;

    ArrayList<CustomMarker> markers;
    CustomMarker customMarker;

    ImageView itemPick;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemcast);

        viewPager = (ViewPager) findViewById(R.id.vp_cast);
        //adapter = new ViewPagerAdapter(this, customMarker.getPic1());
        adapter = new ViewPagerAdapterIc(this, MapsActivity.markers);
        viewPager.setAdapter(adapter);



        itemPick = (ImageView) findViewById(R.id.item_pick);



        ImageView.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.item_pick: {
                        Toast.makeText(v.getContext(), MapsActivity.markers.get(viewPager.getCurrentItem()).getName(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        };
        findViewById(R.id.item_pick).setOnClickListener(mClickListener);


    }


}
