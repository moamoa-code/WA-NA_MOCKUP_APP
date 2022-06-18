package com.example.administrator.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    Window window;

    ImageView homepic1;
    ImageView homegrad1;

    ImageView profileIV;
    ImageView searchIV;
    ImageView mapIV;
    ImageView locIV;

    TextView locTV;

    ArrayList<String> images;

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    FrameLayout toplayout;
    FrameLayout toplayoutShade;
    FrameLayout topSpaceLayout;

    ScrollView homeScrollView;

    boolean isBlack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

        toplayout = (FrameLayout)findViewById(R.id.topLayout);
        toplayoutShade = (FrameLayout)findViewById(R.id.topLayoutShade);
        profileIV =(ImageView)findViewById(R.id.iv_profile);
        searchIV = (ImageView)findViewById(R.id.iv_search);
        mapIV = (ImageView)findViewById(R.id.listbtn);
        locIV = (ImageView)findViewById(R.id.iv_loc);
        locTV = (TextView)findViewById(R.id.tv_loc);

        profileIV.setImageResource(R.drawable.profile_w);
        searchIV.setImageResource(R.drawable.search_w);
        mapIV.setImageResource(R.drawable.map_w);
        locIV.setImageResource(R.drawable.loc_w);
        locTV.setTextColor(0xffffffff);

        //window = getWindow();
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(0x00000000);



        toplayout.setAlpha((float)0.00);
        toplayoutShade.setAlpha((float)0.00);

        homeScrollView = (ScrollView)findViewById(R.id.homeScrollView);

        isBlack  = false;

        //** API LEVEL 23이상
        // 스크롤에 따라 상단바 투명도 조절하기
        homeScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                float alphas = (float)scrollY * (float)0.001 ;


                Log.d("아무거나id",scrollY+" / " +alphas);

                toplayout.setAlpha(alphas);
                toplayoutShade.setAlpha(alphas);

                if(alphas >= (float) 0.5 && isBlack == false){
                    profileIV.setImageResource(R.drawable.profile);
                    searchIV.setImageResource(R.drawable.search);
                    mapIV.setImageResource(R.drawable.map);
                    locIV.setImageResource(R.drawable.loc);
                    locTV.setTextColor(0xff000000);
                    isBlack = true;
                    Log.d("아무거나id","검정색으로 변경");
                }else if (alphas <= (float) 0.499 && isBlack == true){
                    profileIV.setImageResource(R.drawable.profile_w);
                    searchIV.setImageResource(R.drawable.search_w);
                    mapIV.setImageResource(R.drawable.map_w);
                    locIV.setImageResource(R.drawable.loc_w);
                    locTV.setTextColor(0xffffffff);
                    isBlack = false;
                    Log.d("아무거나id","흰색으로 변경");
                }
            }
        });
        //** 스크롤시 투명도 조절 끝


        //상단바 스와이프 이벤트 처리(화면이동)
        toplayout.setOnTouchListener(new HomeActivity.OnSwipeTouchListener(HomeActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent(getApplicationContext(),TicketActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightout_activity,R.anim.rightin_activity);
            }
            public void onSwipeLeft() {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                //Intent intent = new Intent(getApplicationContext(),TicketActivity.class);
                startActivity(intent);
            }
        });

        // 각종 버튼 이벤트 처리
        ImageView.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    //메인이미지 클릭시 맵으로 이동
                }
            }
        };




        images = new ArrayList<>();
        images.add("file:///android_asset/foods.jpg");
        images.add("file:///android_asset/halas.jpg");

        homegrad1 = (ImageView) findViewById(R.id.iv_homegrad1);
        homepic1 = (ImageView) findViewById(R.id.iv_homepic);
    //  homepic1.getLayoutParams().height

        viewPager = (ViewPager) findViewById(R.id.vp_home);
        //adapter = new ViewPagerAdapter(this, customMarker.getPic1());
        adapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(adapter);



    }



    // 맵으로 이동
    public void onMapBtnClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);
    }

    // 맵으로 이동
    public void onPrayerRoomBtnClicked(View v) {
        Toast.makeText(this, "버튼클릭됨", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);
    }

    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new HomeActivity.OnSwipeTouchListener.GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                        result = true;
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                    result = true;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {

        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }

}
