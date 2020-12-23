package com.example.kma_application.Models;

import java.io.Serializable;

public class Parent extends InfoModel implements Serializable {
    private String childName;
    private String teacherName;
    private String _class;

    public Parent(String phone, String name, String id, String childName, String teacherName, String _class) {
        super(phone, name, id);
        this.childName = childName;
        this.teacherName = teacherName;
        this._class = _class;
    }

    public Parent(String _id, String phone, String name, String id, String childName, String teacherName, String _class) {
        super(_id, phone, name, id);
        this.childName = childName;
        this.teacherName = teacherName;
        this._class = _class;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
