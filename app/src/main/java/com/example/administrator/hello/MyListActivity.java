package com.example.administrator.hello;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MyListActivity extends Activity {
    ListView listView1;
    StoreListAdapter adapter;

    public static final int REQUEST_CODE_MYLIST = 1002;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);


        listView1 = (ListView) findViewById(R.id.storelistview);

        // 어댑터 객체 생성
        adapter = new StoreListAdapter(this);
        Resources res = getResources();


        // 새로 정의한 리스너로 객체를 만들어 설정
        // 상점 클릭시 이벤트
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CustomMarker selectedMarker = (CustomMarker) adapter.getItem(position);
                CustomMarker sendMarker = new CustomMarker(selectedMarker);
                sendMarker.cpyPic(selectedMarker.getPics());

                if (selectedMarker.getType().toString().contains("PRY")){
                    //프레이룸일 경우
                    Intent intent = new Intent(MyListActivity.this, PrayStoreActivity.class);
                    intent.putExtra("CustomMarker", sendMarker);
                    startActivity(intent);
                }else{
                    //일반 상점일 경우
                    Intent intent = new Intent(MyListActivity.this, StoreActivity.class);
                    intent.putExtra("CustomMarker", sendMarker);
                    startActivity(intent);
                }

                Toast.makeText(getApplicationContext(), "Selected : " + sendMarker.getName(), Toast.LENGTH_LONG).show();

            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clearItem();

        //MapsActivity에서 생성한 상점 리스트 불러와 리스트에 추가
        for (int i = 0; i < MapsActivity.mymarkers.size(); i++){
            adapter.addItem(MapsActivity.mymarkers.get(i));
        }

        // 리스트뷰에 어댑터 설정
        listView1.setAdapter(adapter);

    }


    //응답받기
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);

        Log.d("응답옴","응답?");
        onResume();

        if (requestCode == REQUEST_CODE_MYLIST){


            if (resultCode == RESULT_OK){

            }
        }

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


}
