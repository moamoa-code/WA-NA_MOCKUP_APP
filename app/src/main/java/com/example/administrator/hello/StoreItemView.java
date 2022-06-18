package com.example.administrator.hello;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoreItemView extends LinearLayout {

    private TextView title;
    private TextView score;
    private TextView rvcount;
    private ImageView store_pic;



    //상점 종류 아이콘 표시
    private LinearLayout iconsLayout;
    private ImageView ivPin;
    private ImageView icon_cafe;
    private ImageView icon_pray;
    private ImageView icon_res;
    private ImageView icon_btq;


    //상점목록에 아이템 로드
    public StoreItemView(Context context,CustomMarker aItem) {
        super(context);

        // Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.storelistitem, this, true);


        // 상점 종류 아이콘 초기화
        iconsLayout = (LinearLayout) findViewById(R.id.ll_icons);

        LayoutParams iconprarm = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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

        //iconsLayout.addView(icon_cafe);
        //iconsLayout.addView(icon_pray);


        //상점 종류에 맞게 아이콘 추가
        if(aItem.getType().toString().contains("PRY")){
            iconsLayout.addView(icon_pray);
            Log.d("아무거나 ", aItem.getName() + "는 PRY다. ");
        }
        if(aItem.getType().toString().contains("BTQ")){
            iconsLayout.addView(icon_btq);
            Log.d("아무거나 ", aItem.getName() + "는 BTQ. ");
        }
        if(aItem.getType().toString().contains("CAF")){
            iconsLayout.addView(icon_cafe);
            Log.d("아무거나 ", aItem.getName() + "는 CAF. ");
        }
        if(aItem.getType().toString().contains("RES")){
            iconsLayout.addView(icon_res);
            Log.d("아무거나 ", aItem.getName() + "는 RES. ");
        }

        // Set Icon
        store_pic = (ImageView) findViewById(R.id.sl_iv_store);
        ivPin = (ImageView) findViewById(R.id.iv_pins);

        //즐겨찾기 목록(핀)에 있으면 아이콘 변경
        if(isNotMyList(MapsActivity.mymarkers,aItem)){
            ivPin.setImageResource(R.drawable.pin_w);
        }else{
            ivPin.setImageResource(R.drawable.pin_p);
        }
        final CustomMarker bItem = aItem;

        // 즐겨찾기에 현재 상점 추가 이벤트 (핀버튼)
        ImageView.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_pins:
                        if(isNotMyList(MapsActivity.mymarkers,bItem))
                        {
                            Log.d("아무거나 추가시도 상점 : ", bItem.getName()+"");
                            MapsActivity.mymarkers.add(bItem);
                            //즐겨찾기 아이콘 변경
                            ivPin.setImageResource(R.drawable.pin_p);
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("아무거나 추가후 mymarkers : ", MapsActivity.mymarkers.get(i).getName());
                            }
                            break;
                        }else{
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                if(MapsActivity.mymarkers.get(i).getName().equals(bItem.getName())){
                                    Log.d("아무거나 삭제시도 상점 : ", bItem.getName()+"");
                                    Log.d("아무거나 삭제시도 인덱스 : ", i+"");
                                    MapsActivity.mymarkers.remove(i);
                                }
                            }
                            ivPin.setImageResource(R.drawable.pin_w);
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("아무거나 삭제후 mymarkers : ", MapsActivity.markers.get(i).getName());
                            }
                        }
                        break;
                }
            }
        };
        findViewById(R.id.iv_pins).setOnClickListener(mClickListener);




        // 사진 없는경우 기본사진으로 대체
        if(aItem.getPic1().isEmpty()){
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load("file:///android_asset/no.jpg")
                    .resize(500,500)
                    .onlyScaleDown()
                    .transform(transForm)
                    .centerCrop()
                    .into(store_pic);
        }else {
            //배경 이미지 세팅
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load(aItem.getPic1())
                    .resize(500,500)
                    .onlyScaleDown()
                    .transform(transForm)
                    .centerCrop()
                    .into(store_pic);
        }




        // Set Text 01
        title = (TextView) findViewById(R.id.sl_tv_title);
        title.setText(aItem.getName());

        // Set Text 02
        score = (TextView) findViewById(R.id.sl_tv_score);
        score.setText(aItem.getScore());

        // Set Text 03
        rvcount = (TextView) findViewById(R.id.sl_rv_count);
        rvcount.setText(aItem.getRv_count());

    }

    public void setTitle(String data){
        title.setText(data);
    }
    public void setScore(String data){
        score.setText(data);
    }
    public void setRvcount(String data){
        rvcount.setText(data);
    }
    public void setIv(String data){

        if(data.isEmpty()){
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load("file:///android_asset/no.jpg")
                    .resize(500,500)
                    .onlyScaleDown()
                    .transform(transForm)
                    .centerCrop()
                    .into(store_pic);
        }else {
            //배경 이미지 세팅
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load(data)
                    .resize(500,500)
                    .onlyScaleDown()
                    .transform(transForm)
                    .centerCrop()
                    .into(store_pic);
        }


    }

    public void setIcons(Context context, CustomMarker aItem){

        // 상점 종류 아이콘 초기화
        iconsLayout = (LinearLayout) findViewById(R.id.ll_icons);
        iconsLayout.removeAllViews();

        LayoutParams iconprarm = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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
        if(aItem.getType().toString().contains("PRY")){
            iconsLayout.addView(icon_pray);
            Log.d("아무거나 ", aItem.getName() + "는 PRY다. ");
        }
        if(aItem.getType().toString().contains("BTQ")){
            iconsLayout.addView(icon_btq);
            Log.d("아무거나 ", aItem.getName() + "는 BTQ. ");
        }
        if(aItem.getType().toString().contains("CAF")){
            iconsLayout.addView(icon_cafe);
            Log.d("아무거나 ", aItem.getName() + "는 CAF. ");
        }
        if(aItem.getType().toString().contains("RES")){
            iconsLayout.addView(icon_res);
            Log.d("아무거나 ", aItem.getName() + "는 RES. ");
        }

        // Set Icon
        store_pic = (ImageView) findViewById(R.id.sl_iv_store);
        ivPin = (ImageView) findViewById(R.id.iv_pins);

        //즐겨찾기 목록(핀)에 있으면 아이콘 변경
        if(isNotMyList(MapsActivity.mymarkers,aItem)){
            ivPin.setImageResource(R.drawable.pin_w);
        }else{
            ivPin.setImageResource(R.drawable.pin_p);
        }

        final CustomMarker bItem = aItem;
        // 즐겨찾기에 현재 상점 추가 이벤트 (핀버튼)
        ImageView.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_pins:
                        if(isNotMyList(MapsActivity.mymarkers,bItem))
                        {
                            Log.d("아무거나 추가시도 상점 : ", bItem.getName()+"");
                            MapsActivity.mymarkers.add(bItem);
                            //즐겨찾기 아이콘 변경
                            ivPin.setImageResource(R.drawable.pin_p);
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("아무거나 추가후 mymarkers : ", MapsActivity.mymarkers.get(i).getName());
                            }
                            break;
                        }else{
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                if(MapsActivity.mymarkers.get(i).getName().equals(bItem.getName())){
                                    Log.d("아무거나 삭제시도 상점 : ", bItem.getName()+"");
                                    Log.d("아무거나 삭제시도 인덱스 : ", i+"");
                                    MapsActivity.mymarkers.remove(i);
                                }
                            }
                            ivPin.setImageResource(R.drawable.pin_w);
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("아무거나 삭제후 mymarkers : ", MapsActivity.markers.get(i).getName());
                            }
                        }
                        break;
                }
            }
        };
        findViewById(R.id.iv_pins).setOnClickListener(mClickListener);

    }

    // 즐겨찾기에 있는지 검사
    public boolean isNotMyList(ArrayList<CustomMarker> to, CustomMarker from){
        for (int i = 0; i < to.size(); i ++)
        {
            if (to.get(i).getName().equals(from.getName())){
                return false;
            }
        }
        return true;
    }


    //DP값 PX로 변환하기
    public static int getDpToPixel(Context context, int DP) {
        float px = 0; try {
            px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP, context.getResources().getDisplayMetrics());
        }
        catch (Exception e) { }
        return (int) px;
    }

}
