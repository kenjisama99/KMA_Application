package com.example.kma_application.Models;

import java.io.Serializable;

public class  Teacher extends InfoModel implements Serializable {
    private String _class;

    public Teacher(String phone, String name, String id) {
        super(phone, name, id);
    }

    public Teacher(String phone, String name, String id, String _class) {
        super(phone, name, id);
        this._class = _class;
    }

    public Teacher(String _id, String phone, String name, String id, String _class) {
        super(_id, phone, name, id);
        this._class = _class;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
