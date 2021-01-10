package com.example.kma_application.Models;

import java.io.Serializable;

public class Image implements Serializable {
    private String _id;
    private String id;
    private String _class;
    private String data;
    private String size;
    private String __v;

    public Image(String _id, String id, String _class, String data, String size, String __v) {
        this._id = _id;
        this.id = id;
        this._class = _class;
        this.data = data;
        this.size = size;
        this.__v = __v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
