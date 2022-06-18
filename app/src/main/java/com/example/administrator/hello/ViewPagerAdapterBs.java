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


//보스스페셜
public class ViewPagerAdapterBs extends PagerAdapter {

    ArrayList<String> images;
    ArrayList<String> names;
    ArrayList<String> prices;
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


    public ViewPagerAdapterBs(Context context, String pic) {
        images = new ArrayList<>();
        images.add(pic);
//        images.add("file:///android_asset/andean.jpg");
//        images.add("file:///android_asset/p5x.jpg");

        this.context = context;
    }
    public ViewPagerAdapterBs(Context context, ArrayList<ItemData> pics) {
        images = new ArrayList<>();
        names = new ArrayList<>();
        prices = new ArrayList<>();
        for (int i = 0; i < pics.size(); i ++){
            images.add(pics.get(i).getBsPic());
            names.add(pics.get(i).getName());
            prices.add(pics.get(i).getPrice());
        }
        this.context = context;

    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider_bs, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.image_view);
        TextView textView = (TextView)v.findViewById(R.id.tv_count);

        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);

        //imageView.setImageResource(images[position]);
        Picasso.get()
                .load(images.get(position))
                .resize(600,600)
                .onlyScaleDown()
                .centerCrop()
                .into(imageView);
        //.centerCrop()

        tvName.setText(names.get(position));
        tvPrice.setText("KRW " + prices.get(position));

        textView.setText(position+1 + "/" + getCount());
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
