package com.example.administrator.hello;

import java.io.Serializable;

public class DishMenu implements Serializable {

    // HAL 할랄, VEG 비건
    // POR 돼지, BEF 소, CHI 치킨, SEA 해산물

    String id;          //고유 아이디
    String refId;       //상점 아이디
    String name;        //음식 이름
    String price;       //음식 가격
    String type;       //할랄, 비건, 등 (재료 아이콘 사용)
    String ingredient;  //음식 재료 (재료 아이콘 사용)
    String hotMeter;    //매운 정도 (고추 아이콘)
    String contents;    //상품 설명
    String pic;         //상품 사진
    int pick;           //픽(좋아요)


    public DishMenu(String id, String refId, String name, String price, String type, String ingredient, String hotMeter){
        this.id = id;
        this.refId = refId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.ingredient = ingredient;
        this.hotMeter = hotMeter;
        this.pick = 0;
    }

    public DishMenu(String id, String refId, String name, String price, String type, String ingredient, String hotMeter, String contents, String pic){
        this.id = id;
        this.refId = refId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.ingredient = ingredient;
        this.hotMeter = hotMeter;
        this.pick = 0;
        this.contents = contents;
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getHotMeter() {
        return hotMeter;
    }

    public void setHotMeter(String hotMeter) {
        this.hotMeter = hotMeter;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
