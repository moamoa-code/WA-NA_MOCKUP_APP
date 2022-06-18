package com.example.administrator.hello;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DishMenuItemView extends LinearLayout {

    static int isClicked = 0;

    private TextView title;
    private TextView titleEng;
    private TextView price;
    private TextView hotmeter;

    private TextView tv_subName;
    private TextView tv_contents;

    private ImageView dm_best;          //추천
    private ImageView dm_bests;         //일반
    private ImageView dm_muslim;

    private TextView dm_contents;

    private FrameLayout dm_pic_frame;
    private ImageView dm_pic;

    private FrameLayout detailFrame;



    //음식 종류 아이콘
    private LinearLayout iconsLayout;
    private ImageView ivPin;
    private ImageView icon_bef;
    private ImageView icon_chi;
    private ImageView icon_hal;
    private ImageView icon_por;
    private ImageView icon_sea;
    private ImageView icon_veg;

    private LinearLayout icon_beef;
    private LinearLayout icon_chicken;
    private LinearLayout icon_shell;
    private LinearLayout icon_gluten;
    private LinearLayout icon_pork;
    private LinearLayout icon_lamb;
    private LinearLayout icon_fish;
    private LinearLayout icon_shr;
    private LinearLayout icon_milk;
    private LinearLayout icon_garlic;
    private LinearLayout icon_spicy1;
    private LinearLayout icon_spicy2;
    private LinearLayout icon_spicy3;
    private LinearLayout icon_spicy4;



    String titleStr;

    //상점목록에 아이템 로드
    public DishMenuItemView(final Context context, DishMenu aItem) {
        super(context);

        // Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dishmenu_item, this, true);

        tv_subName = (TextView)findViewById(R.id.dm_name_eng);
        tv_contents = (TextView)findViewById(R.id.dm_contents);

        //setVisibility(View.GONE);
        dm_best = (ImageView)findViewById(R.id.dm_best);
        dm_bests = (ImageView)findViewById(R.id.dm_bests);
        icon_hal = (ImageView) findViewById(R.id.dm_halal);
        dm_muslim = (ImageView) findViewById(R.id.dm_muslim);

        icon_beef = (LinearLayout) findViewById(R.id.icon_beef);
        icon_chicken = (LinearLayout) findViewById(R.id.icon_chi);
        icon_fish = (LinearLayout) findViewById(R.id.icon_fish);
        icon_gluten = (LinearLayout) findViewById(R.id.icon_gluten);
        icon_lamb = (LinearLayout) findViewById(R.id.icon_lamb);
        icon_shell = (LinearLayout) findViewById(R.id.icon_shell);
        icon_shr = (LinearLayout) findViewById(R.id.icon_shr);
        icon_pork = (LinearLayout) findViewById(R.id.icon_pork);
        icon_garlic = (LinearLayout) findViewById(R.id.icon_garlic);
        icon_milk = (LinearLayout) findViewById(R.id.icon_milk);
        icon_gluten = (LinearLayout) findViewById(R.id.icon_gluten);

        icon_spicy1 = (LinearLayout) findViewById(R.id.icon_spicy1);
        icon_spicy2 = (LinearLayout) findViewById(R.id.icon_spicy2);
        icon_spicy3 = (LinearLayout) findViewById(R.id.icon_spicy3);
        icon_spicy4 = (LinearLayout) findViewById(R.id.icon_spicy4);




        // 음식 종류 아이콘 초기화
        iconsLayout = (LinearLayout) findViewById(R.id.dm_icons);

        if(aItem.getHotMeter().equals("") || aItem.getHotMeter().equals("0")){
            icon_spicy1.setVisibility(View.VISIBLE);
        }
        if(aItem.getHotMeter().equals("2")){
            icon_spicy2.setVisibility(View.VISIBLE);
        }
        if(aItem.getHotMeter().equals("3")){
            icon_spicy3.setVisibility(View.VISIBLE);
        }
        if(aItem.getHotMeter().equals("4")){
            icon_spicy4.setVisibility(View.VISIBLE);
        }



        //상점 종류에 맞게 아이콘 추가
        if(aItem.getType().toString().contains("BST")){
            dm_best.setVisibility(View.VISIBLE);
            dm_bests.setVisibility(View.GONE);
        }
        if(aItem.getType().toString().contains("BEF")){
            icon_beef.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("CHI")){
            icon_chicken.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("HAL")){
            icon_hal.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("POR")){
            icon_pork.setVisibility(View.VISIBLE);
        }else {
            dm_muslim.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("SHE")){
            icon_shell.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("SHR")){
            icon_shr.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("GAL")){
            icon_garlic.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("MIL")){
            icon_milk.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("GLU")){
            icon_gluten.setVisibility(View.VISIBLE);
        }


        dm_pic = (ImageView) findViewById(R.id.dm_pic);

        if(aItem.getPic().isEmpty()){
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load("file:///android_asset/no.jpg")
                    .resize(500,500)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(dm_pic);
        }else {
            //배경 이미지 세팅
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load(aItem.getPic())
                    .resize(500,500)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(dm_pic);
        }




        //언어별로 음식 이름 넣기
        titleStr = aItem.getName();

        // Set Text 01
        title = (TextView) findViewById(R.id.dm_name);
        titleEng = (TextView) findViewById(R.id.dm_name_eng);

        dm_contents = (TextView) findViewById(R.id.dm_contents);
        dm_contents.setText(aItem.getContents());

        if(titleStr.indexOf("KOR:{") >= 0){
            title.setText(titleStr.substring(titleStr.indexOf("KOR:{")+5,titleStr.indexOf("}:KOR")));
        }else
        {
            title.setText(aItem.getName());
        }
        if(titleStr.indexOf("ENG:{") >= 0){
            titleEng.setText(titleStr.substring(titleStr.indexOf("ENG:{")+5,titleStr.indexOf("}:ENG")));
        }else{
            titleEng.setText("Food");
        }





        // Set Text 02
        price = (TextView) findViewById(R.id.dm_price);
        price.setText(aItem.getPrice());



        // 음식 이미지 프레임 레이아웃
        dm_pic_frame = (FrameLayout) findViewById(R.id.dm_pic_frame);
        final LayoutParams extendedPic = new LayoutParams(getDpToPixel(context,210), getDpToPixel(context,220));
        final LayoutParams normalPic = new LayoutParams(getDpToPixel(context,110), getDpToPixel(context,220));

        detailFrame = (FrameLayout) findViewById(R.id.detailFrame);

        dm_pic = (ImageView) findViewById(R.id.dm_pic);

        // 음식 이미지 클릭시 크게 보여주기
        dm_pic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClicked == 0){
                    //확장 애니메이션
                    dm_pic_frame.setLayoutParams(extendedPic);
                    dm_pic_frame.invalidate();
                    Animation ani = new TranslateAnimation(-200, 0, 0, 0);
                    ani.setDuration(60);
                    dm_pic_frame.setAnimation(ani);
                    tv_subName.setVisibility(View.GONE);
                    tv_contents.setVisibility(View.GONE);
                    isClicked = 1;
                }else {
                    //축소
                    dm_pic_frame.setLayoutParams(normalPic);
                    dm_pic_frame.invalidate();
                    Animation ani = new TranslateAnimation(100, 0, 0, 0);
                    ani.setDuration(20);
                    dm_pic_frame.setAnimation(ani);
                    tv_subName.setVisibility(View.VISIBLE);
                    tv_contents.setVisibility(View.VISIBLE);
                    isClicked = 0;
                }
            }
        });

        dm_contents.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                detailFrame.setVisibility(View.VISIBLE);

            }
        });




    }

    public void setTitle(String data){

        //음식 이름 언어별로 넣기
        titleStr = data;

        // Set Text 01
        title = (TextView) findViewById(R.id.dm_name);
        titleEng = (TextView) findViewById(R.id.dm_name_eng);

        if(titleStr.indexOf("KOR:{") >= 0){
            title.setText(titleStr.substring(titleStr.indexOf("KOR:{")+5,titleStr.indexOf("}:KOR")));
        }else
        {
            title.setText(data);
        }
        if(titleStr.indexOf("ENG:{") >= 0){
            titleEng.setText(titleStr.substring(titleStr.indexOf("ENG:{")+5,titleStr.indexOf("}:ENG")));
        }else{
            titleEng.setText("Food");
        }

    }
    public void setPrice(String data){
        price.setText(data);
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
                    .into(dm_pic);
        }else {
            //배경 이미지 세팅
            RoundedTransform transForm = new RoundedTransform(30, 0);
            Picasso.get()
                    .load(data)
                    .resize(500,500)
                    .onlyScaleDown()
                    .transform(transForm)
                    .centerCrop()
                    .into(dm_pic);
        }


    }

    public void setIcons(Context context, DishMenu aItem){

        dm_best = (ImageView)findViewById(R.id.dm_best);
        dm_bests = (ImageView)findViewById(R.id.dm_bests);
        icon_hal = (ImageView) findViewById(R.id.dm_halal);

        icon_beef = (LinearLayout) findViewById(R.id.icon_beef);
        icon_chicken = (LinearLayout) findViewById(R.id.icon_chi);
        icon_fish = (LinearLayout) findViewById(R.id.icon_fish);
        icon_gluten = (LinearLayout) findViewById(R.id.icon_gluten);
        icon_lamb = (LinearLayout) findViewById(R.id.icon_lamb);
        icon_shell = (LinearLayout) findViewById(R.id.icon_shell);
        icon_shr = (LinearLayout) findViewById(R.id.icon_shr);
        icon_pork = (LinearLayout) findViewById(R.id.icon_pork);

        //상점 종류에 맞게 아이콘 추가
        if(aItem.getType().toString().contains("BST")){
            dm_best.setVisibility(View.VISIBLE);
            dm_bests.setVisibility(View.GONE);
        }
        if(aItem.getType().toString().contains("BEF")){
            icon_beef.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("CHI")){
            icon_chicken.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("HAL")){
            icon_hal.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("POR")){
            icon_pork.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("SHE")){
            icon_shell.setVisibility(View.VISIBLE);
        }
        if(aItem.getType().toString().contains("SHR")){
            icon_shr.setVisibility(View.VISIBLE);
        }

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
