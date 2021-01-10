package com.example.kma_application.Models;

import java.io.Serializable;

public class  Teacher extends Person implements Serializable {
    private String _class;

    public Teacher(String phone, String name, String email, String _class) {
        super(phone, name, email);
        this._class = _class;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
