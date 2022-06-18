package com.example.administrator.hello;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;



// 커스텀 마커
// http://gun0912.tistory.com/57


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final int REQUEST_CODE_LIST = 1001;


    String mapTarget;

    View marker_root_view;

    private GoogleMap mMap;

    FrameLayout topSpaceLayout;

    ////// 상점 데이터 리스트
    static ArrayList<CustomMarker> markers;

    ///// 음식메뉴 데이터 리스트
    static ArrayList<DishMenu> dishMenus;

    ///// 보스스페셜
    static ArrayList<ItemData> bsDatas;


    //// 즐겨찾기 추가한 상점 데이터 리스트
    public static ArrayList<CustomMarker> mymarkers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        marker_root_view = LayoutInflater.from(this).inflate(R.layout.marker, null);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        //스와이프 동작 이벤트 처리 (화면이동)
        topSpaceLayout = (FrameLayout) findViewById(R.id.topSpace);
        topSpaceLayout.setOnTouchListener(new OnSwipeTouchListener(MapsActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent();

                setResult(RESULT_OK,intent);
                finish();
            }
            public void onSwipeLeft() {
                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                startActivityForResult(intent,REQUEST_CODE_LIST);
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        markers = new ArrayList<>();
        mymarkers = new ArrayList<>();
        dishMenus = new ArrayList<>();



        //맵중앙 좌표
        LatLng centerPos = new LatLng(37.552,126.925);




        // 보스스페셜 매뉴 셋
        ItemData bsCafe1 = new ItemData("CAF1","CAF","아인슈페너","4,800","file:///android_asset/coffee1.jpg");
        ItemData bsCafe2 = new ItemData("CAF2","CAF","클래식커피","5,800","file:///android_asset/coffee2.jpg");
        ItemData bsCafe3 = new ItemData("CAF3","CAF","그린티프라푸치노","6,800","file:///android_asset/coffee3.jpg");

        ItemData bsRes1 = new ItemData("RES1","RESHAL","닭볶음","19,800","file:///android_asset/dark1.jpg");
        ItemData bsRes2 = new ItemData("RES2","RESHAL","치즈떡볶이","12,800","file:///android_asset/ddobo2.jpg");

        ItemData bsBtq1 = new ItemData("BTQ1","BTQ","하라케케","24,800","file:///android_asset/h1.jpg");
        ItemData bsBtq2 = new ItemData("BTQ2","BTQ","망고시드","14,800","file:///android_asset/h2.jpg");

        ItemData bsGoods1 = new ItemData("GTZ1","GTZ","나이키운동화","64,800","file:///android_asset/s1.jpg");
        ItemData bsGoods2 = new ItemData("GTZ2","GTZHIJ","히잡","56,800","file:///android_asset/hj1.jpg");

        ItemData adCos1 = new ItemData("ADC1","BTQ","물방울가득수분크림","26,800","file:///android_asset/adcos1.jpg");
        ItemData adCos2 = new ItemData("ADC2","BTQ","이니스프리","16,800","file:///android_asset/adcos2.jpg");
        ItemData adCaf1 = new ItemData("ADF3","CAF","설빙","9,800","file:///android_asset/adcafe1.jpg");
        ItemData adRes1 = new ItemData("ADR1","RESHAL","뉴욕할랄푸드","12,800","file:///android_asset/adfood1.jpg");
        ItemData adRes2 = new ItemData("ADR1","RESHAL","풀떼기","42,800","file:///android_asset/adfood2.jpg");



        // 음식메뉴 셋
        DishMenu dm1 = new DishMenu("dm1", "22", "KOR:{진짜할랄삼겹살}:KOR, ENG:{REAL HALAL PORK}:ENG", "￦12,900", "HAL,BST,POR", "", "");
        DishMenu dm2 = new DishMenu("dm2", "22", "KOR:{소주}:KOR, ENG:{SOJU}:ENG", "￦4,900", "", "", "");
        DishMenu dm3 = new DishMenu("dm3", "22", "비건된장찌게", "￦5,900", "VEG", "", "★");
        DishMenu dm4 = new DishMenu("dm4", "22", "소곱창", "￦15,900", "BEF", "", "★");
        DishMenu dm5= new DishMenu("dm5", "22", "닭갈비", "￦15,900", "CHI, BST", "", "★★★");
        DishMenu dm6= new DishMenu("dm6", "22", "매운탕", "￦15,900", "SEA, HAL", "", "★★★");

        DishMenu dm7= new DishMenu("dm7", "1", "알라", "15900", "SEA, HAL", "", "★★★");

        DishMenu dd1 = new DishMenu("dd1","11", "KOR:{유가네 닭갈비}:KOR, ENG:{YOOGANE’S MARINATED CHICKEN GALB}:ENG", "￦10,900", "CHI, GAL, BST", "", "3", "Stir-frying marinated diced chicken in a Yoogane’s marinated chicken sauce with sweet potatoes, cabbage, perilla leaves, scallions, tteok (rice cake)", "file:///android_asset/uga1.jpg");
        DishMenu dd2 = new DishMenu("dd2","11", "KOR:{치즈퐁~닭}:KOR, ENG:{fresh cheese chicken galbi}:ENG", "￦12,900", "CHI, GAL, MIL, ", "", "2", "Stir-frying marinated diced chicken in a Yoogane’s marinated chicken sauce with sweet potatoes, cabbage, perilla leaves, scallions, tteok (rice cake)", "file:///android_asset/yg2.png");
        DishMenu dd3 = new DishMenu("dd3","11", "KOR:{콩닭콩닭}:KOR, ENG:{bean sprouts & chicken galbi}:ENG", "￦13,900", "CHI, GAL, GLU ", "", "2", "Stir-frying marinated diced chicken in a Yoogane’s marinated chicken sauce with sweet potatoes, cabbage, perilla leaves, scallions, tteok (rice cake)", "file:///android_asset/yg3.jpg");



        CustomMarker yg1 = new CustomMarker(11, 37.551515, 126.924913, "RES","유가네 닭갈비",
                "유가네 닭갈비", "MUK", "보스스페셜",
                "오너","09:00 - 22:00", "010-1234-4567", "서울시 우리집","112","4.5","file:///android_asset/yg1.jpg");
        yg1.addPic("file:///android_asset/yg1.jpg");
        yg1.addBsPics(bsCafe1);
        yg1.addDishMenu(dd1);
        yg1.addDishMenu(dd2);
        yg1.addDishMenu(dd3);
        markers.add(yg1);


        CustomMarker sb1 = new CustomMarker(12, 37.551555, 126.924983, "RES","사월에 보리밥",
                "사월에 보리밥", "MUK", "보스스페셜",
                "오너","09:00 - 22:00", "010-1234-4567", "서울시 우리집","78","4.8","file:///android_asset/sb1.gif");

        markers.add(sb1);



        //
        // 상점정보(마커, 리스트) 초기화
        // MUK : Mukena, SAR : sarong, CAR : carpet, QOR : Koran
        // PRY : 프레이룸, CAF : 카페, RES : 음식점, BTQ : 부띠끄
        CustomMarker cm1 = new CustomMarker(1, 37.551555, 126.924983, "PRY","홍대 서울캠",
                "홍대 서울캠퍼스", "MUK", "보스스페셜",
                "오너","09:00 - 22:00", "010-1234-4567", "서울시 우리집","112","4.5","file:///android_asset/aeroad.jpg");
        cm1.addPic("file:///android_asset/aeroad.jpg");
        cm1.addPic("file:///android_asset/p5x.jpg");
        cm1.addBsPics(adCos1);

        CustomMarker cm2 = new CustomMarker(2, 37.554881, 126.925187, "PRY","서교초등학교",
                "내용입니당", "MUKSAR", "보스스페셜",
                "오너","09:00 - 22:00", "010-0000-0000", "서울시 우리집","54","4.1","file:///android_asset/p5x.jpg");
        cm2.addPic("file:///android_asset/aeroad.jpg");
        cm2.addPic("file:///android_asset/p5x.jpg");
        cm2.addBsPics(adCos2);

        CustomMarker cm3 = new CustomMarker(3, 37.552788, 126.921551, "CAF","기도실1",
               "내용입니당", "", "보스스페셜",
               "오너","09:00 - 22:00", "010-0000-0000", "서울시 우리집","23","3.7","file:///android_asset/andean.jpg");
        cm3.addPic("file:///android_asset/aeroad.jpg");
        cm3.addPic("file:///android_asset/p5x.jpg");

        CustomMarker cm4 = new CustomMarker(3, 37.559665, 126.921902, "CAF","기도실2",
                "내용입니당", "", "보스스페셜",
                "오너","09:00 - 22:00", "010-0000-0000", "서울시 우리집","213","3.9","file:///android_asset/aeroad.jpg");
        cm4.addPic("file:///android_asset/aeroad.jpg");
        cm4.addPic("file:///android_asset/p5x.jpg");

        CustomMarker cm5 = new CustomMarker(3, 37.555298, 126.917908, "PRYCAF","기도실3",
                "내용입니당", "QORCAR", "보스스페셜",
                "오너","09:00 - 22:00", "010-0000-0000", "서울시 우리집","223","3.7","file:///android_asset/andean.jpg");
        cm5.addPic("file:///android_asset/aeroad.jpg");
        cm5.addPic("file:///android_asset/p5x.jpg");

        CustomMarker ca1 = new CustomMarker(21, 37.5524244, 126.9207866, "CAF","달콤 홍대점",
                "사장님이 맛있는 카페 https://goo.gl/maps/Kaku5zNV8Ay", "", "보스스페셜",
                "오너","09:00 - 22:00", "02-333-0442", "서울특별시 마포구 서교동 358-37","321","4.5","file:///android_asset/dalkomm1.jpg");
        ca1.addPic("file:///android_asset/dalkomm1.jpg");
        ca1.addPic("file:///android_asset/dalkomm2.jpg");
        //ca1.addBsPics("file:///android_asset/adcafe1.jpg");
        //ca1.addBsPics("file:///android_asset/coffee2.jpg");
        markers.add(ca1);

        CustomMarker ca2 = new CustomMarker(22, 37.5466057, 126.9209313, "CAF","제비다방 홍대점",
                "맛있는 사장님 카페 https://goo.gl/maps/LkVChi7J9hr", "", "보스스페셜",
                "오너","09:00 - 22:00", "02-325-1969", "서울특별시 마포구 상수동 330-12","232","4.5","file:///android_asset/jeb1.jpg");
        ca2.addPic("file:///android_asset/jeb1.jpg");
        ca2.addPic("file:///android_asset/jeb2.jpg");
        ca2.addBsPics(bsCafe1);
        ca2.addBsPics(bsCafe2);
        ca2.addDishMenu(dd1);
        ca2.addDishMenu(dm2);
        ca2.addDishMenu(dm3);
        ca2.addDishMenu(dm4);
        ca2.addDishMenu(dm5);
        //qca2.addDishMenu(dm6);
        markers.add(ca2);
        Log.d("아무거나 보스 초기값",ca2.getBsPics().size()+"");
        Log.d("아무거나 디쉬 초기값",ca2.getDishMenus().size()+"");

        CustomMarker re1 = new CustomMarker(31, 37.5508969, 126.9214532, "RES","봉추찜닭 홍대점",
                "맛있는 찜닭 https://goo.gl/maps/VvQMrvr5nJB2", "", "보스스페셜",
                "오너","09:00 - 22:00", "02-323-9381", "서울특별시 마포구 서교동 361-9","112","4.5","file:///android_asset/bonchu1.jpg");
        re1.addPic("file:///android_asset/bonchu1.jpg");
        re1.addPic("file:///android_asset/bonchu2.jpg");
        re1.addBsPics(bsRes1);
        markers.add(re1);

        CustomMarker re2 = new CustomMarker(32, 37.552078, 126.9187173, "PRYRES","또보겠지 떡볶이짐",
                "https://goo.gl/maps/hBJLj4khLRF2", "CARQORMUKSAR", "보스스페셜",
                "오너","09:00 - 22:00", "070-8639-0105", "서울특별시 마포구 서교동 366-11","112","4.5","file:///android_asset/ddobo1.jpg");
        re2.addPic("file:///android_asset/ddobo1.jpg");
        re2.addBsPics(adRes2);
        markers.add(re2);

        CustomMarker bt1 = new CustomMarker(41, 37.5572572, 126.9227029, "PRYBTQ","유니클로 홍대점",
                "유니클로 Y'ZPARK홍대점 - 유니클로 Y'ZPARK홍대점 https://goo.gl/maps/Asd7NLYf1DR2", "", "보스스페셜",
                "오너","09:00 - 22:00", "02-3143-7301", "서울특별시 마포구 동교동 166-15","512","4.5","file:///android_asset/uni1.jpg");
        bt1.addPic("file:///android_asset/uni1.jpg");
        bt1.addPic("file:///android_asset/uni2.png");
        markers.add(bt1);

        CustomMarker bt2 = new CustomMarker(42, 37.5572572, 126.9227029, "PRYBTQ","엠플레이그라운드 홍대점",
                "https://goo.gl/maps/XmyUsBReawu", "", "보스스페셜",
                "오너","09:00 - 22:00", "02-3143-7301", "서울특별시 마포구 서교동 홍익로3길 8","512","4.5","file:///android_asset/mpl1.jpg");
        bt2.addPic("file:///android_asset/mpl1.jpg");
        bt2.addPic("file:///android_asset/mpl2.jpg");
        markers.add(bt2);


        markers.add(cm1);
        markers.add(cm2);
        markers.add(cm3);
        markers.add(cm4);
        markers.add(cm5);

        mymarkers.add(yg1);


        // 마커 지도에 추가
        //for (int i = 0; i < markers.size(); i ++){
        //    CustomMarker cm = markers.get(i);
        //    mMap.addMarker(new MarkerOptions().position(cm.getLatLng()).title(cm.getName()));
        //}

        Log.d("아무거나","맵엑티비티 실행됨");
        Intent intent = getIntent();

        if (intent.hasExtra("MapTarget")){
            mapTarget = (String)intent.getStringExtra("MapTarget");
            if (mapTarget.equals("prayroom")) {
                markMarker("PRY");
            }else if(mapTarget.equals("mypick")){
                markMyMarker();
            }else if(mapTarget.equals("food")){
                markMarker("RES");
            }else if(mapTarget.equals("btq")){
                markMarker("BTQ");
            }else if(mapTarget.equals("cafe")){
                markMarker("CAF");
            }
            else{
                markMarker();
            }
        }else{
            markMarker();
        }



        //카메라 위치 변경
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(centerPos));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerPos,15));

        mMap.setOnMarkerClickListener(this);
        //enableMyLocation();



    }

    public void markMyMarker(){
        mMap.clear();
        for (int i = 0; i < mymarkers.size(); i ++){
            CustomMarker cm = mymarkers.get(i);
            mMap.addMarker(new MarkerOptions().position(cm.getLatLng()).title(cm.getName()));
        }
    }


    //모든 마커 표시
    public void markMarker(){
        mMap.clear();
        //Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        for (int i = 0; i < markers.size(); i ++){
            CustomMarker cm = markers.get(i);
                mMap.addMarker(new MarkerOptions().position(cm.getLatLng()).title(cm.getName()).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view))));
        }
    }

    //상점 타입별 선택적 마커 표시
    public void markMarker(String target){
        mMap.clear();
        //Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        for (int i = 0; i < markers.size(); i ++){
            CustomMarker cm = markers.get(i);
            if(markers.get(i).getType().contains(target)){
                mMap.addMarker(new MarkerOptions().position(cm.getLatLng()).title(cm.getName()).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view))));
            }
        }
    }

    //커스텀마커를 위한 함수
    private Bitmap createDrawableFromView(Context context, View view) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


    private void startLocationService(){
        LocationManager manager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
    }

    //전체리스트 표시 버튼
    public void onAllBtnClicked(View v) {
        markMarker();
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //마이핀 표시
    public void onMypinBtnClicked(View v) {
        markMyMarker();
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }


    //기도실 표시
    public void onPrayerBtnClicked(View v) {
        markMarker("PRY");
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //카페 표시
    public void onCafeBtnClicked(View v) {
        markMarker("CAF");
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //옷가게 표시
    public void onDressBtnClicked(View v) {
        markMarker("BTQ");
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //음식점 표시
    public void onFoodBtnClicked(View v) {
        markMarker("RES");
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }


    //홈버튼 클릭
    public void onHomeBtnClicked(View v) {

        Intent intent = new Intent();

        setResult(RESULT_OK,intent);
        finish();

        /*
        Toast.makeText(this, "홈버튼 클릭됨", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        */
        //startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //리스트 버튼 클릭 -> 리스트로 이동
    public void onCastBtnClicked(View v) {
        //Toast.makeText(this, "캐스트버튼 클릭됨", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),ItemCastActivity.class);
        startActivityForResult(intent,REQUEST_CODE_LIST);
    }


    //리스트 버튼 클릭 -> 리스트로 이동
    public void onListBtnClicked(View v) {
        Toast.makeText(this, "리스트 버튼클릭됨", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
        startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //내 즐겨찾기 리스트 클릭
    public void onMyListBtnClicked(View v) {
        Toast.makeText(this, "리스트 버튼클릭됨", Toast.LENGTH_LONG).show();
        markMyMarker();

        Intent intent = new Intent(getApplicationContext(),MyListActivity.class);
        startActivityForResult(intent,REQUEST_CODE_LIST);
    }

    //기도실 아이콘 클릭
    public void onPrayListBtnClicked(View v) {
        markMarker();
    }

    //리스트 액티비티에서 응답 받기
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);

        if (requestCode == REQUEST_CODE_LIST){


            if (resultCode == RESULT_OK){

            }
        }

    }



    //마커 클릭시 이벤트
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("아무거나id",marker.getId());
        Log.d("아무거나타이틀",marker.getTitle());
        Log.d("아무거나타이틀",""+marker.getPosition());
        Log.d("아무거나태그",""+marker.getTag());

        marker.showInfoWindow();



        //상점 데이터(selectedMarker)를 새로운 객체(sendMarker)에 깊은복사 후 전송 (포인터만 전송시 에러남...??)
        CustomMarker selectedMarker = getMarkerObject(marker.getTitle());
        CustomMarker sendMarker = new CustomMarker(selectedMarker.getId(),selectedMarker.getLat(),selectedMarker.getLng(),
                selectedMarker.getType(),selectedMarker.getName(),selectedMarker.getContent(),selectedMarker.getProvide(),
                selectedMarker.getSpecial(),selectedMarker.getManager(),selectedMarker.getOpen(),selectedMarker.getTel(),
                selectedMarker.getLoc(),selectedMarker.getRv_count(),selectedMarker.getScore(),selectedMarker.getPic1(),selectedMarker.getLatLng());
        sendMarker.cpyPic(selectedMarker.getPics());
        sendMarker.cpyBsPics(selectedMarker.getBsPics());
        sendMarker.cpyDishMenus(selectedMarker.getDishMenus());
        //sendMarker.setLatLng(sendMarker.getLat(),sendMarker.getLng());

        if (selectedMarker.getType().toString().contains("PRY")){
            //프레이룸일 경우
            Intent intent = new Intent(MapsActivity.this, PrayStoreActivity.class);
            intent.putExtra("CustomMarker", sendMarker);
            startActivity(intent);
        }else{
            //일반 상점일 경우
            Intent intent = new Intent(MapsActivity.this, StoreActivity.class);
            intent.putExtra("CustomMarker", sendMarker);
            startActivity(intent);
        }

        return false;
    }


    // 스와이프 동작 이벤트
    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends SimpleOnGestureListener {

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


    //title값으로 선택된 마커 찾아냄
    public CustomMarker getMarkerObject(String title){
        for (int i = 0; i < markers.size(); i ++)
        {
            if (markers.get(i).getName().equals(title)){
                return markers.get(i);
            }
        }
        Log.d("fsdfsd","null 리턴함");
        return null;
    }



}
