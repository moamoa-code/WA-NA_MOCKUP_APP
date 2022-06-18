package com.example.administrator.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class StoreActivity extends Activity {

    ListView dishMenuListView;
    DishMenuListAdapter dishMenuListAdapter;

    ScrollView scrollView;

    CustomMarker customMarker;
    TextView tvTitle;
    TextView tvOpen;
    TextView tvPhone;
    TextView tvContent;
    TextView tvLoc;
    ImageView ivPin;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    ImageView imageView;

    ViewPager bsViewPager;
    ViewPagerAdapterBs bsAdapter;

    FrameLayout reportFrame;
    TextView reportBtn;
    Button reportSend;
    EditText reportText;

    LinearLayout bossSpecial;
    LinearLayout frame_dishimenu;
    FrameLayout dishMenuFrame;

    ImageView dishMenuFrameCloseBtn;
    TextView dishMenuFrameTitle;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Log.d("스토어 액티비티 실행됨","스토어 액티비티 실행됨");
        LatLng asd = new LatLng(123.123,123.123);
        Intent intent = getIntent();


        ////// 스크롤뷰 안 리스트뷰에 스크롤 활성화
        /*
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        dishMenuListView = (ListView)findViewById(R.id.listview_dishimenu);

        dishMenuListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dishMenuListView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        */

        customMarker = (CustomMarker)intent.getSerializableExtra("CustomMarker");
        customMarker.setLatLng(customMarker.getLat(),customMarker.getLng());

        //String strTitle = intent.getExtras().getString("CustomMarker");
        String strTitle = customMarker.getName();

        Log.d("상점 : 가져온 데이터 : ",strTitle);

        Log.d(this.getClass().getSimpleName(), strTitle);

        /*
        customMarker = getMarkerObject(strTitle);
        Log.d(this.getClass().getSimpleName(), customMarker.getLat()+"");
        getServerData();
        */

        // 값 집어넣기
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(customMarker.getName());
        tvOpen = (TextView) findViewById(R.id.tv_open);
        tvOpen.setText(customMarker.getOpen());
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvPhone.setText(customMarker.getTel());
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText(customMarker.getContent());
        tvLoc = (TextView) findViewById(R.id.tv_loc);
        tvLoc.setText(customMarker.getLoc());
        ivPin = (ImageView) findViewById(R.id.pinebtn);

        reportFrame = (FrameLayout) findViewById(R.id.reportFrame);
        reportFrame.setVisibility(View.GONE);
        reportText = (EditText) findViewById(R.id.reportText);




        // 리포트 레이아웃 셋팅
        final FrameLayout.LayoutParams reportParam_full = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        reportParam_full.setMargins(getDpToPixel(this,50),getDpToPixel(this,50),getDpToPixel(this,50),getDpToPixel(this,50));
        final FrameLayout.LayoutParams reportParam_texted = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        reportParam_texted.setMargins(getDpToPixel(this,50),0,getDpToPixel(this,50),getDpToPixel(this,50));

        reportFrame.setLayoutParams(reportParam_full);

        viewPager = (ViewPager) findViewById(R.id.vp_store);
        //adapter = new ViewPagerAdapter(this, customMarker.getPic1());
        adapter = new ViewPagerAdapter(this, customMarker.getPics());
        viewPager.setAdapter(adapter);


        bossSpecial = (LinearLayout) findViewById(R.id.boss);
        bsViewPager = (ViewPager) findViewById(R.id.vp_bs);
        //adapter = new ViewPagerAdapter(this, customMarker.getPic1());
        if(customMarker.getBsPics().isEmpty()){
            bossSpecial.setVisibility(View.GONE);
        }else {
            bsAdapter = new ViewPagerAdapterBs(this, customMarker.getBsPics());
            bsViewPager.setAdapter(bsAdapter);
        }




        //음식메뉴 리스트뷰

        frame_dishimenu = (LinearLayout)findViewById(R.id.frame_dishimenu);
        dishMenuFrame = (FrameLayout)findViewById(R.id.dishMenuFrame);

        dishMenuListView = (ListView) findViewById(R.id.listview_dishimenu);
        dishMenuListAdapter = new DishMenuListAdapter(this);

        dishMenuFrameCloseBtn = (ImageView) findViewById(R.id.dishMenuFrameCloseBtn);
        dishMenuFrameTitle = (TextView) findViewById(R.id.dishMenuFrameTitle);
        dishMenuFrameTitle.setText(customMarker.getName()+" Menu");





        if(customMarker.getDishMenus().isEmpty()){
            //bossSpecial.setVisibility(View.GONE);
        }else {
            for (int i = 0; i < customMarker.getDishMenus().size(); i++){
                dishMenuListAdapter.addItem(customMarker.getDishMenus().get(i));
            }
        }
        Log.d("아무거나 보스스페셜",customMarker.getBsPics().size()+"");
        Log.d("아무거나 디쉬아이템",customMarker.getDishMenus().size()+"");

        dishMenuListView.setAdapter(dishMenuListAdapter);

        dishMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "버튼클릭됨", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });





        //즐겨찾기 목록에 있으면 아이콘 변경
        if(isNotMyList(MapsActivity.mymarkers,customMarker)){
            ivPin.setImageResource(R.drawable.pin_w);
        }else{
            ivPin.setImageResource(R.drawable.pin_p);
        }

        // 각종 버튼 이벤트 처리
        ImageView.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.pinebtn:
                        if(isNotMyList(MapsActivity.mymarkers,customMarker))
                        {
                            MapsActivity.mymarkers.add(customMarker);
                            //즐겨찾기 아이콘 변경
                            ivPin.setImageResource(R.drawable.pin_p);
                            Toast.makeText(StoreActivity.this, "나의 리스트에 추가했습니다.", Toast.LENGTH_LONG).show();
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("추가후 mymarkers : ", MapsActivity.markers.get(i).getName());
                            }
                            break;
                        }else{
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                if(MapsActivity.mymarkers.get(i).getName().equals(customMarker.getName())){
                                    Log.d("삭제시도 인덱스 : ", i+"");
                                    MapsActivity.mymarkers.remove(i);
                                }
                            }
                            Toast.makeText(StoreActivity.this, "나의 리스트에서 제거했습니다.", Toast.LENGTH_LONG).show();
                            ivPin.setImageResource(R.drawable.pin_w);
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("삭제후 mymarkers : ", MapsActivity.markers.get(i).getName());
                            }
                        }
                        break;
                    case R.id.reportBtn:
                    {
                        reportFrame.setVisibility(View.VISIBLE);
                        Log.d("아무거나 ","리포트버튼눌림");
                        break;
                    }

                    case R.id.reportSend:
                    {
                        reportFrame.setVisibility(View.GONE);
                        reportFrame.setLayoutParams(reportParam_full);
                        Log.d("아무거나 ","센드버튼눌림");
                        break;
                    }
                    case R.id.reportClose:
                    {
                        reportFrame.setVisibility(View.GONE);
                        reportFrame.setLayoutParams(reportParam_full);
                        break;
                    }
                    case R.id.reportFrame:
                    {
                        reportFrame.setLayoutParams(reportParam_full);
                        break;
                    }
                    case R.id.reportText:
                    {
                        reportFrame.setLayoutParams(reportParam_texted);
                        break;
                    }
                    case R.id.frame_dishimenu:
                    {
                        dishMenuFrame.invalidate();
                        Animation ani = new TranslateAnimation(0, 0, -400, 0);
                        ani.setDuration(120);
                        dishMenuFrame.setAnimation(ani);
                        dishMenuFrame.setVisibility(View.VISIBLE);
                        break;
                    }
                    case R.id.dishMenuFrameCloseBtn:
                    {
                        dishMenuFrame.invalidate();
                        Animation ani = new TranslateAnimation(0, 0, 0, -400);
                        ani.setDuration(120);
                        dishMenuFrame.setAnimation(ani);
                        dishMenuFrame.setVisibility(View.GONE);
                        break;
                    }
                }

            }
        };
        findViewById(R.id.pinebtn).setOnClickListener(mClickListener);
        findViewById(R.id.reportBtn).setOnClickListener(mClickListener);
        findViewById(R.id.reportSend).setOnClickListener(mClickListener);
        findViewById(R.id.reportText).setOnClickListener(mClickListener);
        findViewById(R.id.reportFrame).setOnClickListener(mClickListener);
        findViewById(R.id.reportClose).setOnClickListener(mClickListener);
        findViewById(R.id.frame_dishimenu).setOnClickListener(mClickListener);
        findViewById(R.id.dishMenuFrameCloseBtn).setOnClickListener(mClickListener);






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


    // 뒤로가기키 눌릴경우
    @Override
    public void onBackPressed() {

        //리포트 프레임 숨기기
        if(reportFrame.getVisibility() == View.VISIBLE){
            //레이아웃 원래대로 돌리기
            final FrameLayout.LayoutParams reportParam_full = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            reportParam_full.setMargins(getDpToPixel(this,50),getDpToPixel(this,50),getDpToPixel(this,50),getDpToPixel(this,50));
            reportFrame.setLayoutParams(reportParam_full);
            //숨김
            reportFrame.setVisibility(View.GONE);
        }
        else
        {
            super.onBackPressed();
        }
    }

    // 뒤로가기 버튼
    public void onBackBtnClicked(View v) {
        Toast.makeText(this, "끝내기 버튼 클릭됨", Toast.LENGTH_LONG).show();

        //Intent intent = new Intent();
        //intent.putExtra("name","하ㅣ하하");

        //setResult(RESULT_OK,intent);
        finish();
    }

    // SNS에 공유하기 버튼
    public void onShareBtnClicked(View v) {
        Toast.makeText(this, "공유 버튼 클릭됨", Toast.LENGTH_LONG).show();
    }


    public CustomMarker getMarkerObject(String title){

        for (int i = 0; i < MapsActivity.markers.size(); i ++)
        {
            if (MapsActivity.markers.get(i).getName().equals(title)){
                return MapsActivity.markers.get(i);
            }
        }
        Log.d("fsdfsd","null 리턴함");
        return null;
    }

    void getServerData(){
        customMarker.setContent("상점입니다.");
        customMarker.setLoc("주소입니다");
        customMarker.setOpen("오픈시간입니다");
        customMarker.setProvide("제공 서비스");
        customMarker.setRules("규칙");
        customMarker.setTel("전화번호");
        customMarker.setManager("싸장님");
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
