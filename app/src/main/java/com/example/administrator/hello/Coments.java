package com.example.administrator.hello;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class Coments implements Serializable {


    //유저 테이블 정보
    String id;          //유저 아이디
    String name;        //유저 이름
    String nation;      //국적
    String type;        //무슬림, 비건, 채식주의자 등


    //리뷰 코멘트 내용
    String coments;             //리뷰 내용
    String date;                //날짜
    String pic;                 //리뷰사진 (1장)
    double score;               //별점

    int option;                 //익명 여부

}
