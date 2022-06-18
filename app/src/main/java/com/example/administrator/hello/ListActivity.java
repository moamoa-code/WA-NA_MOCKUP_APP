package com.example.administrator.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class ListActivity extends Activity {
    ListView listView1;
    StoreListAdapter adapter;

    FrameLayout topSpaceLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //**리스트뷰 애니메이션
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(50);

        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);

        animation.setDuration(200);

        set.addAnimation(animation);

        LayoutAnimationController controller =
                new LayoutAnimationController(set, 0.5f);

        //** 리스트뷰 애니메이션

        listView1 = (ListView) findViewById(R.id.storelistview);
        listView1.setLayoutAnimation(controller);


        // 어댑터 객체 생성
        adapter = new StoreListAdapter(this);
        Resources res = getResources();



        //MapsActivity에서 생성한 상점 리스트 불러와 리스트에 추가
        for (int i = 0; i < MapsActivity.markers.size(); i++){
            adapter.addItem(MapsActivity.markers.get(i));
        }


        // 리스트뷰에 어댑터 설정
        listView1.setAdapter(adapter);


        // 새로 정의한 리스너로 객체를 만들어 설정
        // 상점 클릭시 이벤트
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CustomMarker selectedMarker = (CustomMarker) adapter.getItem(position);
                CustomMarker sendMarker = new CustomMarker(selectedMarker);
                sendMarker.cpyPic(selectedMarker.getPics());
                sendMarker.cpyBsPics(selectedMarker.getBsPics());
                sendMarker.cpyDishMenus(selectedMarker.getDishMenus());

                if (selectedMarker.getType().toString().contains("PRY")){
                    //프레이룸일 경우
                    Intent intent = new Intent(ListActivity.this, PrayStoreActivity.class);
                    intent.putExtra("CustomMarker", sendMarker);
                    startActivity(intent);
                }else{
                    //일반 상점일 경우
                    Intent intent = new Intent(ListActivity.this, StoreActivity.class);
                    intent.putExtra("CustomMarker", sendMarker);
                    startActivity(intent);
                }


                //Intent intent = new Intent(ListActivity.this, StoreActivity.class);
                //intent.putExtra("CustomMarker", sendMarker);
                //startActivity(intent);



                //CustomMarker curItem = (CustomMarker) adapter.getItem(position);
                //String curData = curItem.getName();

                Toast.makeText(getApplicationContext(), "Selected : " + sendMarker.getName(), Toast.LENGTH_LONG).show();

            }
        });

        // 상점 종류에 따라 리스트 표시하기
        FrameLayout.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                //**리스트뷰 애니메이션
                AnimationSet set = new AnimationSet(true);
                Animation animation = new AlphaAnimation(0.0f, 1.0f);

                animation.setDuration(50);

                set.addAnimation(animation);

                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                animation.setDuration(200);

                set.addAnimation(animation);

                LayoutAnimationController controller =
                        new LayoutAnimationController(set, 0.5f);

                //** 리스트뷰 애니메이션

                switch (v.getId()) {
                    case R.id.allicon:
                        adapter.clearItem();

                        for (int i = 0; i < MapsActivity.markers.size(); i++){
                                adapter.addItem(MapsActivity.markers.get(i));
                        }
                        listView1.setLayoutAnimation(controller);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.pinicon:
                        adapter.clearItem();

                        for (int i = 0; i < MapsActivity.mymarkers.size(); i++){
                                adapter.addItem(MapsActivity.mymarkers.get(i));
                        }
                        listView1.setLayoutAnimation(controller);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.prayicon:
                        adapter.clearItem();

                        for (int i = 0; i < MapsActivity.markers.size(); i++){
                            if(MapsActivity.markers.get(i).getType().toString().contains("PRY"))
                            adapter.addItem(MapsActivity.markers.get(i));
                        }
                        listView1.setLayoutAnimation(controller);
                        adapter.notifyDataSetChanged();

                        break;
                    case R.id.cafeicon:
                        adapter.clearItem();

                        for (int i = 0; i < MapsActivity.markers.size(); i++){
                            if(MapsActivity.markers.get(i).getType().toString().contains("CAF"))
                                adapter.addItem(MapsActivity.markers.get(i));
                        }
                        listView1.setLayoutAnimation(controller);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.foodicon:
                        adapter.clearItem();
                        for (int i = 0; i < MapsActivity.markers.size(); i++){
                            if(MapsActivity.markers.get(i).getType().toString().contains("RES"))
                                adapter.addItem(MapsActivity.markers.get(i));
                        }
                        listView1.setLayoutAnimation(controller);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.dressicon:
                        adapter.clearItem();
                        for (int i = 0; i < MapsActivity.markers.size(); i++){
                            if(MapsActivity.markers.get(i).getType().toString().contains("BTQ"))
                                adapter.addItem(MapsActivity.markers.get(i));
                        }
                        listView1.setLayoutAnimation(controller);
                        adapter.notifyDataSetChanged();
                        break;

                }
            }
        };
        findViewById(R.id.allicon).setOnClickListener(mClickListener);
        findViewById(R.id.pinicon).setOnClickListener(mClickListener);
        findViewById(R.id.prayicon).setOnClickListener(mClickListener);
        findViewById(R.id.cafeicon).setOnClickListener(mClickListener);
        findViewById(R.id.foodicon).setOnClickListener(mClickListener);
        findViewById(R.id.dressicon).setOnClickListener(mClickListener);



        //스와이프 동작 이벤트 처리 (화면이동)
        topSpaceLayout = (FrameLayout) findViewById(R.id.topSpace);
        topSpaceLayout.setOnTouchListener(new ListActivity.OnSwipeTouchListener(ListActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent();

                setResult(RESULT_OK,intent);
                finish();
            }

        });

    }



    public void onButton1Clicked(View v) {
        Toast.makeText(this, "버튼클릭됨", Toast.LENGTH_LONG).show();


        Intent intent = new Intent();
        intent.putExtra("name","하ㅣ하하");

        setResult(RESULT_OK,intent);
        finish();

    }

    // 맵으로 이동
    public void onMapBtnClicked(View v) {
        Toast.makeText(this, "버튼클릭됨", Toast.LENGTH_LONG).show();


        Intent intent = new Intent();
        intent.putExtra("name","하ㅣ하하");

        setResult(RESULT_OK,intent);
        finish();
    }


    // 스와이프 동작 이벤트
    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new ListActivity.OnSwipeTouchListener.GestureListener());
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
    }


}
