package com.example.administrator.hello;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomMarker implements Serializable {

    int id;
    double lat;
    double lng;

    String type;
    String name;
    String content;
    String provide;
    String rules;
    String special;
    String manager;
    String open;
    String tel;
    String loc;
    String score;
    String rv_count;
    String pic1;

    ArrayList<String> pics;
    ArrayList<ItemData> bsPics;
    ArrayList<DishMenu> dishMenus;

    LatLng latLng;

    /*
    public CustomMarker(double lat, double lng, int id) {
        this.lat = lat;
        this.lng = lng;
        this.id = id;
        this.latLng = new LatLng(lat, lng);
    }
    */
    public CustomMarker(CustomMarker original){
        this.id = original.id;
        this.lat = original.lat;
        this.lng = original.lng;
        this.type = original.type;
        this.name = original.name;
        this.content = original.content;
        this.provide = original.provide;
        this.special = original.special;
        this.manager = original.manager;
        this.open = original.open;
        this.tel = original.tel;
        this.loc = original.loc;
        this.pic1 = original.pic1;
        this.rv_count = original.rv_count;
        this.score = original.score;
//        this.latLng = new LatLng(lat, lng);
        this.pics = new ArrayList<>();
        this.bsPics = new ArrayList<>();
        this.dishMenus = new ArrayList<>();
    }

    public CustomMarker(int id, double lat, double lng, String type, String name, String content, String provide, String special, String manager, String open, String tel, String loc, String rv_count, String score , String pic1) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.name = name;
        this.content = content;
        this.provide = provide;
        this.special = special;
        this.manager = manager;
        this.open = open;
        this.tel = tel;
        this.loc = loc;
        this.pic1 = pic1;
        this.rv_count = rv_count;
        this.score = score;
//        this.latLng = new LatLng(lat, lng);
        this.pics = new ArrayList<>();
        this.bsPics = new ArrayList<>();
        this.dishMenus = new ArrayList<>();
        setLatLng(lat, lng);
    }

    public CustomMarker(int id, double lat, double lng, String type, String name, String content, String provide, String special, String manager, String open, String tel, String loc, String rv_count, String score , String pic1, LatLng latLng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.name = name;
        this.content = content;
        this.provide = provide;
        this.special = special;
        this.manager = manager;
        this.open = open;
        this.tel = tel;
        this.loc = loc;
        this.pic1 = pic1;
        this.rv_count = rv_count;
        this.score = score;
//        this.latLng = new LatLng(lat, lng);
        this.pics = new ArrayList<>();
        this.bsPics = new ArrayList<>();
        this.dishMenus = new ArrayList<>();
    }


    public void addPic(String pic) {
        this.pics.add(pic);
    }

    public void cpyPic(ArrayList<String> pics){
        if(!pics.isEmpty()){
            for(int i = 0; i < pics.size(); i++ ){
                this.pics.add(pics.get(i));
            }
        }
        return;
    }

    public ArrayList<ItemData> getBsPics() {
        Log.d("아무거나 get함",bsPics.isEmpty()+"");
        return bsPics;
    }


    public void addBsPics(ItemData pic) {
        Log.d("아무거나 보스스페셜 추가 : ", pic.getName());
        this.bsPics.add(pic);
    }

    public void setBsPics(ArrayList<ItemData> bsPics) {
        this.bsPics = bsPics;
    }


    public void cpyBsPics(ArrayList<ItemData> pics){
        if ((!pics.isEmpty()) || (pics.size() > 0)){
            for(int i = 0; i < pics.size(); i++ ){
                this.bsPics.add(pics.get(i));
            }
        }
        return;
    }


    public void addDishMenu(DishMenu dm){
        Log.d("아무거나 디쉬 추가 : ", dm.getName());
        this.dishMenus.add(dm);
    }


    public ArrayList<DishMenu> getDishMenus() {
        return dishMenus;
    }

    public void setDishMenus(ArrayList<DishMenu> dishMenus) {
        this.dishMenus = dishMenus;
    }

    public void cpyDishMenus(ArrayList<DishMenu> dms){
        if ((!dms.isEmpty()) || (dms.size() > 0)){
            for(int i = 0; i < dms.size(); i++ ){
                this.dishMenus.add(dms.get(i));
            }
        }
        return;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvide() {
        return provide;
    }

    public void setProvide(String provide) {
        this.provide = provide;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setLatLng(double lat, double lng) {
        if (this.latLng == null){
            this.latLng = new LatLng(lat,lng);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public ArrayList<String> getPics() {
        return pics;
    }

    public void setPics(ArrayList<String> pics) {
        this.pics = pics;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRv_count() {
        return rv_count;
    }

    public void setRv_count(String rv_count) {
        this.rv_count = rv_count;
    }
}
