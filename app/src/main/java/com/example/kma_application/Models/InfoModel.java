package com.example.kma_application.Models;

public class InfoModel {
    private String _id;
    private String phone;
    private String name;
    private String id;

    public InfoModel(String phone, String name, String id) {
        this.phone = phone;
        this.name = name;
        this.id = id;
    }

    public InfoModel(String _id, String phone, String name, String id) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
