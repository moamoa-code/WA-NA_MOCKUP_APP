package com.example.administrator.hello;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.administrator.hello.StoreItemView.getDpToPixel;


//아이템케스트
public class ViewPagerAdapterIc extends PagerAdapter {

    ArrayList<String> images;
    ArrayList<String> names;
    ArrayList<String> sTypes;
    ArrayList<String> prices;

    String curName;
    int curPos;


    //상점 종류 아이콘 표시
    private LinearLayout iconsLayout;
    private ImageView ivPin;
    private ImageView icon_cafe;
    private ImageView icon_pray;
    private ImageView icon_res;
    private ImageView icon_btq;

    private ImageView pin_btn;


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


    public ViewPagerAdapterIc(Context context, String pic) {
        images = new ArrayList<>();
        images.add(pic);
//        images.add("file:///android_asset/andean.jpg");
//        images.add("file:///android_asset/p5x.jpg");

        this.context = context;
    }
    public ViewPagerAdapterIc(Context context, ArrayList<CustomMarker> markers) {
        images = new ArrayList<>();
        names = new ArrayList<>();
        sTypes =  new ArrayList<>();
        for (int i = 0; i < markers.size(); i ++){
            images.add(markers.get(i).getPic1());
            names.add(markers.get(i).getName());
            sTypes.add(markers.get(i).getType());
        }
        this.context = context;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider_ic, container, false);

        curPos = position;
        curName = names.get(position);

        ImageView imageView = (ImageView)v.findViewById(R.id.image_view);
        TextView textView = (TextView)v.findViewById(R.id.tv_count);

        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);


        // 상점 종류 아이콘 초기화
        iconsLayout = (LinearLayout) v.findViewById(R.id.ll_icons);

        LinearLayout.LayoutParams iconprarm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        iconprarm.setMargins(getDpToPixel(context,8),0,0,0);
        icon_cafe = new ImageView(context);
        icon_cafe.setLayoutParams(iconprarm);
        icon_cafe.setAdjustViewBounds(true);
        icon_cafe.setImageResource(R.drawable.coffee_w);

        icon_pray = new ImageView(context);
        icon_pray.setLayoutParams(iconprarm);
        icon_pray.setAdjustViewBounds(true);
        icon_pray.setImageResource(R.drawable.prays_w);

        icon_res = new ImageView(context);
        icon_res.setLayoutParams(iconprarm);
        icon_res.setAdjustViewBounds(true);
        icon_res.setImageResource(R.drawable.food_w);

        icon_btq = new ImageView(context);
        icon_btq.setLayoutParams(iconprarm);
        icon_btq.setAdjustViewBounds(true);
        icon_btq.setImageResource(R.drawable.shirt_w);

        //상점 종류에 맞게 아이콘 추가
        if(sTypes.get(position).toString().contains("PRY")){
            iconsLayout.addView(icon_pray);
        }
        if(sTypes.get(position).toString().contains("BTQ")){
            iconsLayout.addView(icon_btq);
        }
        if(sTypes.get(position).toString().contains("CAF")){
            iconsLayout.addView(icon_cafe);
        }
        if(sTypes.get(position).toString().contains("RES")){
            iconsLayout.addView(icon_res);
        }

        //imageView.setImageResource(images[position]);
        if(images.get(position).isEmpty()){
            Picasso.get()
                    .load("file:///android_asset/no.jpg")
                    .resize(600,600)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(imageView);
        }
        else{
            Picasso.get()
                    .load(images.get(position))
                    .resize(600,600)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(imageView);
        }

        //.centerCrop()

        tvName.setText(names.get(position));
        //tvPrice.setText("KRW " + prices.get(position));

        textView.setText(position+1 + "/" + getCount());
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
