package com.abid.admin.demoapp.model;

public class DataModel {
    String _id;
    String title;
    String color;
    String slug;
    int order;


    public void set_id(String id){
        _id = id;
    }
    public String get_id(){
        return _id;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
       return color;
    }

    public void setSlug(String slug){
        this.slug = slug;
    }
    public String getSlug(){
        return slug;
    }

    public void setOrder(int order){
        this.order = order;
    }
    public int getOrder(){
        return order;
    }
}
