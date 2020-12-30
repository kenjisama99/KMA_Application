package com.example.kma_application.Models;

import java.io.Serializable;

public class Absent implements Serializable {
    private String _id;
    private String phone;
    private String name;
    private String _class;
    private String startDate;
    private String endDate;
    private String content;
    private String __v;

    public Absent(String _id, String phone, String name, String _class, String startDate, String endDate, String content, String __v) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this._class = _class;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.__v = __v;
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

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
