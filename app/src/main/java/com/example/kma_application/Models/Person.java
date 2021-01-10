package com.example.kma_application.Models;

import java.io.Serializable;

public class Person implements Serializable {
    private String _id;
    private String phone;
    private String name;
    private String email;

    public Person(String phone, String name, String email) {
        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public Person(String _id, String phone, String name, String email) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
