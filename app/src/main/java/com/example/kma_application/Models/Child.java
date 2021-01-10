package com.example.kma_application.Models;

import java.io.Serializable;

public class Child extends Person implements Serializable {
    private String birth;
    private String _class;
    private String height;
    private String weight;
    private String body_ratio;

    public Child(String phone, String name, String email, String birth, String _class, String height, String weight, String body_ratio) {
        super(phone, name, email);
        this.birth = birth;
        this._class = _class;
        this.height = height;
        this.weight = weight;
        this.body_ratio = body_ratio;
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
}
