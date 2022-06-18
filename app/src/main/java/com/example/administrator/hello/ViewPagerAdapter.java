package com.example.administrator.hello;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


//상점보기 뷰페이지
public class ViewPagerAdapter extends PagerAdapter {

    ArrayList<String> images;
    private LayoutInflater inflater;
    private Context context;

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }


    public ViewPagerAdapter(Context context, String pic) {
        images = new ArrayList<>();
        images.add(pic);
//        images.add("file:///android_asset/andean.jpg");
//        images.add("file:///android_asset/p5x.jpg");

        this.context = context;
    }
    public ViewPagerAdapter(Context context, ArrayList<String> pics) {
        images = new ArrayList<>();
        for (int i = 0; i < pics.size(); i ++){
            images.add(pics.get(i));
        }

        this.context = context;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.image_view);
        TextView textView = (TextView)v.findViewById(R.id.tv_count);

        //imageView.setImageResource(images[position]);
        Picasso.get()
                .load(images.get(position))
                .resize(600,600)
                .onlyScaleDown()
                .centerCrop()
                .into(imageView);

        //.centerCrop()

        textView.setText(position+1 + "/" + getCount());
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
