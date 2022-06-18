package com.example.administrator.hello;

import java.io.Serializable;

public class User implements Serializable {

    //유저 테이블 정보
    String id;          //아이디 *필수
    String name;        //이름   *필수
    String sex;         //성별   *필수
    String age;         //나이
    String nation;      //국적

    String type;        //무슬림, 비건, 채식주의자 등


}
