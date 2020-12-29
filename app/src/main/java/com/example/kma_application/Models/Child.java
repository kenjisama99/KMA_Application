package com.example.kma_application.Models;

import java.io.Serializable;

public class Child implements Serializable {
    private String _id;
    private String phone;
    private String name;
    private String birth;
    private String _class;
    private String height;
    private String weight;
    private String body_ratio;
    private String id;

    public Child(String _id, String phone, String name, String birth, String _class, String height, String weight, String body_ratio, String id) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this.birth = birth;
        this._class = _class;
        this.height = height;
        this.weight = weight;
        this.body_ratio = body_ratio;
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBody_ratio() {
        return body_ratio;
    }

    public void setBody_ratio(String body_ratio) {
        this.body_ratio = body_ratio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
