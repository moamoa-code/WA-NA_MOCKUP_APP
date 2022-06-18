package com.example.administrator.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class PrayStoreActivity extends Activity {

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

    FrameLayout frCar;
    FrameLayout frQor;
    FrameLayout frMuk;
    FrameLayout frSar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praystore);

        Log.d("프레이스토어 액티비티 실행됨","프레이스토어 액티비티 실행됨");
        LatLng asd = new LatLng(123.123,123.123);
        Intent intent = getIntent();

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



        /////// ** WE PROVIDE에 아이콘 채우기
        LinearLayout.LayoutParams icon_empty = new LinearLayout.LayoutParams(0, 0);
        LinearLayout.LayoutParams icon_full = new LinearLayout.LayoutParams(getDpToPixel(this,85), getDpToPixel(this,80));

        frCar = (FrameLayout) findViewById(R.id.icon_carpet);
        frCar.setLayoutParams(icon_empty);
        frMuk = (FrameLayout) findViewById(R.id.icon_mukena);
        frMuk.setLayoutParams(icon_empty);
        frQor = (FrameLayout) findViewById(R.id.icon_koran);
        frQor.setLayoutParams(icon_empty);
        frSar = (FrameLayout) findViewById(R.id.icon_sarong);
        frSar.setLayoutParams(icon_empty);

        if (customMarker.getProvide().toString().isEmpty()){
            frCar.setLayoutParams(icon_full);
            frQor.setLayoutParams(icon_full);
            frMuk.setLayoutParams(icon_full);
            frSar.setLayoutParams(icon_full);
        }else {
            if(customMarker.getProvide().toString().contains("CAR")){
                frCar.setLayoutParams(icon_full);
            }
            if(customMarker.getProvide().toString().contains("QOR")){
                frQor.setLayoutParams(icon_full);
            }
            if(customMarker.getProvide().toString().contains("MUK")){
                frMuk.setLayoutParams(icon_full);
            }
            if(customMarker.getProvide().toString().contains("SAR")){
                frSar.setLayoutParams(icon_full);
            }
        }


        /////// **


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



        viewPager = (ViewPager) findViewById(R.id.vp_store);
        //adapter = new ViewPagerAdapter(this, customMarker.getPic1());
        adapter = new ViewPagerAdapter(this, customMarker.getPics());
        viewPager.setAdapter(adapter);

        //즐겨찾기 목록에 있으면 아이콘 변경
        if(isNotMyList(MapsActivity.mymarkers,customMarker)){
            ivPin.setImageResource(R.drawable.pin_w);
        }else{
            ivPin.setImageResource(R.drawable.pin_p);
        }

        // 즐겨찾기에 현재 상점 추가 이벤트 (핀버튼)
        ImageView.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.pinebtn:
                        if(isNotMyList(MapsActivity.mymarkers,customMarker))
                        {
                            MapsActivity.mymarkers.add(customMarker);
                            //즐겨찾기 아이콘 변경
                            ivPin.setImageResource(R.drawable.pin_p);
                            Toast.makeText(PrayStoreActivity.this, "나의 리스트에 추가했습니다.", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(PrayStoreActivity.this, "나의 리스트에서 제거했습니다.", Toast.LENGTH_LONG).show();
                            ivPin.setImageResource(R.drawable.pin_w);
                            for(int i =0;i<MapsActivity.mymarkers.size();i++){
                                Log.d("삭제후 mymarkers : ", MapsActivity.markers.get(i).getName());
                            }
                        }
                        break;
                }
            }
        };
        findViewById(R.id.pinebtn).setOnClickListener(mClickListener);
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