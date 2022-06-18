package com.example.administrator.hello;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemData implements Serializable {

    String id;
    String name;
    String price;
    String bsPic;
    String categories;

    String sale;
    String content;
    int count_pick;



    public ItemData(String id, String categories, String name, String price, String bsPic){
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.sale = "0";
        this.content = "내용없음";
        this.count_pick = 0;
        this.bsPic = bsPic;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBsPic() {
        return bsPic;
    }

    public void setBsPic(String bsPic) {
        this.bsPic = bsPic;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount_pick() {
        return count_pick;
    }

    public void setCount_pick(int count_pick) {
        this.count_pick = count_pick;
    }


    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

}
