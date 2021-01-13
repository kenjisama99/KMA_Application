package com.example.kma_application.Models;

import java.io.Serializable;

public class Parent extends Teacher implements Serializable {
    private String childName;
    private String teacherName;
    private String teacherPhone;

    public Parent(String phone, String name, String email, String _class, String childName, String teacherName, String teacherPhone) {
        super(phone, name, email, _class);
        this.childName = childName;
        this.teacherName = teacherName;
        this.teacherPhone = teacherPhone;
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

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }
}
